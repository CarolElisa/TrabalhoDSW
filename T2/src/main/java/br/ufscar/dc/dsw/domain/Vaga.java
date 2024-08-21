package br.ufscar.dc.dsw.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "vaga")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nivel")
    private String nivel;

    @Column(name = "anos_contrato")
    private int anosContrato;

    @Column(name = "salario")
    private BigDecimal salario;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToMany(mappedBy = "candidaturas")
    private Set<Profissional> profissionais;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getAnosContrato() {
        return anosContrato;
    }

    public void setAnosContrato(int anosContrato) {
        this.anosContrato = anosContrato;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Set<Profissional> getProfissionais() {
        return profissionais;
    }

    public void setProfissionais(Set<Profissional> profissionais) {
        this.profissionais = profissionais;
    }
}
