package br.ufscar.dc.dsw.domain;

public class Candidatura {

    private Long id;
    private Vaga vaga;
    private Profissional profissional;
    private String status = "ABERTO";

    public Candidatura(Long id, Vaga vaga, Profissional profissional, String status){
        this.id = id;
        this.vaga = vaga;
        this.profissional = profissional;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vaga getVaga() {
        return vaga;
    }
    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Profissional getProfissional() {
        return profissional;
    }
    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }


    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
