package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import br.ufscar.dc.dsw.validation.UniqueCNPJ;
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
public class Vaga extends Entidade<Long> {

	@NotBlank(message = "{NotBlank.vaga.nome}")
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	private String nome;

	@NotBlank(message = "{NotBlank.vaga.nivel}")
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	private String nivel;

	
	@NotBlank(message = "{NotBlank.vaga.descricao}")
	@Size(max = 250)
	@Column(nullable = false, length = 250)
	private String descricao;
    
	@NotNull(message = "{NotNull.vaga.anosContrato}")
	@Column(nullable = false, length = 5)
	private Integer anosContrato;
	
	@NotNull(message = "{NotNull.vaga.salario}")
	@Column(nullable = false, columnDefinition = "DECIMAL(9,2) DEFAULT 500.0")
	private BigDecimal salario;
    
	@NotNull(message = "{NotNull.vaga.empresa}")
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

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

	public Integer getAnosContrato() {
		return anosContrato;
	}

	public void setAnosContrato(Integer anosContrato) {
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

	public String getDescricao()
	{
		return this.descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
}
