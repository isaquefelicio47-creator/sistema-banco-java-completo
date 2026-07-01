package banco.ui;

import banco.dao.ClienteDAO;
import java.awt.*;
import javax.swing.*;

public class TelaCadastroCliente extends JFrame {
    private JTextField txtNome, txtCpf, txtTelefone;
    private JButton btnSalvar, btnCancelar;

    public TelaCadastroCliente() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Cadastro de Cliente");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nome Completo:"), gbc);
        txtNome = new JTextField(15);
        gbc.gridx = 1;
        add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("CPF:"), gbc);
        txtCpf = new JTextField(15);
        gbc.gridx = 1;
        add(txtCpf, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Telefone:"), gbc);
        txtTelefone = new JTextField(15);
        gbc.gridx = 1;
        add(txtTelefone, gbc);

        JPanel painelBotoes = new JPanel();
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> this.dispose());
        painelBotoes.add(btnVoltar);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(painelBotoes, gbc);
        
        btnCancelar.addActionListener(e -> {
            txtNome.setText("");
            txtCpf.setText("");
            txtTelefone.setText("");
        });

        btnSalvar.addActionListener(e -> {
            if (txtNome.getText().isEmpty() || txtCpf.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Campos Nome e CPF são obrigatórios.");
                return;
            }
            ClienteDAO dao = new ClienteDAO();
            dao.salvar(txtNome.getText(), txtCpf.getText(), txtTelefone.getText());
            JOptionPane.showMessageDialog(this, "Cliente cadastrado no banco!");
            this.dispose();
        });
    }
}