package br.bti.pds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.bti.pds.exception.AcaoInvalidaException;
import br.bti.pds.model.ParametroAtivo;
import br.bti.pds.repository.ParametroAtivoRepository;
import br.bti.pds.service.facade.ParametroAtivoFacade;
import javassist.NotFoundException;
import yahoofinance.Stock;


@Service
public class ParametroAtivoService {
	
	@Autowired
	private ParametroAtivoFacade facade;
	
	public ParametroAtivo salvar(ParametroAtivo parametroAtivo) throws AcaoInvalidaException {
		if (facade.validarExistenciaAtivo(parametroAtivo))
			return facade.salvar(parametroAtivo);
		else
			throw new AcaoInvalidaException("Essa ação não existe.");
	}
	
	public void remover(Integer id) throws NotFoundException {
		facade.remover(id);
	}
	
	public void removerTodos() {
		facade.removerTodos();;
	}
	
	public List<ParametroAtivo> recuperarTodos() {
		return facade.recuperarTodos();
	}
	
	
	
}
