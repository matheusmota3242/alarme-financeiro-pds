package br.bti.pds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.bti.pds.model.ParametroAcao;
import br.bti.pds.repository.ParametroAcaoRepository;


@Service
public class ParametroAcaoService {

	@Autowired
	private ParametroAcaoRepository repository;
	
	public ParametroAcao salvar(ParametroAcao parametroAcao) {
		return repository.save(parametroAcao);
		
	}
}
