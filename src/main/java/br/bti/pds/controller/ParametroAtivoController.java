package br.bti.pds.controller;

import java.util.List;

import javax.validation.UnexpectedTypeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.bti.pds.exception.AcaoInvalidaException;
import br.bti.pds.model.ParametroAtivo;
import br.bti.pds.service.ParametroAtivoService;
import javassist.NotFoundException;

@RestController
@RequestMapping("parametro-ativo")
public class ParametroAtivoController {

	@Autowired
	private ParametroAtivoService service;
	
	@GetMapping("/recuperar")
	public List<ParametroAtivo> recuperarTodos() {
		return service.recuperarTodos();
	}
	
	@RequestMapping(value="/salvar", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
	public ResponseEntity<Object> salvar(@Validated @RequestBody ParametroAtivo ParametroAtivo) throws AcaoInvalidaException {
		try {
			service.salvar(ParametroAtivo);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (AcaoInvalidaException  e) {
			return new ResponseEntity<>(e.getCausa(), HttpStatus.BAD_REQUEST);
		} catch (UnexpectedTypeException e) {
			return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
		}
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