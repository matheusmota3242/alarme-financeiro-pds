package br.bti.pds;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class FinanceiroService {

	public ResponseEntity<Stock> receberValor(String tiquete) {
		Stock stock = null;
		try {
			stock = YahooFinance.get(tiquete);
			return new ResponseEntity<Stock>(stock, HttpStatus.CREATED);
		} catch (IOException e) {
			System.out.println(e.toString());
			return new ResponseEntity<Stock>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
