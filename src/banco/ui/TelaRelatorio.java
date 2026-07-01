package banco.ui;

import java.awt.BorderLayout;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaRelatorio extends JFrame {

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public TelaRelatorio() {
        setTitle("Relatório de Clientes Cadastrados");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("ID");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("CPF");
        modeloTabela.addColumn("Telefone/Email");

        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            this.setVisible(false);
        });
        add(btnVoltar, BorderLayout.SOUTH);
        carregarDados();
    }

    private void carregarDados() {
        String sql = "SELECT id, nome, cpf, email FROM clientes ORDER BY id";
        try (Connection conn = banco.dao.ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            modeloTabela.setRowCount(0);

            while (rs.next()) {
                Vector<Object> linha = new Vector<>();
                linha.add(rs.getInt("id"));
                linha.add(rs.getString("nome"));
                linha.add(rs.getString("cpf"));
                linha.add(rs.getString("email"));
                modeloTabela.addRow(linha);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler base de clientes: " + e.getMessage());
        }
    }
}