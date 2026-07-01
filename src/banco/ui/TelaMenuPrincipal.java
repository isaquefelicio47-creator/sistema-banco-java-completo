package banco.ui;

import banco.model.Usuario;
import java.awt.*;
import javax.swing.*;

public class TelaMenuPrincipal extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    
    // Declaração dos componentes
    private JButton btnIrOperacoes;
    private JButton btnIrGerenciamento;
    private JButton btnLogout;

    public TelaMenuPrincipal(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Bancário - Menu Principal");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Instanciação
        JLabel lblBoasVindas = new JLabel("Olá, " + usuarioLogado.getNome() + " | Menu Principal");
        btnIrOperacoes = new JButton("Ir para Operações Bancárias");
        btnIrGerenciamento = new JButton("Ir para Gerenciamento [ADMIN]");
        btnLogout = new JButton("Fazer Logout");

        // Layout
        gbc.gridy = 0; add(lblBoasVindas, gbc);
        gbc.gridy = 1; add(btnIrOperacoes, gbc);
        gbc.gridy = 2; add(btnIrGerenciamento, gbc);
        gbc.gridy = 3; add(btnLogout, gbc);

        // Ações - CONEXÃO CORRETA COM btnIrOperacoes
        btnIrOperacoes.addActionListener(e -> {
            if (this.usuarioLogado != null) {
                // Abre a tela de operações passando o usuário
                TelaOperacoes tela = new TelaOperacoes(this.usuarioLogado);
                tela.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro: Usuário não identificado.");
            }
        });

        btnIrGerenciamento.addActionListener(e -> {
            // Lógica de admin
        });

    btnIrGerenciamento.addActionListener(e -> {
    new TelaGerenciamentoUsuarios(usuarioLogado).setVisible(true);
    this.dispose();
});
        btnLogout.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            this.dispose();
        });
        
        // Regra de perfil
        boolean isAdmin = "ADMIN".equalsIgnoreCase(usuarioLogado.getPerfil());
        btnIrGerenciamento.setVisible(isAdmin);
    }
}