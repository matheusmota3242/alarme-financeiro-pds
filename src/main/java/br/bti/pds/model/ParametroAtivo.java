package br.bti.pds.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

@Entity
public class ParametroAtivo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1336149664628001668L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@NotEmpty(message = "O campo simbolo nao pode ser nulo")
	private String simbolo;
	
	@NotEmpty(message = "O campo tipoAtivo nao pode ser nulo")
	private String tipoAtivo;

	@NotNull(message = "O campo valor nao pode ser nulo")
	protected Float valor;
	
	@NotEmpty(message = "O campo token nao pode ser nulo")
	protected String token;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSimbolo() {
		return simbolo;
	}
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	
	public Float getValor() {
		return valor;
	}
	
	public void setValor(Float valor) {
		this.valor = valor;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getTipoAtivo() {
		return tipoAtivo;
	}
	
	public void setTipoAtivo(String tipoAtivo) {
		this.tipoAtivo = tipoAtivo;
	}
}
