package br.bti.pds.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.bti.pds.enumeration.TipoAtivo;
import br.bti.pds.exception.ErroConsultaApiCriptomoedaException;
import br.bti.pds.model.Criptomoeda;
import br.bti.pds.model.ResultadoTransacoes;
import br.bti.pds.model.Transacao;
import br.bti.pds.service.facade.TransacaoFacade;
import yahoofinance.Stock;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoFacade facade;
	
	@Autowired
	private AcaoService acaoService;
	
	@Autowired
	private CriptomoedaService criptomoedaService;
	
    public Transacao salvar(Transacao transacao) throws Exception {
    	boolean resultado = false;
    	transacao.setSimbolo(transacao.getSimbolo().toLowerCase());
    	if (transacao.getTipoOperacao().equals("VENDA")) {
    		transacao.setValorTransacao(transacao.getValorTransacao()*(-1));
    	}
    	if (transacao.getTipoAtivo().equals(TipoAtivo.ACAO.name())) {
    		Stock stock = acaoService.consultarAcao(transacao.getSimbolo());
    		if (stock != null) {
    			transacao.setValorAtual(stock.getQuote().getPrice().floatValue());
        		resultado = true;
    			return facade.salvar(transacao);
        	}
    	} else if (transacao.getTipoAtivo().equals(TipoAtivo.CRIPTOMOEDA.name())) {
    		Criptomoeda criptomoeda = criptomoedaService.consultar(transacao.getSimbolo());
    		transacao.setValorAtual(criptomoeda.getPreco());
    		if (criptomoeda != null) {
    			
    			resultado = true;
    		}
    		
    	}
    	
    	if (!resultado) {
    		throw new Exception("Erro ao persistir transação.");
    	}
    	
    	return facade.salvar(transacao);
    		
    }
    
    public List<Transacao> receberTodos() {
    	return facade.recebrTodos();
    }
    
    public List<Transacao> receberTodosPorSimbolo(String simbolo) {
    	return facade.receberTodosPorSimbolo(simbolo);
    }
    
    public List<ResultadoTransacoes> receberResultados() throws IOException, ErroConsultaApiCriptomoedaException {
    	List<Transacao> transacoes = facade.recebrTodos();
    	Map<String, List<Transacao>> map = transacoes.stream().collect(Collectors.groupingBy(t -> t.getSimbolo()));
    	List<String> simbolos = new ArrayList<String>();
    	List<ResultadoTransacoes> resultados = new ArrayList<ResultadoTransacoes>();
    	for (Map.Entry<String, List<Transacao>> entry : map.entrySet()) {
    		
    		ResultadoTransacoes resultado = new ResultadoTransacoes();
    		resultado.setSimbolo(entry.getKey());
    		resultado.setTotalGasto(entry.getValue().stream().mapToDouble(t -> (
    				t.getQuantidadeTransacao()*t.getValorTransacao())).sum());
    		
    		Transacao primeiraTransacao = entry.getValue().get(0);
    		Double valorAtual = 0.0;
    		if (primeiraTransacao.getTipoAtivo().equals(TipoAtivo.ACAO.name())) {
    			Stock acao = acaoService.consultarAcao(entry.getKey());
    			valorAtual = acao.getQuote().getPrice().doubleValue();
    		} else if (primeiraTransacao.getTipoAtivo().equals(TipoAtivo.CRIPTOMOEDA.name())) {
    			Criptomoeda criptomoeda = criptomoedaService.consultar(primeiraTransacao.getSimbolo());
    			valorAtual = criptomoeda.getPreco().doubleValue();
    		}
    		
    		resultado.setValorTotalAtual(entry.getValue().stream().mapToDouble(t -> (t.getQuantidadeTransacao())).sum());
    		resultado.setValorTotalAtual(resultado.getValorTotalAtual()*valorAtual);
    		resultado.setResultadoBruto(resultado.getValorTotalAtual() - resultado.getTotalGasto());
    		resultado.setResultadoPercentual(100*(resultado.getValorTotalAtual()/resultado.getTotalGasto()));
    		resultados.add(resultado);
    	}
    	return resultados;
    	
    }
    
    public void remover(Integer id) {
    	facade.remover(id);
    }
    
    public void removerTodos() {
    	facade.removerTodos();
    }

}
