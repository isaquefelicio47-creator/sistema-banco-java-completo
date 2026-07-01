package banco.dao;

import java.sql.*;

public class ClienteDAO {

    public void salvar(String nome, String cpf, String email) {
        String sql = "INSERT INTO clientes (nome, cpf, email) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, email);
            stmt.executeUpdate();
            
            System.out.println("Cliente saved com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    public void listarTodos() {
        String sql = "SELECT * FROM clientes";
        
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            System.out.println("Lista de Clientes:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + 
                                   " | Nome: " + rs.getString("nome") + 
                                   " | CPF: " + rs.getString("cpf"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
        }
    }
}