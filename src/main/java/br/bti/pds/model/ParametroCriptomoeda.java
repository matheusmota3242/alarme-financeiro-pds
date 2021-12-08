package br.bti.pds.model;

import javax.persistence.Entity;

import org.springframework.lang.NonNull;

@Entity
public class ParametroCriptomoeda extends Parametro {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5997111845540353979L;
	
	@NonNull
	private String idCripto;

	public String getIdCripto() {
		return idCripto;
	}

	public void setIdCripto(String idCripto) {
		this.idCripto = idCripto;
	}
}
