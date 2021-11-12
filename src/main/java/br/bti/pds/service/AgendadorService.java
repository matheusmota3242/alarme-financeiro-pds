package br.bti.pds.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.bti.pds.model.ParametroAcao;
import javassist.NotFoundException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class AgendadorService {

	@Autowired
	private ParametroAcaoService service;
	
	@Scheduled(fixedDelay = 7000)
	public void agendarConsulta() {
		List<ParametroAcao> parametrosAcao = service.recuperarTodos();
		parametrosAcao.forEach(parametroAcao -> {
			try {
				Stock acao = YahooFinance.get(parametroAcao.getTiquete());
				System.out.println(String.format("Tíquete: %s; Valor: %s", acao.getName(), acao.getQuote().getPrice()));
				service.remover(parametroAcao.getId());
			} catch (IOException | NotFoundException e) {
				e.printStackTrace();
			}
		});
	}
}
