package br.bti.pds.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.io.Serializable;


@Entity
public class Transacao implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4014041047218061868L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@NotEmpty(message = "O campo simbolo nao pode ser nulo")
	private String simbolo;

	@NotEmpty(message = "O campo tipoAtivo nao pode ser nulo")
	private String tipoAtivo;

	@NotEmpty(message = "O campo tipooperacao nao pode ser nulo")
	private String tipoOperacao;

	@NotNull(message = "O campo valorCompra nao pode ser nulo")
	private float valorTransacao;
	
	@NotNull(message = "O campo quantidadeTransacao nao pode ser nulo")
	private Integer quantidadeTransacao;

	private float valorAtual;


//
//	public Transacao(String simbolo, String tipoAtivo, String tipoperacao, float valorCompra) {
//		this.simbolo = simbolo;
//		this.tipoAtivo = tipoAtivo;
//		this.tipoOperacao = tipoperacao;
//		this.valorCompra = valorCompra;
//	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidadeTransacao() {
		return quantidadeTransacao;
	}
	
	public void setQuantidadeTransacao(Integer quantidadeTransacao) {
		this.quantidadeTransacao = quantidadeTransacao;
	}
	public void setSimbolo(String simbolo) { this.simbolo = simbolo; }
	public void setTipoAtivo(String tipoAtivo) { this.tipoAtivo = tipoAtivo; }
	public void setTipoOperacao(String tipoOperacao) { this.tipoOperacao = tipoOperacao; }
	public void setValorTransacao(float valorTransacao) { this.valorTransacao = valorTransacao; }
	public void setValorAtual(float valorAtual) { this.valorAtual = valorAtual; }

	public String getSimbolo() { return simbolo; }	
	public String getTipoAtivo() { return tipoAtivo; }
	public String getTipoOperacao() { return tipoOperacao; }
	public float getValorTransacao() { return valorTransacao; }
	public float getValorAtual() { return valorAtual; }

}
