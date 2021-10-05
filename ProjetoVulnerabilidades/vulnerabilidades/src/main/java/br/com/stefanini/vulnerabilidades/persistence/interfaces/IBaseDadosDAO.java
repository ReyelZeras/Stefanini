package br.com.stefanini.vulnerabilidades.persistence.interfaces;

import java.util.List;

import br.com.stefanini.vulnerabilidades.entity.BaseDados;

public interface IBaseDadosDAO {


	
	
	public List<BaseDados> findAllBaseDados() throws Exception;

	public BaseDados findById(Long id) throws Exception;

	public String createBaseDados(BaseDados baseDados) throws Exception;

	public String updateBaseDados(BaseDados baseDados) throws Exception;

	public String deleteBaseDados(Long id) throws Exception;
	
	
}
