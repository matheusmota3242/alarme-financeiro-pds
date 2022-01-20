package br.bti.pds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.bti.pds.exception.ErroConsultaApiCriptomoedaException;
import br.bti.pds.model.Criptomoeda;
import br.bti.pds.service.facade.CriptomoedaFacade;

@Service
public class CriptomoedaService {

	@Autowired
	CriptomoedaFacade facade;
	
	public Criptomoeda consultar(String idCripto) throws ErroConsultaApiCriptomoedaException {
		ResponseEntity<String> response = facade.consultar(idCripto);
		return extrairObjeto(response.getBody());
	}


	private Criptomoeda extrairObjeto(String body) {
		Criptomoeda criptomoeda = null;
		try {
			JsonNode node = new ObjectMapper().readTree(body);
			node = node.get("data");
			criptomoeda = new Criptomoeda(node.get("name").textValue(), node.get("priceUsd").textValue());
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return criptomoeda;
	}
}
