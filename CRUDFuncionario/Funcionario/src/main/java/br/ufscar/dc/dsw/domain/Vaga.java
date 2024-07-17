package br.ufscar.dc.dsw.domain;

public class Vaga {

    private Long id;
    private String funcao;
    private String nivel;
    private Integer anosContrato;
    private Float salario;
    private Empresa empresa;

    public Vaga(Long id) {
        this.id = id;
    }

    public Vaga(String func, String nv, Integer ano, Float slr,
            Empresa emp) {
        this.funcao = func;
        this.nivel = nv;
        this.anosContrato = ano;
        this.salario = slr;
        this.empresa = emp;
    }

      
    public Vaga(Long id, String funcao, String nivel, Integer ano, 
            Float salario, Empresa empresa) {
        this(funcao, nivel, ano, salario, empresa);
        this.id = id;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String func) {
        this.funcao = func;
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

    public void setAno(Integer anosContrato) {
        this.anosContrato = anosContrato;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
