package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bd.Conexao;
import model.Pedido;
import model.Produto;


public class ProdutoDao implements IProdutoDao {

	private Conexao con = Conexao.getInstancia();

	public boolean cadastrarProduto(Produto p) {

		int res = con.executarSql("insert into produto(id,tamanho,preco,preco_custo,marca,modelo) values (" + p.getId()+ "," + p.getTamanho() + "," + p.getPreco() + "," + p.getPrecoC() +",'"+p.getMarca()+ "','"+p.getModelo()+"')");    
		
		if (res > 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean excluirProduto(Produto p) {

		int res = con.executarSql("delete from produto where id = '" + p.getId() + "' ");
		if (res > 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean atualizarProduto(Produto p) {

		int res = con.executarSql("update pedido set tamanho="+p.getTamanho()+",preco="+p.getPreco()+",preco_custo="+p.getPrecoC()+",marca='"+p.getMarca()+"',modelo='"+p.getModelo()+"'  where id = " + p.getId());
		if (res > 0) {
			return true;
		} else {
			return false;
		}

	}

	public List<Produto> recuperar() {

		ResultSet rs = con.executarBusca("select * from produto");
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));
				int tamanho = Integer.parseInt(rs.getString("tamanho"));
				double preco = Double.parseDouble(rs.getString("preco"));
				double precoc =Double.parseDouble( rs.getString("preco_custo"));
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				Produto p = new Produto(id,tamanho,preco,precoc,marca,modelo);
				
				produtos.add(p);
			}
		
			return produtos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}


	
}
