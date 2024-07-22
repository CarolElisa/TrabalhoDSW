package br.ufscar.dc.dsw.domain;

public class Usuario {

	private Long id;
	private String nome;
	private String login;
	private String senha;
	private String papel;
	public String documento;


	public Usuario(Long id) {
		this.id = id;
	}

	public Usuario(String nome, String login, String senha, String papel, String documento) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.papel = papel;
		this.documento = documento;
	}

	public Usuario(Long id, String nome, String login, String senha, String papel, String documento) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.papel = papel;
		this.documento = documento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String d) {
		this.documento = d;
	}


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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String password) {
		this.senha = password;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}
}
