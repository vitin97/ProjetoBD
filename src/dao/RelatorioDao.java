package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bd.Conexao;
import model.Pedido;

public class RelatorioDao {
	
	private Conexao con = Conexao.getInstancia();

	public List<String[]> lucroMensal() {
		ResultSet rs = con.executarBusca("select sum((p.preco - p.preco_custo)*pp.quantidade) , Extract('Month' From ped.data_pedido) as mes from produto as p inner join pedido_produto as pp on (p.id = pp.id_produto) inner join pedido as pad on ( ped.id = pp.id_pedido) group by mes ");
		List<String[]> relatorio = new ArrayList<String[]>();
		
		try {
			while (rs.next()) {
				String r[] = new String[2];
				r[0]=rs.getString("sum");
				r[1]=rs.getString("mes");
				relatorio.add(r);
			}
		
			return relatorio;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	 
	public List<String[]> lucroSemanal() {
		ResultSet rs = con.executarBusca("	select sum((p.preco - p.preco_custo)*pp.quantidade) from produto as p inner join pedido_produto as pp on (p.id = pp.id_produto) where ped.data_pedido between ‘2019-06-23’ and  ‘2019-06-29’\r\n" + 
				"");
		List<String[]> relatorio = new ArrayList<String[]>();
		
		try {
			while (rs.next()) {
				String r[] = new String[1];
				r[0]=rs.getString("sum");
				relatorio.add(r);
			}
		
			return relatorio;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String[]> quantidadePVMes() {
		ResultSet rs = con.executarBusca("	select count(pp.*) as quantidade, Extract('Month' From ped.data_pedido) as mes from pedido as ped inner join pedido_produto as pp on (pp.id_pedido = ped.id) group by mes\r\n" + 
				"");
		List<String[]> relatorio = new ArrayList<String[]>();
		
		try {
			while (rs.next()) {
				String r[] = new String[2];
				r[0]=rs.getString("quantidade");
				r[1]=rs.getString("mes");
				relatorio.add(r);
			}
		
			return relatorio;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String[]> quantidadeVF() {
		ResultSet rs = con.executarBusca("	select f.nome count (ped.*), sum(pp.quantidade),sum(ped.valor) as valorTotal from pedido as ped inner join pedido_produto on (ped.id = pp.id_pedido) inner join Funcionario as f on (f.id = ped.id_fun) group by f.nome\r\n" + 
				"");
		List<String[]> relatorio = new ArrayList<String[]>();
		
		try {
			while (rs.next()) {
				String r[] = new String[4];
				r[0]=rs.getString("nome");
				r[1]=rs.getString("count");
				r[2]=rs.getString("sum");
				r[3]=rs.getString("valorTotal");
				relatorio.add(r);
			}
		
			return relatorio;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String[]> marcaMaisVendida() {
		ResultSet rs = con.executarBusca("	select max(select p.marca, sum(pp.quantidade) from produto as p inner join pedido_produto as pp on(p.id = pp.id_produto) group by p.marca )\r\n" + 
				"");
		List<String[]> relatorio = new ArrayList<String[]>();
		
		try {
			while (rs.next()) {
				String r[] = new String[2];
				r[0]=rs.getString("marca");
				r[1]=rs.getString("sum");
				relatorio.add(r);
			}
		
			return relatorio;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	
	
	
//	
	
	
//	
	
	
//	

}
