package banco.ui;

import banco.dao.BancoDadosSimulado;
import banco.model.Usuario;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class TelaLogin extends javax.swing.JFrame {

    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JButton btnEntrar;

    // Variáveis de controle do bloqueio
    private int tentativasErradas = 0;
    private long tempoLiberacao = 0; 
    private Timer cronometroBloqueio;

    public TelaLogin() {
        initComponents();
        inicializarCronometro();
    }

    private void initComponents() {
        txtLogin = new javax.swing.JTextField(15);
        txtSenha = new javax.swing.JPasswordField(15);
        btnEntrar = new javax.swing.JButton("Entrar");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login - Sistema Bancário");
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        gbc.gridy = 0; add(new javax.swing.JLabel("Usuário:"), gbc);
        gbc.gridy = 1; add(txtLogin, gbc);
        gbc.gridy = 2; add(new javax.swing.JLabel("Senha:"), gbc);
        gbc.gridy = 3; add(txtSenha, gbc);
        gbc.gridy = 4; add(btnEntrar, gbc);

        btnEntrar.addActionListener(this::btnEntrarActionPerformed);

        setSize(350, 250);
        setLocationRelativeTo(null);
    }

    private void inicializarCronometro() {
        cronometroBloqueio = new Timer(1000, e -> {
            long tempoAtual = System.currentTimeMillis();
            
            if (tempoAtual < tempoLiberacao) {

                long segundosRestantes = (tempoLiberacao - tempoAtual) / 1000;
                btnEntrar.setEnabled(false);
                btnEntrar.setText("Bloqueado (" + segundosRestantes + "s)");
            } else {

                if (!btnEntrar.isEnabled()) {
                    btnEntrar.setEnabled(true);
                    btnEntrar.setText("Entrar");
                }
            }
        });
        cronometroBloqueio.start();
    }

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt != null) { System.out.print(""); }
        
        String login = txtLogin.getText().trim();
        String senha = new String(txtSenha.getPassword());

        Usuario usuario = BancoDadosSimulado.buscarPorLogin(login);
        
        if (usuario != null && usuario.getSenha().equals(senha)) {
            tentativasErradas = 0;
            new TelaMenuPrincipal(usuario).setVisible(true);
            this.dispose();
        } else {
            tentativasErradas++;
            
            if (tentativasErradas >= 3) {

                tempoLiberacao = System.currentTimeMillis() + 30000;
                tentativasErradas = 0;
                
                JOptionPane.showMessageDialog(this, 
                    "Senha incorreta pela 3ª vez!\nO botão de login foi bloqueado por 30 segundos.", 
                    "Bloqueio Ativado", JOptionPane.ERROR_MESSAGE);
            } else {
                int tentativasRestantes = 3 - tentativasErradas;
                JOptionPane.showMessageDialog(this, 
                    "Usuário ou senha incorretos!\nRestam " + tentativasRestantes + " tentativa(s).", 
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}