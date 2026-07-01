package banco.dao;

import banco.model.Usuario;
import java.util.List;

public class UsuarioDAO {

    public boolean validarLogin(String login, String senha) {
        Usuario u = buscarPorLogin(login);
        return u != null && u.getSenha().equals(senha.trim());
    }

    public Usuario buscarPorLogin(String login) {
        return BancoDadosSimulado.buscarPorLogin(login);
    }

    public void salvar(Usuario usuario) {
        cadastrar(usuario);
    }

    public void cadastrar(Usuario usuario) {
        if (usuario.getId() == null) {
            usuario.setId((long) (BancoDadosSimulado.usuarios.size() + 1));
            BancoDadosSimulado.usuarios.add(usuario);
        } else {
            atualizar(usuario);
        }
    }

    public void atualizar(Usuario usuario) {
        for (int i = 0; i < BancoDadosSimulado.usuarios.size(); i++) {
            if (BancoDadosSimulado.usuarios.get(i).getId().equals(usuario.getId())) {
                BancoDadosSimulado.usuarios.set(i, usuario);
                return;
            }
        }
    }

    public List<Usuario> listarTodos() {
        return BancoDadosSimulado.usuarios;
    }
}