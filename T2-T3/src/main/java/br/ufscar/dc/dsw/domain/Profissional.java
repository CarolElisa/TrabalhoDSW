package br.ufscar.dc.dsw.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "profissional")
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    @NotNull(message = "Usuario is mandatory")
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
        name = "candidatura",
        joinColumns = @JoinColumn(name = "profissional_id"),
        inverseJoinColumns = @JoinColumn(name = "vaga_id")
    )
    private List<Vaga> candidaturas;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public List<Vaga> getCandidaturas() {
        return candidaturas;
    }

    public void setCandidaturas(List<Vaga> candidaturas) {
        this.candidaturas = candidaturas;
    }
}