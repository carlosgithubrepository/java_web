package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	private static Connection conexao = null;

	public static Connection criarConexao() {
		
		try {
			Class.forName("org.postgresql.Driver");
			//singleton
			if(conexao == null) {
				conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/javaweb1_db","","");
			}
			return conexao;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
