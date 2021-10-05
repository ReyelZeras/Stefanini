package br.com.stefanini.vulnerabilidades.persistence.interfaces;

import java.util.List;

import br.com.stefanini.vulnerabilidades.entity.Vulnerabilidades;

public interface IVulnerabilidadesDAO {
	
	
	public List<Vulnerabilidades> findAllVulnerabilidades() throws Exception;

	public Vulnerabilidades findById(Long id) throws Exception;

	public String createVulnerabilidade(Vulnerabilidades vulnerabilidades) throws Exception;

	public String updateVulnerabilidade(Vulnerabilidades vulnerabilidades) throws Exception;

	public String deleteVulnerabilidade(Long id) throws Exception;

}
