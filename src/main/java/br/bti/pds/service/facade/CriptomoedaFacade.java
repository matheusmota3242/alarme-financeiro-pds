package br.bti.pds.service.facade;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.bti.pds.exception.ErroConsultaApiCriptomoedaException;

@Service
public class CriptomoedaFacade {
	
	private static String COIN_API_URL = "https://api.coincap.io/v2/assets/bitcoin";
	
	public ResponseEntity<String> consultar(String idCripto) throws ErroConsultaApiCriptomoedaException {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.getForEntity(COIN_API_URL, String.class);
		} catch (RestClientException e) {
			throw new ErroConsultaApiCriptomoedaException("Limite de tentativas da API Coincap excedido");
		}
		
		return response;
	}
	

}
