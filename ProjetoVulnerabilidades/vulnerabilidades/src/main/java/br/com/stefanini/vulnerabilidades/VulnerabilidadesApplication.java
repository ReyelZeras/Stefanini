package br.com.stefanini.vulnerabilidades;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import br.com.stefanini.vulnerabilidades.uploadarquivo.StorageProperties;
import br.com.stefanini.vulnerabilidades.uploadarquivo.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class VulnerabilidadesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VulnerabilidadesApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}