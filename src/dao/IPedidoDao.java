package dao;

import java.util.List;

import model.Pedido;

public interface IPedidoDao {

	public boolean cadastrarPedido(Pedido p);
	
	public boolean excluirPedido(Pedido p);
	
	public boolean atualizarPedido(Pedido p);
	
	public List<Pedido> recuperar ();
	
}
