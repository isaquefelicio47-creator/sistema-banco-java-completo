package banco.model;

import banco.interfaces.Operavel;

public abstract class ContaBancaria implements Operavel {
    
    private Long id;
    private String numeroConta;
    private Cliente titular;
    protected double saldo;

    public ContaBancaria(Long id, String numeroConta, Cliente titular, double saldo){
        this.id = id;
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldo;
    }

    @Override
    public void depositar(double valor){
        if(valor > 0){
            this.saldo += valor;
        }
    } 

    @Override
    public boolean sacar(double valor){
        if(valor <= 0){
            return false;
        }
        if(this.saldo >= valor){
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    @Override
    public double exibirSaldo(){
        return this.saldo;
    } 

    public abstract void gerarExtrato();

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }
    public void setNumeroConta(String numeroConta){
        this.numeroConta = numeroConta;
    }
    public String getNumeroConta(){
        return numeroConta;
    }
    public void setTitular(Cliente titular){
        this.titular = titular;
    }
    public Cliente getTitular(){
        return titular;
    }

    public double getSaldo() {
        return this.saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}