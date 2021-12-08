package br.bti.pds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.bti.pds.model.ParametroCriptomoeda;
import br.bti.pds.service.CriptomoedaService;

@RestController
@RequestMapping("parametro-criptomoeda")
public class CriptomoedaController {

	@Autowired
	CriptomoedaService service;
	
	@PostMapping("salvar")
	public ParametroCriptomoeda salvarParametroCriptoMoeda(@RequestBody ParametroCriptomoeda parametroCriptomoeda) {
		return service.salvar(parametroCriptomoeda);
	}
}
