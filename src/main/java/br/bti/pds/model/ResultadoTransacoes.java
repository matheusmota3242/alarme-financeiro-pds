package br.bti.pds.model;

import java.io.Serializable;

public class ResultadoTransacoes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4252734767986754158L;
	

	private String simbolo;
	private Double totalGasto;
	private Double valorTotalAtual;
	private Double resultadoBruto;
	private Double resultadoPercentual;
	
	public Double getResultadoBruto() {
		return resultadoBruto;
	}
	public void setResultadoBruto(Double resultadoBruto) {
		this.resultadoBruto = resultadoBruto;
	}
	public Double getResultadoPercentual() {
		return resultadoPercentual;
	}
	public void setResultadoPercentual(Double resultadoPercentual) {
		this.resultadoPercentual = resultadoPercentual;
	}

	public String getSimbolo() {
		return simbolo;
	}
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	public Double getTotalGasto() {
		return totalGasto;
	}
	public void setTotalGasto(Double totalGasto) {
		this.totalGasto = totalGasto;
	}
	public Double getValorTotalAtual() {
		return valorTotalAtual;
	}
	public void setValorTotalAtual(Double valorTotalAtual) {
		this.valorTotalAtual = valorTotalAtual;
	}
}
