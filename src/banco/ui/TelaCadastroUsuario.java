package banco.ui;

import java.awt.*;
import javax.swing.*;

public class TelaCadastroUsuario extends JFrame {
    private JTextField txtNome, txtLogin;
    private JPasswordField txtSenha;
    private JComboBox<String> cbPerfil;
    private JButton btnSalvar, btnCancelar;

    public TelaCadastroUsuario() {
        setTitle("Cadastro de Usuários do Sistema");
        setSize(360, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Nome do Funcionário:"), gbc);
        txtNome = new JTextField(15); gbc.gridx = 1; add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Login de Acesso:"), gbc);
        txtLogin = new JTextField(15); gbc.gridx = 1; add(txtLogin, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Senha:"), gbc);
        txtSenha = new JPasswordField(15); gbc.gridx = 1; add(txtSenha, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("Nível de Perfil:"), gbc);
        cbPerfil = new JComboBox<>(new String[]{"OPERADOR", "ADMIN"});
        gbc.gridx = 1; add(cbPerfil, gbc);

        JPanel p = new JPanel();
        btnSalvar = new JButton("Cadastrar");
        btnCancelar = new JButton("Sair");
        p.add(btnSalvar); p.add(btnCancelar);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(p, gbc);

        btnCancelar.addActionListener(e -> dispose());
        btnSalvar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Novo operador guardado!");
            dispose();
        });
    }
}