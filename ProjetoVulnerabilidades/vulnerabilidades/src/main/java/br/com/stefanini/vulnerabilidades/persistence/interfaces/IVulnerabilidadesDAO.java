package br.com.stefanini.vulnerabilidades.persistence.interfaces;

import java.util.List;

import br.com.stefanini.vulnerabilidades.entity.Vulnerabilidades;

public interface IVulnerabilidadesDAO {
	
	
	public void insertTeste(Vulnerabilidades v) throws Exception;
	
	public List<Vulnerabilidades> bucarVulnerabilidadeNaoCadastradas(String nomeArquivo) throws Exception;
	
	public List<Vulnerabilidades> bucarRelatorio(String nomeArquivo) throws Exception;



}
