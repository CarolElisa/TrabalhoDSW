package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Vaga")
public class Vaga extends AbstractEntity<Long> {

	@NotBlank(message = "{NotBlank.vaga.nome}")
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	private String nome;

	@NotBlank(message = "{NotBlank.vaga.nivel}")
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	private String nivel;
    
	@NotNull(message = "{NotNull.vaga.anosContrato}")
	@Column(nullable = false, length = 5)
	private Integer anosContrato;
	
	@NotNull(message = "{NotNull.vaga.salario}")
	@Column(nullable = false, columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
	private BigDecimal salario;
    
	@NotNull(message = "{NotNull.vaga.empresa}")
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	public String getTitulo() {
		return nome;
	}

	public void setTitulo(String nome) {
		this.nome = nome;
	}

	public String getAutor() {
		return nivel;
	}

	public void setAutor(String nivel) {
		this.nivel = nivel;
	}

	public Integer getAno() {
		return anosContrato;
	}

	public void setAno(Integer anosContrato) {
		this.anosContrato = anosContrato;
	}

	public BigDecimal getsalario() {
		return salario;
	}

	public void setsalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}
