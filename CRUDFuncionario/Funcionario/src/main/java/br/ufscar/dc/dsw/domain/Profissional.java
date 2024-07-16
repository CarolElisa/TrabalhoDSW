package br.ufscar.dc.dsw.domain;

public class Profissional {

    private String cpf;
    private String telefone;
    private String sexo;
    private String dataDeNascimento;
 

    public Profissional(String cpf) {
        this.cpf = cpf;
    }

    public Profissional(String telefone, String sexo, String dataDeNascimento) {
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataDeNascimento = dataDeNascimento;
    }

    public Profissional(String cpf, String telefone, String sexo, String dataDeNascimento) {
        this(telefone, sexo, dataDeNascimento);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getDatadenascimento() {
        return dataDeNascimento;
    }

    public void setDatadenascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
}