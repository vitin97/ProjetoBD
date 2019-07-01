package model;

public class Produto {
	private int id;
	private int tamanho;
	private double preco;
	private double precoC;
	private String marca;
	private String modelo;
	
	public Produto(int id, int tamanho, double preco,double precoc,String marca, String modelo) {
		this.id=id;
		this.tamanho =tamanho;
		this.preco =preco;
		this.precoC = precoc;
		this.marca = marca;
		this.modelo=modelo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public double getPrecoC() {
		return precoC;
	}
	public void setPrecoC(double precoC) {
		this.precoC = precoC;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	
}
