package banco.app;

import banco.ui.TelaLogin;

public class SistemaBanco {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
}