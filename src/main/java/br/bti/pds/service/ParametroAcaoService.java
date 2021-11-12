package br.bti.pds.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.bti.pds.model.ParametroAcao;
import br.bti.pds.repository.ParametroAcaoRepository;
import javassist.NotFoundException;


@Service
public class ParametroAcaoService {

	@Autowired
	private ParametroAcaoRepository repository;
	
	public ParametroAcao salvar(ParametroAcao parametroAcao) {
		return repository.save(parametroAcao);	
	}
	
	public void remover(Integer id) throws NotFoundException {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new NotFoundException("registro não encontrado.");
		}
		
	}
	
	public void removerTodos() {
		repository.deleteAll();
	}
	
	public List<ParametroAcao> recuperarTodos() {
		return repository.findAll();
	}
	
}
