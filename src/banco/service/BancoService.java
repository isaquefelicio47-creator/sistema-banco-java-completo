package banco.service;

import banco.dao.ContaCorrenteDAO;
import banco.dao.ContaPoupancaDAO;
import banco.model.ContaBancaria;
import banco.model.ContaCorrente;
import banco.model.ContaPoupanca;
import java.util.ArrayList;
import java.util.List;

public class BancoService {
    // Definidos como final para eliminar os alertas do compilador
    private final ContaCorrenteDAO correnteDAO = new ContaCorrenteDAO();
    private final ContaPoupancaDAO poupancaDAO = new ContaPoupancaDAO();

    public void cadastrarContaCorrente(ContaCorrente conta) throws Exception {
        if (correnteDAO.buscarPorNumero(conta.getNumeroConta()) != null ||
            poupancaDAO.buscarPorNumero(conta.getNumeroConta()) != null) {
            throw new Exception("Número de conta já cadastrado no sistema!");
        }
        correnteDAO.save(conta);
    }

    public void cadastrarContaPoupanca(ContaPoupanca conta) throws Exception {
        if (correnteDAO.buscarPorNumero(conta.getNumeroConta()) != null ||
            poupancaDAO.buscarPorNumero(conta.getNumeroConta()) != null) {
            throw new Exception("Número de conta já cadastrado no sistema!");
        }
        poupancaDAO.salvar(conta);
    }

    public ContaBancaria buscarConta(String numeroConta) {
        ContaCorrente cc = correnteDAO.buscarPorNumero(numeroConta);
        if (cc != null) {
            return cc;
        }
        return poupancaDAO.buscarPorNumero(numeroConta);
    }

    public double calcularPatrimonioTotal() {
        double saldoCorrente = correnteDAO.somarTodosSaldos();
        double saldoPoupanca = poupancaDAO.somarTodosSaldos();
        return saldoCorrente + saldoPoupanca;
    }

    public List<ContaBancaria> exibirRelatorioGeral() {
        List<ContaBancaria> relatorio = new ArrayList<>();
        relatorio.addAll(correnteDAO.listarTodas());
        relatorio.addAll(poupancaDAO.listarTodas());
        return relatorio;
    }
}