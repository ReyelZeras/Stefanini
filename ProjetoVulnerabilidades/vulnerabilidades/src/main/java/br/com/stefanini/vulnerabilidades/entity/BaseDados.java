package br.com.stefanini.vulnerabilidades.entity;

public class BaseDados {

	private Integer id;
	private String nome;
	private String criticidade;
	private String hora;
	private String solucao;

	public BaseDados() {

	}

	public BaseDados(Integer id, String criticidade, String hora, String nome, String solucao) {
		super();
		this.id = id;
		this.criticidade = criticidade;
		this.hora = hora;
		this.nome = nome;
		this.solucao = solucao;
	}

	public BaseDados(Integer id, String nome, String criticidade, String hora) {
		super();
		this.id = id;
		this.nome = nome;
		this.criticidade = criticidade;
		this.hora = hora;
	}

	public BaseDados(String nome, String criticidade, String hora) {
		super();
		this.nome = nome;
		this.criticidade = criticidade;
		this.hora = hora;
	}

	public BaseDados(String nome) {
		super();
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCriticidade() {
		return criticidade;
	}

	public void setCriticidade(String criticidade) {
		this.criticidade = criticidade;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

	@Override
	public String toString() {
		return "\nBase de dados: id :" + id + ", Nome: " + nome + ", solucao: " + solucao;
	}

}
