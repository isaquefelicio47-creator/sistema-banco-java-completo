package banco.ui;

import java.awt.*;
import javax.swing.*;

public class TelaCadastroContaCorrente extends JFrame {
    private JTextField txtNumero, txtAgencia, txtSaldo, txtLimite;
    private JButton btnSalvar, btnCancelar;

    public TelaCadastroContaCorrente() {
        setTitle("Abertura de Conta Corrente");
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

        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("Limite Cheque Especial:"), gbc);
        txtLimite = new JTextField("200.00", 10); gbc.gridx = 1; add(txtLimite, gbc);

        JPanel p = new JPanel();
        btnSalvar = new JButton("Abrir Conta");
        btnCancelar = new JButton("Fechar");
        p.add(btnSalvar); p.add(btnCancelar);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(p, gbc);

        btnCancelar.addActionListener(e -> dispose());
        btnSalvar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Conta aberta com sucesso no sistema!");
            dispose();
        });
    }
}