package dao;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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
	public void gerarPDF() {
		Document doc = new Document();
		List<String[]> lucroMensal = this.lucroMensal();
		List<String[]> lucroSemanal = this.lucroSemanal();
		List<String[]> quantidadePVMes = this.quantidadePVMes();
		List<String[]> quantidadeVF = this.quantidadeVF();
		List<String[]> marcaMaisVendida = this.marcaMaisVendida();
		
		String arquivo = "Relatorio.pdf";
		
		try {
			PdfWriter.getInstance(doc,new FileOutputStream(arquivo));
			doc.open();
			Paragraph p = new Paragraph("Lucro mensal");
			p.setAlignment(1);
			doc.add(p);
			p = new Paragraph("  ");
			doc.add(p);
			
			PdfPTable tabela = new PdfPTable(2);
			PdfPCell cel1 = new PdfPCell(new Paragraph("total"));
			PdfPCell cel2 = new PdfPCell(new Paragraph("mes"));
			
			tabela.addCell(cel1);
			tabela.addCell(cel2);
			
			for(String[] r:lucroMensal) {
				cel1 = new PdfPCell(new Paragraph(r[0]));
				cel2 = new PdfPCell(new Paragraph(r[1]));
				tabela.addCell(cel1);
				tabela.addCell(cel2);
			}
			
			doc.add(tabela);
			p = new Paragraph("Lucro semanal");
			doc.add(p);
			p = new Paragraph("  ");
			doc.add(p);
			
			PdfPTable tabela2 = new PdfPTable(1);
			PdfPCell cel3 = new PdfPCell(new Paragraph("total"));
			
			
			tabela2.addCell(cel3);
			
			
			for(String[] r:lucroSemanal) {
				cel3 = new PdfPCell(new Paragraph(r[0]));
				tabela2.addCell(cel3);
			}
			
			doc.add(tabela2);
			p = new Paragraph("Quantidade de produtos vendidos por mes");
			doc.add(p);
			p = new Paragraph("  ");
			doc.add(p);
			
			
			PdfPTable tabela3 = new PdfPTable(2);
			PdfPCell cel4 = new PdfPCell(new Paragraph("quantidade"));
			PdfPCell cel5 = new PdfPCell(new Paragraph("mes"));
			
			tabela3.addCell(cel4);
			tabela3.addCell(cel5);
			
			for(String[] r:quantidadePVMes) {
				cel4 = new PdfPCell(new Paragraph(r[0]));
				cel5 = new PdfPCell(new Paragraph(r[1]));
				tabela3.addCell(cel4);
				tabela3.addCell(cel5);
			}
			
			doc.add(tabela3);
			p = new Paragraph("Quantidade de vendas por funcionario");
			doc.add(p);
			p = new Paragraph("  ");
			doc.add(p);
			
			PdfPTable tabela4 = new PdfPTable(4);
			PdfPCell cel6 = new PdfPCell(new Paragraph("nome"));
			PdfPCell cel7 = new PdfPCell(new Paragraph("quantidade"));
			PdfPCell cel8 = new PdfPCell(new Paragraph("totalProdutos"));
			PdfPCell cel9 = new PdfPCell(new Paragraph("valorTotal"));
			
			tabela4.addCell(cel6);
			tabela4.addCell(cel7);
			tabela4.addCell(cel8);
			tabela4.addCell(cel9);

			
			for(String[] r:quantidadeVF) {
				cel6 = new PdfPCell(new Paragraph(r[0]));
				cel7 = new PdfPCell(new Paragraph(r[1]));
				cel8 = new PdfPCell(new Paragraph(r[2]));
				cel9 = new PdfPCell(new Paragraph(r[3]));
				tabela4.addCell(cel6);
				tabela4.addCell(cel7);
				tabela4.addCell(cel8);
				tabela4.addCell(cel9);
			}
			
			doc.add(tabela4);
			p = new Paragraph("Marca mais vendida");
			doc.add(p);
			p = new Paragraph("  ");
			doc.add(p);
			
			
			PdfPTable tabela5 = new PdfPTable(2);
			PdfPCell cel10 = new PdfPCell(new Paragraph("marca"));
			PdfPCell cel11 = new PdfPCell(new Paragraph("Quantidade"));
			
			tabela5.addCell(cel10);
			tabela5.addCell(cel11);
			
			for(String[] r:marcaMaisVendida) {
				cel10 = new PdfPCell(new Paragraph(r[0]));
				cel11 = new PdfPCell(new Paragraph(r[1]));
				tabela5.addCell(cel10);
				tabela5.addCell(cel11);
			}
			
			doc.add(tabela5);
			p = new Paragraph("  ");
			doc.add(p);
			
			
			
			doc.close();
			Desktop.getDesktop().open(new File(arquivo));

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
//	
	
	
//	
	
	
//	

}
