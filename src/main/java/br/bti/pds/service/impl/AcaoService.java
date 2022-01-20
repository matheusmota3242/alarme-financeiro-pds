package br.bti.pds.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class AcaoService {

	public Stock consultarAcao(String simbolo) throws IOException {
		Stock stock = null;
		try {
			stock = YahooFinance.get(simbolo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stock;
	}
	
}
