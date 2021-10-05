package br.com.stefanini.vulnerabilidades.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="vulnerabilidades")
public class Vulnerabilidades {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String vulnerabilidade;
	private String quantidade;
	
	public Vulnerabilidades() {
		// TODO Auto-generated constructor stub
	}

	public Vulnerabilidades(Long id, String vulnerabilidade, String quantidade) {
		super();
		this.id = id;
		this.vulnerabilidade = vulnerabilidade;
		this.quantidade = quantidade;
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

	@Override
	public String toString() {
		return "\nVulnerabilidades: id: " + id + ", Nome: " + vulnerabilidade + ", Quantidade: " + quantidade;
	}
	


}