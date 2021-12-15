package br.bti.pds.service;

import br.bti.pds.model.Transacao;


import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import yahoofinance.Stock;

import java.util.ArrayList;

public class TransacaoService {

    @Autowired
    private AcaoService acaoService;

    @Autowired
    private CriptomoedaService criptomoedaService;

    ArrayList<Transacao> listaTransacoes;

    public void addTransacao(Transacao transacao) {
        Transacao inserir = new Transacao(transacao.getSimbolo(), transacao.getTipoAtivo(), transacao.getTipoOperacao(), transacao.getValorCompra());


        try {
            Stock acao = acaoService.consultarAcao(transacao.getSimbolo());
            inserir.setValorAtual(acao.getQuote().getPrice().floatValue());

        }
        catch (Exception e){
            e.printStackTrace();
        }


        listaTransacoes.add(inserir);
    }


    public JSONArray recuperarTransacoes(){

        //preciso pegar o valor atual do ativo para comparar

        JSONArray jsonArray = new JSONArray();



        ArrayList<String> jaVerificados = new ArrayList<>();
        for (int i = 0; i <listaTransacoes.size();i++){
            JSONObject jsonObject = new JSONObject();

            if( !(jaVerificados.contains(listaTransacoes.get(i).getSimbolo())) ){
                jaVerificados.add(listaTransacoes.get(i).getSimbolo());

                float somatorio = 0;
                for(int j=i;j<listaTransacoes.size();j++){
                    if(listaTransacoes.get(j).getSimbolo().equals(listaTransacoes.get(i).getSimbolo())){
                        somatorio += listaTransacoes.get(j).getValorCompra();
                    }
                }
                // após esse for, comparar com o valor da ação
                if(somatorio > 0){
                    try {
                        Stock acao = acaoService.consultarAcao(listaTransacoes.get(i).getSimbolo());

                        jsonObject.put("ACAO", listaTransacoes.get(i).getSimbolo());

                        if(acao.getQuote().getPrice().floatValue() > listaTransacoes.get(i).getValorAtual()){ // acao desvalorizou
                           jsonObject.put("valorizou", somatorio);
                        }
                        else { // acao valorizou
                            jsonObject.put("desvalorizou", somatorio);
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            jsonArray.put(jsonObject);

        }


        return jsonArray;

    }





}
