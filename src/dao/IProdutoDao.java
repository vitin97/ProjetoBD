package dao;

import java.util.List;

import model.Produto;

public interface IProdutoDao {
	public boolean cadastrarProduto(Produto p);
	
	public boolean excluirProduto(Produto p);
	
	public boolean atualizarProduto(Produto p);
	
	public List<Produto> recuperar ();

}
