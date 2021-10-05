package br.com.stefanini.vulnerabilidades.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="baseDados")
public class BaseDados {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String Nome;
	private String Criticidade;
	private String Hora;
	
	public BaseDados() {
		// TODO Auto-generated constructor stub
	}

	public BaseDados(Long id, String nome, String criticidade, String hora) {
		super();
		this.id = id;
		Nome = nome;
		Criticidade = criticidade;
		Hora = hora;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCriticidade() {
		return Criticidade;
	}

	public void setCriticidade(String criticidade) {
		Criticidade = criticidade;
	}

	public String getHora() {
		return Hora;
	}

	public void setHora(String hora) {
		Hora = hora;
	}

	@Override
	public String toString() {
		return "\nBase de dados: id :" + id + ", Nome: " + Nome + ", Criticidade: " + Criticidade + ", Hora: " + Hora;
	}
	
	
	
	
	
}
