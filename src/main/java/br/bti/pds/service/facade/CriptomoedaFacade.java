package br.bti.pds.service.facade;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.bti.pds.model.ParametroCriptomoeda;
import br.bti.pds.repository.CriptomoedaRepository;

@Service
public class CriptomoedaFacade {

	@Autowired
	CriptomoedaRepository repository;
	
	private static String COIN_API_URL = "https://api.coincap.io/v2/assets/bitcoin";
	
	public ResponseEntity<String> consultar(String idCripto) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(COIN_API_URL, String.class);
		return response;
	}
	
	public ParametroCriptomoeda salvar(ParametroCriptomoeda parametroCriptomoeda) {
		return repository.save(parametroCriptomoeda);
	}
}
