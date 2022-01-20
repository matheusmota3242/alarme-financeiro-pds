package br.bti.pds.controller;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.PathParam;

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

import br.bti.pds.exception.ErroConsultaApiCriptomoedaException;
import br.bti.pds.model.ResultadoTransacoes;
import br.bti.pds.model.Transacao;
import br.bti.pds.service.impl.TransacaoService;

@RestController
@RequestMapping("transacao")
public class TransacaoController {

	@Autowired
    private TransacaoService service;

    @RequestMapping(value="/salvar", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public ResponseEntity<Object> salvar(@Validated @RequestBody Transacao transacao){
        try {
            
            return new ResponseEntity<>(service.salvar(transacao), HttpStatus.CREATED);
        }
        catch (Exception e){
        	return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping
    public ResponseEntity<Object> receberTodos() {
    	try {
			return new ResponseEntity<>(service.receberTodos(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);		}
    }
    
    @GetMapping("{simbolo}")
    public List<Transacao> receberTodosPorSimbolo(@PathVariable String simbolo) {
    	return service.receberTodosPorSimbolo(simbolo);
    }

    @GetMapping("resultados")
    public ResponseEntity<Object> receberResultados() {
    	Exception exception = null;
    	try {
			return new ResponseEntity<>(service.receberResultados(), HttpStatus.OK);
		} catch (IOException e) {
			exception = e;
			e.printStackTrace();
			
		} catch (ErroConsultaApiCriptomoedaException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
    	return new ResponseEntity<>("Erro inesperado.",HttpStatus.BAD_REQUEST);
    }
    
    @DeleteMapping("{id}/remover")
    public void remover(@PathVariable Integer id) {
    	service.remover(id);
    }
    
    @DeleteMapping("remover")
    public void removerTodos() {
    	service.removerTodos();
    }
    

}
