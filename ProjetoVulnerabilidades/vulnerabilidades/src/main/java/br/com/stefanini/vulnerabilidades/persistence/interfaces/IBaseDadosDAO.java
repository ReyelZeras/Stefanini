package br.com.stefanini.vulnerabilidades.persistence.interfaces;

import java.util.List;

import br.com.stefanini.vulnerabilidades.entity.BaseDados;

public interface IBaseDadosDAO {
	
	public void insertTeste(BaseDados bd) throws Exception;
	
	public List<BaseDados> findAllBaseDados() throws Exception;

	public void updateBaseDados(BaseDados baseDados) throws Exception;

	public BaseDados findByCriticidade(String nomeCriticidade) throws Exception;
}
