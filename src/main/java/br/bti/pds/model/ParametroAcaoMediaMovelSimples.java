package br.bti.pds.model;

import java.io.Serializable;

import javax.annotation.Nonnull;

public class ParametroAcaoMediaMovelSimples extends ParametroAcao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1843465802066402220L;
	
	@Nonnull
	private Integer intervalo;

	public Integer getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Integer intervalo) {
		this.intervalo = intervalo;
	}

}
