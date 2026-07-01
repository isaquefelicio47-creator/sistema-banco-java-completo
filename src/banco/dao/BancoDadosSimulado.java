package banco.dao;

import banco.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class BancoDadosSimulado {
    public static final List<Usuario> usuarios = criarListaSimulada();

    private static List<Usuario> criarListaSimulada() {
        List<Usuario> lista = new ArrayList<>();
        Usuario admin = new Usuario();
        admin.setId(1L);
        admin.setLogin("admin");
        admin.setSenha("20252021711");
        admin.setNome("Administrador");
        admin.setPerfil("ADMIN");
        admin.setSaldo(1000.0);
        lista.add(admin);
        return lista;
    }

    public static Usuario buscarPorLogin(String login) {
        if (login == null) return null;
        for (Usuario u : usuarios) {
            if (u.getLogin() != null && u.getLogin().equalsIgnoreCase(login.trim())) {
                return u;
            }
        }
        return null;
    }
}