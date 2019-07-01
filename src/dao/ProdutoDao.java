package dao;

import java.util.List;

import model.Produto;

public class ProdutoDao implements IProdutoDao {

	@Override
	public boolean cadastrarProduto(Produto p) {
		
		return false;
	}

	@Override
	public boolean excluirProduto(Produto p) {
		
		return false;
	}

	@Override
	public boolean atualizarProduto(Produto p) {
		
		return false;
	}

	@Override
	public List<Produto> recuperar() {
		
		return null;
	}

	@Override
	public List<Produto> recuperarPorFiltro(Produto p) {
		
		return null;
	}
	
}
