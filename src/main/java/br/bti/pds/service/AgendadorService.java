package br.bti.pds.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.bti.pds.enumeration.TipoAtivo;
import br.bti.pds.exception.AcaoInvalidaException;
import br.bti.pds.exception.ErroConsultaApiCriptomoedaException;
import br.bti.pds.model.Criptomoeda;
import br.bti.pds.model.ParametroAtivo;
import br.bti.pds.model.PushNotificationRequest;
import javassist.NotFoundException;
import yahoofinance.Stock;

@Service
public class AgendadorService extends AbstractAgendadorService {

	@Autowired
	private AcaoService acaoService;
	
	@Autowired
	private CriptomoedaService criptomoedaService;

	@Autowired
	private ParametroAtivoService parametroAtivoService;

	@Autowired
	private FCMService fcmService;

	private static String CORPO_MENSAGEM = "Ativo: %s\nPreço atual: %s\nParâmetro: %s";
	
	@Override
	public void aplicarLogica() {
		List<ParametroAtivo> parametrosAcao = parametroAtivoService.recuperarTodos();
		parametrosAcao.forEach(parametroAtivo -> {
			try {
				aplicarLogicaParaDisparoDePushNotification(parametroAtivo);
			} catch (AcaoInvalidaException e) {
				System.out.println(e.getCausa());
			}
		});		
	}

	private void aplicarLogicaParaDisparoDePushNotification(ParametroAtivo parametroAtivo) throws AcaoInvalidaException {
		if (parametroAtivo.getTipoAtivo().equals(TipoAtivo.ACAO.name())) {
			dispararNotificaoParaAcao(parametroAtivo);
		} else if (parametroAtivo.getTipoAtivo().equals(TipoAtivo.CRIPTOMOEDA.name())) {
			dispararNotificaoParaCriptomoeda(parametroAtivo);
		}
	}

	private void dispararNotificaoParaAcao(ParametroAtivo parametroAtivo) {
		try {
			Stock acao = acaoService.consultarAcao(parametroAtivo.getSimbolo());
			if (compararValorParametroComPreco(parametroAtivo.getValor(), acao.getQuote().getPrice().floatValue())) {
				dispararNotificacao(acao.getSymbol(), acao.getQuote().getPrice().toString(), parametroAtivo);
				
			} else {
				System.out.println("Parâmetro: "+parametroAtivo.getValor().toString()+"\n"+ "Preço atual: "+acao.getQuote().getPrice().toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void dispararNotificaoParaCriptomoeda(ParametroAtivo parametroAtivo) {
		Criptomoeda criptomoeda = null;
		try {
			criptomoeda = criptomoedaService.consultar(parametroAtivo.getSimbolo());
		} catch (ErroConsultaApiCriptomoedaException e) {
			System.out.println("Erro: " + e.getMessage());
			return;
		}
		if (compararValorParametroComPreco(parametroAtivo.getValor(), criptomoeda.getPreco())) {
			dispararNotificacao(criptomoeda.getNome(), criptomoeda.getPreco().toString(), parametroAtivo);
			try {
				parametroAtivoService.remover(parametroAtivo.getId());
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void dispararNotificacao(String simboloAtivo, String precoAtivo, ParametroAtivo parametroAtivo) {
		try {
			fcmService.sendMessageToToken(new PushNotificationRequest(simboloAtivo, String.format(CORPO_MENSAGEM, parametroAtivo.getTipoAtivo(), precoAtivo, parametroAtivo.getValor().toString()), null, parametroAtivo.getToken()));
			parametroAtivoService.remover(parametroAtivo.getId());
		} catch (InterruptedException | ExecutionException | NotFoundException e) {
			e.printStackTrace();
		}
	}

	private boolean compararValorParametroComPreco(Float valorParametroAtivo, Float precoAtivo) {
		return valorParametroAtivo >= precoAtivo;
	}


}
