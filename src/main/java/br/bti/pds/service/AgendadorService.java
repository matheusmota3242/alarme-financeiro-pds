package br.bti.pds.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.bti.pds.model.ParametroAcao;
import br.bti.pds.model.PushNotificationRequest;
import javassist.NotFoundException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class AgendadorService {

	@Autowired
	private ParametroAcaoService service;
	
	@Autowired
	private FCMService fcmService;
	
	private static String CORPO_MENSAGEM = "Preço atual: %s\nParâmetro: %s";
	
	@Scheduled(fixedDelay = 15000)
	public void agendarConsulta() {
		List<ParametroAcao> parametrosAcao = service.recuperarTodos();
		parametrosAcao.forEach(parametroAcao -> {
			aplicarLogicaParaDisparoDePushNotification(parametroAcao);
		});
	}
	
	private void aplicarLogicaParaDisparoDePushNotification(ParametroAcao parametroAcao) {
		try {
			Stock acao = YahooFinance.get(parametroAcao.getTiquete());
			if (compararValorParametroComPrecoAcao(parametroAcao.getValor(), acao.getQuote().getPrice())) {
				fcmService.sendMessageToToken(new PushNotificationRequest(acao.getSymbol(), String.format(CORPO_MENSAGEM, acao.getQuote().getPrice().toString(), parametroAcao.getValor().toString()), null, parametroAcao.getToken()));
				service.remover(parametroAcao.getId());
			} else {
				System.out.println("Parâmetro: "+parametroAcao.getValor().toString()+"\n"+ "Preço atual: "+acao.getQuote().getPrice().toString());
			}
		} catch (IOException | NotFoundException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	private boolean compararValorParametroComPrecoAcao(Float valorParametroAcao, BigDecimal precoAcao) {
		return valorParametroAcao >= Float.parseFloat(precoAcao.toString());
	}
}
