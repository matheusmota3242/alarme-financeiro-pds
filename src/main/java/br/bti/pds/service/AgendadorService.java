package br.bti.pds.service;

import java.io.IOException;
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
	
	@Scheduled(fixedDelay = 7000)
	public void agendarConsulta() {
		List<ParametroAcao> parametrosAcao = service.recuperarTodos();
		parametrosAcao.forEach(parametroAcao -> {
			try {
				Stock acao = YahooFinance.get(parametroAcao.getTiquete());
				fcmService.sendMessageToToken(new PushNotificationRequest(acao.getName(), null, null, null));
				service.remover(parametroAcao.getId());
			} catch (IOException | NotFoundException | InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
	}
}
