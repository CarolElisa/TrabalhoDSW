package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import jakarta.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "candidatura")
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;

    @Column(nullable = false, length = 10)
    private String data;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Vaga getVaga() {
        return vaga;
    }
    
    public void setVaga(Vaga vaga){
        this.vaga = vaga;
    }
    
    public void setData(String data)
    {
        this.data = data;
    }

    public String getData()
    {
        return this.data;
    }
}
