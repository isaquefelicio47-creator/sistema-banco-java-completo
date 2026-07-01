package banco.model;

public class Cliente {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    
    public Cliente(Long id, String nome, String cpf, String telefone){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public String getCpf(){
        return cpf;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public String getTelefone(){
        return telefone;
    }

    @Override
    public String toString(){
        return "Cliente " +
               "Id: " + id +
               " | Nome: " + nome +
               " | Cpf: " + cpf + 
               " | Telefone: " + telefone;
    }
}