package br.com.stefanini.vulnerabilidades;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.stefanini.vulnerabilidades.controller.ControllerVulnerabilidades;
import br.com.stefanini.vulnerabilidades.entity.Vulnerabilidades;
import br.com.stefanini.vulnerabilidades.persistence.ArquivoDAO;

@SpringBootApplication
public class VulnerabilidadesApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(VulnerabilidadesApplication.class, args);
		
	}

}