package br.bti.pds.service;

import org.springframework.scheduling.annotation.Scheduled;

public abstract class AgendadorService implements IAgendadorService {

	
	@Scheduled(fixedDelayString = "${fixedDelayInMs}")
	public void agendarConsulta() {
		aplicarLogica();
	}
	

}
