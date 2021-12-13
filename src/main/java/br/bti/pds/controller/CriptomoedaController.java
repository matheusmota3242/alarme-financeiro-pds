package br.bti.pds.controller;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.bti.pds.exception.ErroConsultaApiCriptomoedaException;
import br.bti.pds.model.ParametroCriptomoeda;
import br.bti.pds.service.CriptomoedaService;

@RestController
@RequestMapping("criptomoeda")
public class CriptomoedaController {

	@Autowired
	CriptomoedaService service;
	
	@GetMapping("{idCripto}")
	public ResponseEntity<Object> consultar(@PathParam(value = "idCripto") String idCripto) {
		try {
			return new ResponseEntity<>(service.consultar(idCripto), HttpStatus.OK);
		} catch (ErroConsultaApiCriptomoedaException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.TOO_MANY_REQUESTS);
		}
	}
	
	@PostMapping("salvar")
	public ParametroCriptomoeda salvarParametroCriptoMoeda(@RequestBody ParametroCriptomoeda parametroCriptomoeda) {
		return service.salvar(parametroCriptomoeda);
	}
	
	
}
