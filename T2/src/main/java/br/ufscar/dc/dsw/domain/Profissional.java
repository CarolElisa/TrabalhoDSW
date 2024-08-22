package br.ufscar.dc.dsw.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;


import java.util.List;

@Entity
@Table(name = "profissional")
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotNull(message = "Usuario is mandatory")
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @ManyToMany
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