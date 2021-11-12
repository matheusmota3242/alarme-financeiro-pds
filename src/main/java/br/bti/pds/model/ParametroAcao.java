package br.bti.pds.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class ParametroAcao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1336149664628001668L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@NonNull
	private String tiquete;
	@NonNull
	private Float valor;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTiquete() {
		return tiquete;
	}
	public void setTiquete(String tiquete) {
		this.tiquete = tiquete;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		return "ParametroAcao [tiquete=" + tiquete + ", valor=" + valor + "]";
	}
	
	
}
