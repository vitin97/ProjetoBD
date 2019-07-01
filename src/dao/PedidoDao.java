package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bd.Conexao;
import model.Pedido;

public class PedidoDao implements IPedidoDao {
	private Conexao con = Conexao.getInstancia();

	@Override
	public boolean excluirPedido(Pedido p) {
		int res = con.executarSql("delete from pedido where id = '" + p.getId() + "' ");
		if (res > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean atualizarPedido(Pedido p) {
<<<<<<< refs/remotes/origin/master
		int res = con.executarSql("update pedido set  where id = '" + p.getId() + "' ");
=======
		int res = con.executarSql("update pedido set   where id = '" + p.getId() + "' ");
>>>>>>> Correções
		return false;
	}

	@Override
	public List<Pedido> recuperar() {
		ResultSet rs = con.executarBusca("select * from pedido");
		List<Pedido> pedidos = new ArrayList<Pedido>();
		
		try {
			while (rs.next()) {
				Pedido ped = new Pedido(0, null, 0, 0, 0, null);
				rs.getString("id");
				pedidos.add(ped);
			}
		
			return pedidos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Pedido> recuperarPorFiltro(Pedido p) {
		ResultSet rs = con.executarBusca("select * from pedido where id = "+ p.getId());
		return null;
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



	

}
