package br.bti.pds.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.bti.pds.exception.AcaoInvalidaException;
import br.bti.pds.model.ParametroAtivo;
import br.bti.pds.model.PushNotificationRequest;
import javassist.NotFoundException;
import yahoofinance.Stock;

@Service
public class AgendadorService {
	
	@Autowired
	private AcaoService acaoService;

	@Autowired
	private ParametroAtivoService parametroAtivoService;
	
	@Autowired
	private FCMService fcmService;
	
	private static String CORPO_MENSAGEM = "Ativo: %s\nPreço atual: %s\nParâmetro: %s";
	
	@Scheduled(fixedDelay = 15000)
	public void agendarConsulta() {
		List<ParametroAtivo> parametrosAcao = parametroAtivoService.recuperarTodos();
		parametrosAcao.forEach(ParametroAtivo -> {
			try {
				aplicarLogicaParaDisparoDePushNotification(ParametroAtivo);
			} catch (AcaoInvalidaException e) {
				System.out.println(e.getCausa());
			}
		});
	
	}
	
	private void aplicarLogicaParaDisparoDePushNotification(ParametroAtivo parametroAtivo) throws AcaoInvalidaException {
		try {
			Stock acao = acaoService.consultarAcao(parametroAtivo.getSimbolo());
			if (compararValorParametroComPrecoAcao(parametroAtivo.getValor(), acao.getQuote().getPrice())) {
				fcmService.sendMessageToToken(new PushNotificationRequest(acao.getSymbol(), String.format(CORPO_MENSAGEM, acao.getQuote().getPrice().toString(), parametroAtivo.getValor().toString()), null, parametroAtivo.getToken()));
				parametroAtivoService.remover(parametroAtivo.getId());
			} else {
				System.out.println("Parâmetro: "+parametroAtivo.getValor().toString()+"\n"+ "Preço atual: "+acao.getQuote().getPrice().toString());
			}
		} catch (NotFoundException | InterruptedException | ExecutionException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private boolean compararValorParametroComPrecoAcao(Float valorParametroAtivo, BigDecimal precoAcao) {
		return valorParametroAtivo >= Float.parseFloat(precoAcao.toString());
	}
}
