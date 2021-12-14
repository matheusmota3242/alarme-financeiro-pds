package br.bti.pds.service.facade;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.bti.pds.enumeration.TipoAtivo;
import br.bti.pds.exception.AcaoInvalidaException;
import br.bti.pds.model.ParametroAtivo;
import br.bti.pds.repository.ParametroAtivoRepository;
import javassist.NotFoundException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class ParametroAtivoFacade {
	
	@Autowired
	private ParametroAtivoRepository repository;
	
	private static String COIN_API_URL = "https://api.coincap.io/v2/assets/bitcoin";
	
	public Stock consultarAcao(String simbolo) throws AcaoInvalidaException {
		Stock stock = null;
		try {
			stock = YahooFinance.get(simbolo);
		} catch (IOException e) {
			throw new AcaoInvalidaException(simbolo);
		}
		return stock;
	}
	
	public ResponseEntity<String> consultarCriptomoeda(String idCripto) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(COIN_API_URL, String.class);
		return response;
	}
	
	public boolean validarExistenciaAtivo(ParametroAtivo parametroAtivo) throws AcaoInvalidaException {
		boolean ativoExiste = false;
		if (parametroAtivo.getTipoAtivo().equals(TipoAtivo.ACAO.name())) {
			ativoExiste = validarExistenciaAcao(parametroAtivo.getSimbolo());
		} else if (parametroAtivo.getTipoAtivo().equals(TipoAtivo.CRIPTOMOEDA.name())) {
			ativoExiste = validarExistenciaCriptomoeda(parametroAtivo.getSimbolo());
		}
		return ativoExiste;
	}
	
	public ParametroAtivo salvar(ParametroAtivo parametroAtivo) {
		return repository.save(parametroAtivo);
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
	
	public List<ParametroAtivo> recuperarTodos() {
		return repository.findAll();
	}
	
	private boolean validarExistenciaCriptomoeda(String simbolo) {
		ResponseEntity<String> response = consultarCriptomoeda(simbolo);
		if (response.getStatusCode() == HttpStatus.OK) {
			return true;
		}
		return false;
	}

	public boolean validarExistenciaAcao(String simbolo) throws AcaoInvalidaException {
		Stock acao = consultarAcao(simbolo);
		return null != acao && null != acao.getCurrency();
	}
}
