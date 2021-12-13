package br.bti.pds.model;

public class Criptomoeda {

	private String nome;
	private Float preco;
	
	public Criptomoeda(String nome, String preco) {
		this.nome = nome;
		this.preco = Float.valueOf(preco);
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Float getPreco() {
		return preco;
	}
	
	public void setPreco(Float preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Criptomoeda [nome=" + nome + ", preco=" + preco.toString() + "]";
	}
}
