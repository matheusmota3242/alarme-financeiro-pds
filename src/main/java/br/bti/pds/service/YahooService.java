package br.bti.pds.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import br.bti.pds.model.ParametroAcaoMediaMovelSimples;
import br.bti.pds.utils.helper.DataHelper;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class YahooService {

	public Stock receberAcao(String tiquete) {
		Stock acao = null;
		try {
			acao = YahooFinance.get(tiquete);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return acao;
	}
	
	public Stock receberAcoesEmIntervalo(ParametroAcaoMediaMovelSimples parametroAcao) {
		Stock stock = null;
		try {
			stock = YahooFinance.get(parametroAcao.getTiquete(), DataHelper.receberCalendarAtual(), DataHelper.receberPrimeiroDia(parametroAcao.getIntervalo()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return stock;
	}
	
	public boolean validarExistencia(String tiquete) {
		Stock acao = receberAcao(tiquete);
		return null != acao;
	}
}
