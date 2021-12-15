package br.bti.pds.controller;

import br.bti.pds.exception.AcaoInvalidaException;
import br.bti.pds.model.ParametroAtivo;
import br.bti.pds.model.Transacao;
import br.bti.pds.service.ParametroAtivoService;
import br.bti.pds.service.TransacaoService;
import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.UnexpectedTypeException;
import java.util.List;

// private String simbolo, tipoAtivo, tipoOperacao;
// private float valorCompra, valorAtual;



@RestController
@RequestMapping("transacao")
public class TransacaoController {

    private TransacaoService service;

    @GetMapping("/recuperar")
    public JSONArray recuperar() {
        return service.recuperarTransacoes();
    }

    @RequestMapping(value="/salvar", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public void salvar(@Validated @RequestBody Transacao transacao){
        try {
            service.addTransacao(transacao);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }



}
