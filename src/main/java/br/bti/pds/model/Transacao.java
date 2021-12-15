package br.bti.pds.model;
//String simbolo;
//Numeric valorCompra;
//Numeric valorAtual;
//String tipoAtivo;
//String tipoOperação -> compra ou venda;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Entity
public class Transacao implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotEmpty(message = "O campo simbolo nao pode ser nulo")
    private String simbolo;

    @NotEmpty(message = "O campo tipoAtivo nao pode ser nulo")
    private String tipoAtivo;

    @NotEmpty(message = "O campo tipooperacao nao pode ser nulo")
    private String tipoOperacao;


    @NotEmpty(message = "O campo valorCompra nao pode ser nulo")
    private float valorCompra;


    private float valorAtual;



    public Transacao(String simbolo, String tipoAtivo, String tipoperacao, float valorCompra) {
        this.simbolo = simbolo;
        this.tipoAtivo = tipoAtivo;
        this.tipoOperacao = tipoperacao;
        this.valorCompra = valorCompra;
    }

    public Transacao() {
        // ?
    }


    public void setSimbolo(String simbolo) { this.simbolo = simbolo; }
    public void setTipoAtivo(String tipoAtivo) { this.tipoAtivo = tipoAtivo; }
    public void setTipoOperacao(String tipoOperacao) { this.tipoOperacao = tipoOperacao; }
    public void setValorCompra(float valorCompra) { this.valorCompra = valorCompra; }
    public void setValorAtual(float valorAtual) { this.valorAtual = valorAtual; }

    public String getSimbolo() { return simbolo; }
    public String getTipoAtivo() { return tipoAtivo; }
    public String getTipoOperacao() { return tipoOperacao; }
    public float getValorCompra() { return valorCompra; }
    public float getValorAtual() { return valorAtual; }


    public void setId(int id) { this.id = id; }
    @Id
    public int getId() { return id; }
}
