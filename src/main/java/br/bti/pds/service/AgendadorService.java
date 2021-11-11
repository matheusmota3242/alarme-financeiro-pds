package br.bti.pds.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AgendadorService {

	@Scheduled(fixedDelay = 2000)
	public void agendarConsulta() {
		System.out.println("funcionando");
	}
}
