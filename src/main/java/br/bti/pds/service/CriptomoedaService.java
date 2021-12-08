package br.bti.pds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.bti.pds.model.ParametroCriptomoeda;
import br.bti.pds.service.facade.CriptomoedaFacade;

@Service
public class CriptomoedaService {

	@Autowired
	CriptomoedaFacade facade;
	
	public void consultar(ParametroCriptomoeda parametroCriptomoeda) {
		facade.consultar(parametroCriptomoeda.getIdCripto());
	}

	public ParametroCriptomoeda salvar(ParametroCriptomoeda parametroCriptomoeda) {
		return facade.salvar(parametroCriptomoeda);
	}
}
