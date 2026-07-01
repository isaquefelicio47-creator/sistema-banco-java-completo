package banco.dao;

import banco.model.ContaPoupanca;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContaPoupancaDAO {
    // Adicionado final para remover o aviso do compilador
    private static final Map<String, ContaPoupanca> poupancas = new HashMap<>();

    public ContaPoupanca buscarPorNumero(String numeroConta) {
        return poupancas.get(numeroConta);
    }

    public double somarTodosSaldos() {
        double total = 0;
        for (ContaPoupanca cp : poupancas.values()) {
            total += cp.getSaldo();
        }
        return total;
    }

    public void salvar(ContaPoupanca conta) {
        if (conta != null && conta.getNumeroConta() != null) {
            poupancas.put(conta.getNumeroConta(), conta);
        }
    }

    public List<ContaPoupanca> listarTodas() {
        return new ArrayList<>(poupancas.values());
    }
}