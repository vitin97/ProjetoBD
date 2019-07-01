package dao;

import java.util.List;

import model.Pedido;
import model.Produto;
import model.String;

public class ProdutoDao implements IProdutoDao {

	@Override
	public boolean cadastrarProduto(Produto p) {
<<<<<<< refs/remotes/origin/master
		
		return false;
=======
		int res = con.executarSql("insert into produto(id,tamanho,preco,preco_custo,marca,modelo)values (" + p.getId())+ "," + p.getTamanho() + "," + p.getPreco() + "," + p.getPrecoC() +",'"+p.getMarca()+ "','"+p.getModelo()+"')");   
		if (res > 0) {
			return true;
		} else {
			return false;
		}
>>>>>>> Correções
	}

	@Override
	public boolean excluirProduto(Produto p) {
<<<<<<< refs/remotes/origin/master
		
		return false;
=======
		int res = con.executarSql("delete from produto where id = '" + p.getId() + "' ");
		if (res > 0) {
			return true;
		} else {
			return false;
		}
>>>>>>> Correções
	}

	@Override
	public boolean atualizarProduto(Produto p) {
<<<<<<< refs/remotes/origin/master
		
		return false;
=======
		int res = con.executarSql("update pedido set   where id = '" + p.getId() + "' ");
		if (res > 0) {
			return true;
		} else {
			return false;
		}
>>>>>>> Correções
	}

	@Override
	public List<Produto> recuperar() {
<<<<<<< refs/remotes/origin/master
		
		return null;
=======
		ResultSet rs = con.executarBusca("select * from produto");
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			while (rs.next()) {
				int id = (int)rs.getString("id");
				int tamanho = (int)rs.getString("tamanho");
				double preco = rs.getString("preco");
				double precoc = rs.getString("preco_custo");
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
>>>>>>> Correções
	}

	@Override
	public List<Produto> recuperarPorFiltro(Produto p) {
		
		return null;
	}
	
}
