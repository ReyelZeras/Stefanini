package br.com.stefanini.vulnerabilidades.entity;

public class Vulnerabilidades {

	private Long id;
	private String vulnerabilidade;
	private String quantidade;
	private String nomeArquivo;
	private String criticidade;
	private String totalHoras;

	public Vulnerabilidades() {
	}

	public Vulnerabilidades(Long id, String vulnerabilidade, String quantidade, String nomeArquivo) {
		super();
		this.id = id;
		this.vulnerabilidade = vulnerabilidade;
		this.quantidade = quantidade;
		this.nomeArquivo = nomeArquivo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVulnerabilidade() {
		return vulnerabilidade;
	}

	public void setVulnerabilidade(String vulnerabilidade) {
		this.vulnerabilidade = vulnerabilidade;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getTotalHoras() {
		return totalHoras;
	}

	public void setTotalHoras(String totalHoras) {
		this.totalHoras = totalHoras;
	}

	public String getCriticidade() {
		return criticidade;
	}

	public void setCriticidade(String criticidade) {
		this.criticidade = criticidade;
	}

	@Override
	public String toString() {

		return "\nVulnerabilidade: " + vulnerabilidade + ", Criticidade: " + criticidade + ", Quantidade: " + quantidade
				+ ", Total horas: " + totalHoras + ", Nome do Arquivo: " + nomeArquivo;
	}

}