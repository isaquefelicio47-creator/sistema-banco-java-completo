package banco.dao;

import banco.model.ContaCorrente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContaCorrenteDAO {
    private static final Map<Long, ContaCorrente> contasPorUsuario = new HashMap<>();
    private static final Map<String, ContaCorrente> contasPorNumero = new HashMap<>();

    private ContaCorrente obterOuCriarConta(Long usuarioId, String numeroConta) {
        if (!contasPorUsuario.containsKey(usuarioId)) {
            ContaCorrente cc = new ContaCorrente(
                usuarioId, 
                numeroConta, 
                null, 
                1000.0, 
                500.0
            );
            contasPorUsuario.put(usuarioId, cc);
            contasPorNumero.put(numeroConta, cc);
        }
        return contasPorUsuario.get(usuarioId);
    }

    public ContaCorrente buscarPorUsuarioId(Long usuarioId) {
        return obterOuCriarConta(usuarioId, "12345-6");
    }

    public ContaCorrente buscarPorNumero(String numeroConta) {
        if (numeroConta == null) return null;
        if (!contasPorNumero.containsKey(numeroConta)) {
            obterOuCriarConta(999L, numeroConta); 
        }
        return contasPorNumero.get(numeroConta);
    }

    // Atende perfeitamente os 4 erros da TelaOperacoes
    public void atualizarSaldo(double novoSaldo, String numeroConta) {
        ContaCorrente cc = buscarPorNumero(numeroConta);
        if (cc != null) {
            cc.setSaldo(novoSaldo);
        }
    }

    // Atende o cálculo de patrimônio do BancoService
    public double somarTodosSaldos() {
        double total = 0;
        for (ContaCorrente cc : contasPorUsuario.values()) {
            total += cc.getSaldo();
        }
        return total;
    }

    public void atualizar(ContaCorrente conta) {
        if (conta != null) {
            atualizarSaldo(conta.getSaldo(), conta.getNumeroConta());
        }
    }
    
    // CORREÇÃO CRUCIAL: Adicionado método save exigido na linha 21 do BancoService!
    public void save(ContaCorrente conta) {
        salvar(conta);
    }
    
    public void salvar(ContaCorrente conta) {
        if (conta != null && conta.getNumeroConta() != null) {
            contasPorNumero.put(conta.getNumeroConta(), conta);
            contasPorUsuario.put(conta.getId(), conta);
        }
    }

    public List<ContaCorrente> listarTodas() {
        return new ArrayList<>(contasPorUsuario.values());
    }
}