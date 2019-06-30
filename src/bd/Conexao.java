package bd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexao {
	private String url;
	private String usuario;
	private String senha;
	private Connection connect;
	private volatile static Conexao instancia;
	
	private Conexao() {
		url = "jdbc:postgresql://localhost:5432/postgres";
		usuario = "postgres";
		senha = "postgres";
		
		
		try {
			Class.forName("org.postgresql.Driver");
			connect = DriverManager.getConnection(url,usuario,senha);
//			System.out.println("Conectado com sucesso.");
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	public static Conexao getInstancia() {
		if (instancia == null) {
			synchronized(Conexao.class) {
				if (instancia == null) {
					instancia = new Conexao();					
				}
			}
		}
		
        return instancia;
    }
	
	public int executarSql(String sql) {
		try {
			Statement stm = connect.createStatement();
			int res = stm.executeUpdate(sql);
			connect.close();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public ResultSet executarBusca(String sql) {
		try {
			Statement stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			connect.close();
			return rs;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
