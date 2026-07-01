package banco.service;

import banco.dao.BancoDadosSimulado;
import banco.dao.UsuarioDAO;
import banco.model.Usuario;
import java.util.List;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario autenticar(String login, String senha) {
        Usuario usuario = usuarioDAO.buscarPorLogin(login);
        if (usuario != null && usuario.getSenha().trim().equals(senha.trim())) {
            return usuario;
        }
        return null;
    }

    public List<Usuario> listarTodos() {
        return usuarioDAO.listarTodos();
    }

    public void atualizar(Usuario usuario) {
        usuarioDAO.atualizar(usuario);
    }

    public void cadastrar(Usuario usuario) {
        usuarioDAO.cadastrar(usuario);
    }

    // Resolve o erro da linha 151 da TelaGerenciamento
    public boolean excluir(Long id, String login) {
        return BancoDadosSimulado.usuarios.removeIf(u -> u.getId().equals(id));
    }

    public boolean realizarDeposito(String login, double valor) {
        Usuario u = usuarioDAO.buscarPorLogin(login);
        if (u != null && valor > 0) {
            u.setSaldo(u.getSaldo() + valor);
            usuarioDAO.atualizar(u);
            return true;
        }
        return false;
    }

    public boolean realizarSaque(String login, double valor) {
        Usuario u = usuarioDAO.buscarPorLogin(login);
        if (u != null && valor > 0 && u.getSaldo() >= valor) {
            u.setSaldo(u.getSaldo() - valor);
            usuarioDAO.atualizar(u);
            return true;
        }
        return false;
    }

    public boolean realizarTransferencia(String loginOrigem, String loginDestino, double valor) {
        Usuario origem = usuarioDAO.buscarPorLogin(loginOrigem);
        Usuario destino = usuarioDAO.buscarPorLogin(loginDestino);

        if (origem != null && destino != null && valor > 0 && origem.getSaldo() >= valor) {
            origem.setSaldo(origem.getSaldo() - valor);
            destino.setSaldo(destino.getSaldo() + valor);
            usuarioDAO.atualizar(origem);
            usuarioDAO.atualizar(destino);
            return true;
        }
        return false;
    }
}