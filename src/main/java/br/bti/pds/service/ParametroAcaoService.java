package br.bti.pds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.bti.pds.exception.AcaoInvalidaException;
import br.bti.pds.model.ParametroAcao;
import br.bti.pds.model.ParametroAcaoMediaMovelSimples;
import br.bti.pds.repository.ParametroAcaoRepository;
import javassist.NotFoundException;
import yahoofinance.Stock;


@Service
public class ParametroAcaoService {

	@Autowired
	private ParametroAcaoRepository repository;
	
	@Autowired
	private YahooService yahooService;
	
	public ParametroAcao salvar(ParametroAcao parametroAcao) throws AcaoInvalidaException {
		if (yahooService.validarExistencia(parametroAcao.getTiquete()))
			return repository.save(parametroAcao);
		else
			throw new AcaoInvalidaException("Essa ação não existe.");
	}
	
	public void salvarParametroAcaoMediaMovelSimples(ParametroAcaoMediaMovelSimples parametroAcao) {
		if (yahooService.validarExistencia(parametroAcao.getTiquete())) {
			
		}
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
	
	public Stock receberAcoesEmIntervalo(ParametroAcaoMediaMovelSimples parametroAcao) {
		return yahooService.receberAcoesEmIntervalo(parametroAcao);
	}
	
}
