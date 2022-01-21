package br.bti.pds.service;

import org.springframework.scheduling.annotation.Scheduled;

public abstract class AbstractAgendadorService implements IAgendadorService {

	@Scheduled(fixedDelay = 2000)
	public void agendar() {
		aplicarLogica();
	}
}
