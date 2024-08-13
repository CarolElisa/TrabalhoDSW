package br.ufscar.dc.dsw.domain;

public class Profissional extends Usuario{
    
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String sexo;
    private String datanasc;
   

    public Profissional(long id, String cpf) {
        super(id);
        this.cpf = cpf;
    }

    public Profissional(long id, String nome, String email, String senha, String telefone, String sexo, String datanasc) {
        super (id);
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.sexo = sexo;
        this.datanasc = datanasc;
    }

    public Profissional(long id, String cpf, String nome, String email, String senha, String telefone, String sexo, String datanasc) {
        super(id);
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.sexo = sexo;
        this.datanasc = datanasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }

}


