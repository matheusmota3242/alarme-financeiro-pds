package br.bti.pds.controller;

import java.util.List;

import javax.naming.NotContextException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.bti.pds.exception.AcaoInvalidaException;
import br.bti.pds.model.ParametroAcao;
import br.bti.pds.model.ParametroAcaoMediaMovelSimples;
import br.bti.pds.service.ParametroAcaoService;
import javassist.NotFoundException;
import yahoofinance.Stock;

@RestController
@RequestMapping("parametro-acao")
public class ParametroAcaoController {

	@Autowired
	private ParametroAcaoService service;
	
	@GetMapping("/recuperar")
	public List<ParametroAcao> recuperarTodos() {
		return service.recuperarTodos();
	}
	
	@RequestMapping(value="/salvar", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
	public ResponseEntity<Object> salvarNovoParametroAcao(@RequestBody ParametroAcao parametroAcao) throws AcaoInvalidaException {
		try {
			service.salvar(parametroAcao);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (AcaoInvalidaException e) {
			return new ResponseEntity<>(e.getCausa(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/media-movel-simples/salvar")
	public Stock salvarParametroAcaoMediaMovelSimples(@RequestBody ParametroAcaoMediaMovelSimples parametroAcao) {
		return service.receberAcoesEmIntervalo(parametroAcao);
	}
	
	@DeleteMapping("/{id}/remover")
	public ResponseEntity<Object> remover(@PathVariable Integer id) {
		try {
			service.remover(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>("Causa: " + e.getMessage(), HttpStatus.BAD_REQUEST); 
		}
		
	}
	
	@DeleteMapping("/remover")
	public void removerTodos() {
		service.removerTodos();
	}

}
