package dao;

import java.util.List;

import model.Pedido;

public interface IPedidoDao {

	public boolean cadastrarProduto(Pedido p);
	
	public boolean excluirProduto(Pedido p);
	
	public boolean atualizarProduto(Pedido p);
	
	public List<Pedido> recuperar ();
	
	public List<Pedido> recuperarPorFiltro (Pedido p);
}
