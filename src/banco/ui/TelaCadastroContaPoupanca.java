package banco.ui;

import java.awt.*;
import javax.swing.*;

public class TelaCadastroContaPoupanca extends JFrame {
    private JTextField txtNumero, txtAgencia, txtSaldo, txtRendimento;
    private JButton btnSalvar, btnCancelar;

    public TelaCadastroContaPoupanca() {
        setTitle("Abertura de Conta Poupança");
        setSize(380, 280);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Número da Conta:"), gbc);
        txtNumero = new JTextField(10); gbc.gridx = 1; add(txtNumero, gbc);

        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Agência:"), gbc);
        txtAgencia = new JTextField(10); gbc.gridx = 1; add(txtAgencia, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Saldo Inicial (R$):"), gbc);
        txtSaldo = new JTextField("0.00", 10); gbc.gridx = 1; add(txtSaldo, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("Taxa Rendimento (%):"), gbc);
        txtRendimento = new JTextField("0.5", 10); gbc.gridx = 1; add(txtRendimento, gbc);

        JPanel p = new JPanel();
        btnSalvar = new JButton("Criar Poupança");
        btnCancelar = new JButton("Cancelar");
        p.add(btnSalvar); p.add(btnCancelar);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(p, gbc);

        btnCancelar.addActionListener(e -> dispose());
        btnSalvar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Conta Poupança gerada com sucesso!");
            dispose();
        });
    }
}