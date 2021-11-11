package br.bti.pds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.bti.pds.FinanceiroService;
import br.bti.pds.model.ParametroAcao;
import yahoofinance.Stock;

@RestController
public class RestApiController {

	@Autowired
	FinanceiroService service;
	
	@PostMapping("salvar-parametro-acao")
	public ResponseEntity<Stock> salvarNovoParametroAcao(@RequestBody ParametroAcao parametroAcao) {
		
		return service.receberValor(parametroAcao.getTiquete());
	}
}
