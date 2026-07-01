package banco.model;

public class Usuario {
    private Long id;
    private String login;
    private String senha;
    private String nome;
    private String perfil;
    private double saldo;

    public Usuario() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    private String tipoConta;
    public String getTipoConta() { return tipoConta; }
    public void setTipoConta(String tipoConta) { this.tipoConta = tipoConta; }

private double limiteChequeEspecial; 
private double limiteMaximoChequeEspecial;

public double getLimiteChequeEspecial() { return limiteChequeEspecial; }
public void setLimiteChequeEspecial(double limiteChequeEspecial) { this.limiteChequeEspecial = limiteChequeEspecial; }

public double getLimiteMaximoChequeEspecial() { return limiteMaximoChequeEspecial; }
public void setLimiteMaximoChequeEspecial(double limiteMaximoChequeEspecial) { this.limiteMaximoChequeEspecial = limiteMaximoChequeEspecial; }

private java.util.List<String> historicoTransacoes = new java.util.ArrayList<>();

public void adicionarTransacao(String detalhe) {
    this.historicoTransacoes.add(detalhe);
}
public java.util.List<String> getHistoricoTransacoes() {
    return historicoTransacoes;
}
}