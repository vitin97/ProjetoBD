
package dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bd.Conexao;
import model.Pedido;

public class PedidoDao implements IPedidoDao {
	private Conexao con = Conexao.getInstancia();

	public boolean excluirPedido(Pedido p) {
		int res = con.executarSql("delete from pedido where id = '" + p.getId() + "' ");
		if (res > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean atualizarPedido(Pedido p) {
		int res = con.executarSql("update pedido set idfun= " + p.getIdFun() + ",idforma=" + p.getIdForma() + ",valor=" + p.getValor() +"  where id = '" + p.getId() + "' "); 
		if (res > 0) { 
			return true; 
		} else { 
			return false; 
		}
	}

	public List<Pedido> recuperar() {
		ResultSet rs = con.executarBusca("select * from pedido");
		List<Pedido> pedidos = new ArrayList<Pedido>();
		
		try {
			SimpleDateFormat formato = new SimpleDateFormat( "yyyy/MM/dd" ); 
			while (rs.next()) {
				String id = rs.getString("id");; 
				String data = rs.getString("data_pedido");; 
				String idFun = rs.getString("id_fun");; 
				String idForma = rs.getString("id_forma");; 
				String valor = rs.getString("valor");; 
				String cpf_cli = rs.getString("id_cli");; 
				Pedido ped = new Pedido(Integer.parseInt(id), formato.parse(data), Integer.parseInt(idFun), Integer.parseInt(idForma), Double.parseDouble(valor), cpf_cli); 
				pedidos.add(ped); 
			}
		
			return pedidos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	

	@Override
	public boolean cadastrarPedido(Pedido p) {
		int res = con.executarSql("insert into pedido(data_pedido,valor,id_fun,id_forma,id_cli)" 
				+ " values ('" + p.getData_pedido()+ "','" + p.getValor() + "','" + p.getIdFun() + "','" + p.getIdForma() +"','"+p.getCpf_cli()+ "')");
		
		for(ArrayList<int[]> produto :p.getProdutos()) {
			int res2 = con.executarSql("insert into pedido_produto(id_pedido,id_produto,quantidade)" 
			+ " values (" + p.getId() + "," + produto[0] + "," + produto[1] + ")");
			
		}
				if (res > 0 && res2 > 0) {
					return true;
				} else {
					return false;
				}
				
				
	}

	public String recuperarFuncionario(int id) {
		ResultSet rs = con.executarBusca("select nome from funcionario where id ="+id);
		try {
		String nome = rs.getString("nome");;
		return nome;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String recuperarForma(int id) {
		ResultSet rs = con.executarBusca("select tipo from forma_de_pagamento where id ="+id);
		try {
		String nome = rs.getString("tipo");;
		return nome;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String recuperarCliente(int id) {
		ResultSet rs = con.executarBusca("select nome from cliente where id ="+id);
		try {
		String nome = rs.getString("nome");;
		return nome;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	

}
