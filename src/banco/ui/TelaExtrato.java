package banco.ui;

import banco.model.Usuario;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;

public class TelaExtrato extends javax.swing.JFrame {

    private final Usuario usuarioLogado;
    private javax.swing.JTextArea txtAreaExtrato;
    private javax.swing.JButton btnVoltar;

    public TelaExtrato(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
        gerarExtratoTexto();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Extrato Bancário");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        txtAreaExtrato = new javax.swing.JTextArea();
        txtAreaExtrato.setEditable(false);
        txtAreaExtrato.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Fonte estilo cupom fiscal
        
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(txtAreaExtrato);
        scrollPane.setBorder(BorderFactory.createTitledBorder(" Histórico de Movimentações "));
        add(scrollPane, BorderLayout.CENTER);

        btnVoltar = new javax.swing.JButton("⬅️ Voltar para Operações");
        btnVoltar.addActionListener(e -> {
            new TelaOperacoes(usuarioLogado).setVisible(true);
            this.dispose();
        });
        add(btnVoltar, BorderLayout.SOUTH);
    }

    private void gerarExtratoTexto() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("            EXTRATO BANCÁRIO             \n");
        sb.append("=========================================\n");
        sb.append("Cliente: ").append(usuarioLogado.getNome()).append("\n");
        sb.append("Conta Tipo: ").append(usuarioLogado.getTipoConta() != null ? usuarioLogado.getTipoConta() : "CORRENTE").append("\n");
        sb.append("=========================================\n\n");

        if (usuarioLogado.getHistoricoTransacoes().isEmpty()) {
            sb.append(" Não há movimentações registradas.\n");
        } else {
            for (String transacao : usuarioLogado.getHistoricoTransacoes()) {
                sb.append(" ").append(transacao).append("\n");
                sb.append("-----------------------------------------\n");
            }
        }

        sb.append("\n=========================================\n");
        sb.append("RESUMO ATUAL:\n");
        sb.append("Saldo Próprio: R$ ").append(String.format("%.2f", usuarioLogado.getSaldo())).append("\n");
        if ("CORRENTE".equalsIgnoreCase(usuarioLogado.getTipoConta())) {
            sb.append("Limite Cheque Especial: R$ ").append(String.format("%.2f", usuarioLogado.getLimiteChequeEspecial())).append("\n");
        }
        sb.append("=========================================\n");

        txtAreaExtrato.setText(sb.toString());
    }
}