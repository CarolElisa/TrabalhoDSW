package br.ufscar.dc.dsw.domain;

public class Empresa extends Usuario{

    private String cnpj;
    private String nome;
    private String descricao;
    private String email;
    private String senha;
    private String cidade;

    public Empresa(long id, String cnpj) {
        super(id);
        this.cnpj = cnpj;
    }

    public Empresa(long id, String CNPJ, String nome) {
        super(id);
        this.cnpj = CNPJ;
        this.nome = nome;
    }

    public Empresa(Long id, String CNPJ, String nome, String descricao, String email, String senha, String cidade) {
        super(id);
        this.descricao = descricao;
        this.cnpj = CNPJ;
        this.nome = nome;
        this.descricao = descricao;
        this.email = email;
        this.senha = senha;
        this.cidade = cidade;


    }



    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String CNPJ) {
        this.cnpj = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String desc) {
        this.descricao = desc;
    }
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

}