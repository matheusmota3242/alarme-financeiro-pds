package br.bti.pds.service.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.bti.pds.model.Transacao;
import br.bti.pds.repository.TransacaoRepository;

@Service
public class TransacaoFacade {

	@Autowired
	private TransacaoRepository repository;
	
	public Transacao salvar(Transacao transacao) {
		
		return repository.save(transacao);
		
	}
	
	public List<Transacao> recebrTodos() {
		return repository.findAll();
	}
	
	public List<Transacao> receberTodosPorSimbolo(String simbolo) {
		return repository.findAllBySimbolo(simbolo);
	}
	
	public void remover(Integer id) {
		repository.deleteById(id);
	}
	
	public void removerTodos() {
		repository.deleteAll();
	}
}
