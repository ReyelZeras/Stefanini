package br.com.stefanini.vulnerabilidades.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.stefanini.vulnerabilidades.entity.Vulnerabilidades;
import br.com.stefanini.vulnerabilidades.persistence.ArquivoDAO;

@Controller
public class ControllerVulnerabilidades {

	String caminho = System.getProperty("user.home");
	String caminhoArquivo = caminho + File.separator + "Downloads" + File.separator;
	String nomeArquivo = "";

	@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("files") MultipartFile files, RedirectAttributes redirectAttributes)
			throws Exception {

		if (files.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Selecione um arquivo para enviar");
			return "redirect:/";
		}

		try {

			byte[] bytes = files.getBytes();
			Path path = Paths.get(caminhoArquivo + files.getOriginalFilename());
			System.out.println("caminhoArquivo: " + caminhoArquivo + files.getOriginalFilename());
			Files.write(path, bytes);
			nomeArquivo = files.getOriginalFilename();

			redirectAttributes.addFlashAttribute("message",
					"Você enviou com sucesso '" + files.getOriginalFilename() + "'");

			// Começa aqui
			Vulnerabilidades v = new Vulnerabilidades();
			ArquivoDAO arq = new ArquivoDAO();

			String caminhoFolder = caminhoArquivo + files.getOriginalFilename();
			FileReader fr = null;
			BufferedReader br = null;

			try {
				fr = new FileReader(caminhoFolder);
				br = new BufferedReader(fr);

				String line;
				Long contador = (long) 11;
				File file = new File(caminhoFolder);

				while ((line = br.readLine()) != null) {

//					line = br.readLine();
					v.setVulnerabilidade(line.split(",")[0]);
					v.setQuantidade(line.split(",")[1]);
					v.setId(contador);
					v.setNomeArquivo(file.getName());
					System.out.println(v);
					contador++;
					arq.insertTeste(v);

				}
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				try {
					if (br != null) {
						br.close();
					}
					if (fr != null) {
						fr.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// Termina aqui
		} catch (IOException e) {
			e.printStackTrace();
		}
		write(nomeArquivo);
		return "redirect:/";
	}

	@GetMapping("/gerandoRelatorio")
	public String write(String nomeArquivo) throws Exception {

		try {

			ArquivoDAO arqDao = new ArquivoDAO();

			List<Vulnerabilidades> relatorio = arqDao.bucarRelatorio(nomeArquivo);

			String caminho = caminhoArquivo + File.separator + "relatório.txt";

			FileWriter arq = new FileWriter(caminho);

//			for (Vulnerabilidades vulnerabilidades : relatorio) {
//				System.out.println(vulnerabilidades);
//				arq.write(vulnerabilidades.toString());
//			}
			
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.println(relatorio);
			gravarArq.close();

			System.out.println("Arquivo salvo com sucesso!");

		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Erro ao salvar o arquivo!");
		}
		return "redirect:/";
	}

}
