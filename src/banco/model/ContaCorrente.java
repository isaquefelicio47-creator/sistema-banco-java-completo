package banco.model;

public class ContaCorrente extends ContaBancaria {
    
    private double limiteChequeEspecial;

    public ContaCorrente(Long id, String numeroConta, Cliente titular, double saldo, double limiteChequEspecial){
        super(id, numeroConta, titular, saldo);
        this.limiteChequeEspecial = limiteChequEspecial;
    }

    @Override
    public boolean sacar(double valor){
        if(valor <= 0){
            return false;
        }

        if ((this.saldo + this.limiteChequeEspecial) >= valor) {
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    @Override
    public void depositar(double valor){
        super.depositar(valor);
    }

    @Override
    public void gerarExtrato(){

    }

    public void setLimiteChequEspecial(double limiteChequEspecial){
        this.limiteChequeEspecial = limiteChequEspecial;
    }
    public double getLimiteChequEspecial(){
        return limiteChequeEspecial;
    }
}