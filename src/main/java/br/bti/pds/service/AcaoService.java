package br.bti.pds.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class AcaoService {

	public Stock consultarAcao(String simbolo) throws IOException {
		return YahooFinance.get(simbolo);
	}
	
}
