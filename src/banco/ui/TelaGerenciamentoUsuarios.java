package banco.ui;

import banco.dao.BancoDadosSimulado;
import banco.model.Usuario;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JOptionPane;

public class TelaGerenciamentoUsuarios extends javax.swing.JFrame {

    private final Usuario usuarioLogado;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtValor;
    private javax.swing.JButton btnAdicionarConta;
    private javax.swing.JButton btnExcluirConta;
    private javax.swing.JButton btnVoltarMenu;

    public TelaGerenciamentoUsuarios(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Admin - Controle de Contas");
        setSize(400, 420);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        txtDestino = new javax.swing.JTextField(15);
        txtValor = new javax.swing.JTextField(15);
        btnAdicionarConta = new javax.swing.JButton("Adicionar Nova Conta");
        btnExcluirConta = new javax.swing.JButton("Excluir Conta Existente");
        btnVoltarMenu = new javax.swing.JButton("Voltar ao Menu Principal");

        gbc.gridy = 0; add(new javax.swing.JLabel("PAINEL ADMINISTRATIVO"), gbc);
        gbc.gridy = 1; add(new javax.swing.JLabel("Login da Conta (Criar/Excluir):"), gbc);
        gbc.gridy = 2; add(txtDestino, gbc);
        gbc.gridy = 3; add(new javax.swing.JLabel("Saldo Inicial da Conta (R$):"), gbc);
        gbc.gridy = 4; add(txtValor, gbc);
        
        gbc.gridy = 5; add(btnAdicionarConta, gbc);
        gbc.gridy = 6; add(btnExcluirConta, gbc);
        gbc.gridy = 7; add(btnVoltarMenu, gbc);

        btnAdicionarConta.addActionListener(e -> adminAdicionarConta());
        btnExcluirConta.addActionListener(e -> adminExcluirConta());
        btnVoltarMenu.addActionListener(e -> {
            new TelaMenuPrincipal(usuarioLogado).setVisible(true);
            this.dispose();
        });
    }

    private void adminAdicionarConta() {
        try {
            String novoLogin = txtDestino.getText().trim();
            String valorTexto = txtValor.getText().trim();

            if (novoLogin.isEmpty()) throw new IllegalArgumentException("Informe o login da nova conta.");
            if (valorTexto.isEmpty()) throw new IllegalArgumentException("Informe o saldo inicial.");

            double saldoInicial = Double.parseDouble(valorTexto.replace(",", "."));
            if (saldoInicial < 0) throw new IllegalArgumentException("O saldo não pode ser negativo.");

            if (BancoDadosSimulado.buscarPorLogin(novoLogin) != null) {
                throw new IllegalArgumentException("Esse login já existe.");
            }

            String[] opcoes = {"Corrente", "Poupança"};
            int escolha = JOptionPane.showOptionDialog(this, "Selecione o tipo da conta:", "Tipo de Conta",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
            
            String tipo = (escolha == 1) ? "POUPANÇA" : "CORRENTE";
            double limiteChequeDigitado = 0.0;

            if ("CORRENTE".equalsIgnoreCase(tipo)) {
                String chequeTexto = JOptionPane.showInputDialog(this, "Digite o limite do Cheque Especial para este cliente (R$):", "Definir Limite", JOptionPane.QUESTION_MESSAGE);
                if (chequeTexto == null || chequeTexto.trim().isEmpty()) {
                    limiteChequeDigitado = 0.0;
                } else {
                    limiteChequeDigitado = Double.parseDouble(chequeTexto.replace(",", "."));
                    if (limiteChequeDigitado < 0) throw new IllegalArgumentException("O Cheque Especial não pode ser negativo.");
                }
            }

            String novaSenha = JOptionPane.showInputDialog(this, "Defina a senha para '" + novoLogin + "':");
            if (novaSenha == null || novaSenha.trim().isEmpty()) return;

            Usuario novoUsuario = new Usuario();
            novoUsuario.setLogin(novoLogin);
            novoUsuario.setNome(novoLogin.toUpperCase());
            novoUsuario.setSenha(novaSenha.trim());
            novoUsuario.setSaldo(saldoInicial);
            novoUsuario.setPerfil("CLIENTE");
            novoUsuario.setTipoConta(tipo);

            novoUsuario.setLimiteChequeEspecial(limiteChequeDigitado);
            novoUsuario.setLimiteMaximoChequeEspecial(limiteChequeDigitado); 

            BancoDadosSimulado.usuarios.add(novoUsuario);
            JOptionPane.showMessageDialog(this, "Conta " + tipo + " '" + novoLogin + "' criada com sucesso!\nCheque Especial definido: R$ " + limiteChequeDigitado);
            txtDestino.setText(""); txtValor.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro de digitação numérica. Use apenas números válidos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }
    private void adminExcluirConta() {
        try {
            String alvoLogin = txtDestino.getText().trim();
            if (alvoLogin.isEmpty()) throw new IllegalArgumentException("Informe o login da conta.");
            if (alvoLogin.equalsIgnoreCase(usuarioLogado.getLogin())) {
                throw new IllegalArgumentException("Você não pode apagar sua própria conta.");
            }

            Usuario alvo = BancoDadosSimulado.buscarPorLogin(alvoLogin);
            if (alvo == null) throw new IllegalArgumentException("Conta não encontrada.");

            BancoDadosSimulado.usuarios.remove(alvo);
            JOptionPane.showMessageDialog(this, "Conta '" + alvoLogin + "' excluída com sucesso!");
            txtDestino.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }
}