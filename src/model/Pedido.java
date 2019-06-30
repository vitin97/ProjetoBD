package model;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
	private int id;
	private Date data_pedido;
	private int idFun;
	private int idForma;
	private double valor;
	private String cpf_cli;
	private ArrayList <int[]> produtos = new ArrayList<int[]>();
	
	public Pedido (int id,Date data,int idFun,int idForma,double valor, String cpf_cli) {
		this.id=id;
		this.data_pedido=data;
		this.idFun=idFun;
		this.idForma=idForma;
		this.valor=valor;
		this.cpf_cli=cpf_cli;
	}
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData_pedido() {
		return data_pedido;
	}
	public void setData_pedido(Date data_pedido) {
		this.data_pedido = data_pedido;
	}
	public int getIdFun() {
		return idFun;
	}
	public void setIdFun(int idFun) {
		this.idFun = idFun;
	}
	public int getIdForma() {
		return idForma;
	}
	public void setIdForma(int idForma) {
		this.idForma = idForma;
	}
	public String getCpf_cli() {
		return cpf_cli;
	}
	public void setCpf_cli(String cpf_cli) {
		this.cpf_cli = cpf_cli;
	}
	public ArrayList<int[]> getProdutos() {
		return produtos;
	}
	public void setProdutos(ArrayList<int[]> produtos) {
		this.produtos = produtos;
	}
	
	
	
}
