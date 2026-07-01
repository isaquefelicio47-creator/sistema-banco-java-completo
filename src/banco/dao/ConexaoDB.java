package banco.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {


    private static final String URL = "jdbc:postgresql://localhost:5432/Sicoob?currentSchema=public";
    private static final String USER = "postgres"; 
    private static final String PASSWORD = "0102030405.aBc";

    private static Connection conexao = null;

    private ConexaoDB() {}

    public static Connection getConexao() {
       System.out.println("URL de conexão sendo usada: " + URL);
        try {
            if (conexao == null || conexao.isClosed()) {
                Class.forName("org.postgresql.Driver");
                conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro: O Driver JDBC do PostgreSQL não foi encontrado!", e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
        }
        return conexao;
    }
    
}