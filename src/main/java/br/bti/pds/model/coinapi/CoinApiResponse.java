package br.bti.pds.model.coinapi;

import java.io.Serializable;


public class CoinApiResponse implements Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 6970175259096889258L;
	
	private String name;
	private Float priceUsd;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Float getPriceUsd() {
		return priceUsd;
	}
	
	public void setPriceUsd(Float priceUsd) {
		this.priceUsd = priceUsd;
	}
	
}
