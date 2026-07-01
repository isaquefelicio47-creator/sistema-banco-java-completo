package banco.ui;

import banco.dao.BancoDadosSimulado;
import banco.model.Usuario;
import java.awt.*;
import javax.swing.*;

public class TelaOperacoes extends javax.swing.JFrame {

    private final Usuario usuarioLogado;
    // Componentes declarados
    private JLabel lblSaldo, lblChequeEspecial, lblTitulo;
    private JTextField txtDestino, txtValor;
    private JButton btnDepositar, btnSacar, btnTransferir, btnRenderJuros, btnVerExtrato, btnVoltarMenu;

    public TelaOperacoes(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
        atualizarSaldoTela();
        
        // Ajustes de visibilidade
        String tipo = (usuarioLogado.getTipoConta() != null) ? usuarioLogado.getTipoConta() : "CORRENTE";
        lblChequeEspecial.setVisible("CORRENTE".equalsIgnoreCase(tipo));
        btnRenderJuros.setVisible("POUPANÇA".equalsIgnoreCase(tipo));
    }

    private void atualizarSaldoTela() {
        lblSaldo.setText("Saldo Disponível: R$ " + String.format("%.2f", usuarioLogado.getSaldo()));
        lblChequeEspecial.setText("Cheque Especial: R$ " + String.format("%.2f", usuarioLogado.getLimiteChequeEspecial()));
    }

    private void initComponents() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Operações Bancárias");
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        lblTitulo = new JLabel("Área de Transações");
        lblSaldo = new JLabel("Saldo: R$ 0.00");
        lblChequeEspecial = new JLabel("Cheque Especial: R$ 0.00");
        txtDestino = new JTextField(15);
        txtValor = new JTextField(15);
        
        btnDepositar = new JButton("Depositar Valor");
        btnSacar = new JButton("Sacar Valor");
        btnTransferir = new JButton("Confirmar Transferência");
        btnRenderJuros = new JButton("📈 Render Poupança");
        btnVerExtrato = new JButton("📊 Visualizar Extrato");
        btnVoltarMenu = new JButton("⬅️ Voltar ao Menu");

        gbc.gridy = 0; add(lblTitulo, gbc);
        gbc.gridy = 1; add(lblSaldo, gbc);
        gbc.gridy = 2; add(lblChequeEspecial, gbc);
        gbc.gridy = 3; add(new JLabel("Destino:"), gbc);
        gbc.gridy = 4; add(txtDestino, gbc);
        gbc.gridy = 5; add(new JLabel("Valor:"), gbc);
        gbc.gridy = 6; add(txtValor, gbc);
        gbc.gridy = 7; add(btnDepositar, gbc);
        gbc.gridy = 8; add(btnSacar, gbc);
        gbc.gridy = 9; add(btnTransferir, gbc);
        gbc.gridy = 10; add(btnRenderJuros, gbc);
        gbc.gridy = 11; add(btnVerExtrato, gbc);
        gbc.gridy = 12; add(btnVoltarMenu, gbc);

        // Ações
        btnDepositar.addActionListener(e -> executarDeposito());
        btnSacar.addActionListener(e -> executarSaque());
        btnTransferir.addActionListener(e -> executarTransferencia());
        btnVerExtrato.addActionListener(e -> { new TelaExtrato(usuarioLogado).setVisible(true); dispose(); });
        btnVoltarMenu.addActionListener(e -> { new TelaMenuPrincipal(usuarioLogado).setVisible(true); dispose(); });

        pack();
        setLocationRelativeTo(null);
    }

    private void executarDeposito() {
        try {
            double valor = Double.parseDouble(txtValor.getText().replace(",", "."));
            usuarioLogado.setSaldo(usuarioLogado.getSaldo() + valor);
            usuarioLogado.adicionarTransacao("Depósito: +R$ " + String.format("%.2f", valor));
            atualizarSaldoTela();
            txtValor.setText("");
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Erro!"); }
    }

    private void executarSaque() {
    try {
        if (txtValor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o valor!");
            return;
        }
        double valor = Double.parseDouble(txtValor.getText().replace(",", "."));
        
        // Regra de Ouro: O saldo total é o Saldo + o Limite do Cheque Especial
        double totalDisponivel = usuarioLogado.getSaldo() + usuarioLogado.getLimiteChequeEspecial();

        if (valor > totalDisponivel) {
            JOptionPane.showMessageDialog(this, "Saldo e Cheque Especial insuficientes!");
            return;
        }

        // Lógica de subtração inteligente
        if (valor <= usuarioLogado.getSaldo()) {
            usuarioLogado.setSaldo(usuarioLogado.getSaldo() - valor);
        } else {
            // Se o valor for maior que o saldo, zera o saldo e usa o Cheque Especial
            double restante = valor - usuarioLogado.getSaldo();
            usuarioLogado.setSaldo(0);
            usuarioLogado.setLimiteChequeEspecial(usuarioLogado.getLimiteChequeEspecial() - restante);
        }

        usuarioLogado.adicionarTransacao("Saque: -R$ " + String.format("%.2f", valor));
        atualizarSaldoTela();
        txtValor.setText("");
        JOptionPane.showMessageDialog(this, "Saque realizado!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro no saque: " + e.getMessage());
    }
}

private void executarTransferencia() {
    try {
        if (txtValor.getText().isEmpty() || txtDestino.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha valor e destino!");
            return;
        }
        double valor = Double.parseDouble(txtValor.getText().replace(",", "."));
        String destinoLogin = txtDestino.getText().trim();
        Usuario destino = BancoDadosSimulado.buscarPorLogin(destinoLogin);

        if (destino == null) {
            JOptionPane.showMessageDialog(this, "Destino não encontrado.");
            return;
        }

        double totalDisponivel = usuarioLogado.getSaldo() + usuarioLogado.getLimiteChequeEspecial();
        if (valor > totalDisponivel) {
            JOptionPane.showMessageDialog(this, "Saldo e Cheque Especial insuficientes!");
            return;
        }
        destino.adicionarTransacao("Recebimento de " + usuarioLogado.getLogin() + ": +R$ " + String.format("%.2f", valor));
        // Lógica de subtração (idêntica à do saque)
        if (valor <= usuarioLogado.getSaldo()) {
            usuarioLogado.setSaldo(usuarioLogado.getSaldo() - valor);
        } else {
            double restante = valor - usuarioLogado.getSaldo();
            usuarioLogado.setSaldo(0);
            usuarioLogado.setLimiteChequeEspecial(usuarioLogado.getLimiteChequeEspecial() - restante);
        }

        destino.setSaldo(destino.getSaldo() + valor);
        usuarioLogado.adicionarTransacao("Transferência p/ " + destinoLogin + ": -R$ " + String.format("%.2f", valor));
        atualizarSaldoTela();
        txtValor.setText("");
        txtDestino.setText("");
        JOptionPane.showMessageDialog(this, "Transferência OK!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro na transferência: " + e.getMessage());
    }
}
}