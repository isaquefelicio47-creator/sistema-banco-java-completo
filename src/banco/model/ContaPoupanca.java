package banco.model;

public class ContaPoupanca extends ContaBancaria {
    
    private double taxaRendimentoMensal;

    public ContaPoupanca(Long id, String numeroConta, Cliente titular, double saldo, double taxaRendimentoMensal){
        super(id, numeroConta, titular, saldo);
        this.taxaRendimentoMensal = taxaRendimentoMensal;
    }

    public double calcularRendimento(){
        return getSaldo() * (this.taxaRendimentoMensal / 100.0);
    }

    public void aplicarRendimento(){
        double rendimento = calcularRendimento();
        depositar(rendimento);
    }

    @Override
    public void gerarExtrato(){

    }

    public void setTaxaRendimentoMensal(double taxaRendimentoMensal){
        this.taxaRendimentoMensal = taxaRendimentoMensal;
    }
    public double getTaxaRendimentoMensal(){
        return taxaRendimentoMensal;
    }
}