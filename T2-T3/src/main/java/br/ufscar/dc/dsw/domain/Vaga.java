package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "anos_contrato")
    private int anosContrato;

    @Column(name = "salario")
    private BigDecimal salario;

    @Column(name = "dataLim")
    private String dataLim;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "status")
    private String status;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getDataLim() {
        return dataLim;
    }

    public void setDataLim(String dataLim) {
        this.dataLim = dataLim;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
