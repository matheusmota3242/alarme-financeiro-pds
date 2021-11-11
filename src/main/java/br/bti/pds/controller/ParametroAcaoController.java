package br.bti.pds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.bti.pds.model.ParametroAcao;
import br.bti.pds.service.ParametroAcaoService;
import yahoofinance.Stock;

@RestController
public class ParametroAcaoController {

	@Autowired
	private ParametroAcaoService service;
	
	@PostMapping("salvar-parametro-acao")
	public ParametroAcao salvarNovoParametroAcao(@RequestBody ParametroAcao parametroAcao) {
		return service.salvar(parametroAcao);
	}
}
