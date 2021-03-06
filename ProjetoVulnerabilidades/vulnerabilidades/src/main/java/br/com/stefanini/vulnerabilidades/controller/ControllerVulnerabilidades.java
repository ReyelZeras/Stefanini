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
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
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

					v.setVulnerabilidade(line.split(",")[0]);
					v.setQuantidade(line.split(",")[1].replace(";", ""));
					v.setId(contador);
					v.setNomeArquivo(file.getName());
					System.out.println(v);
					contador++;
					arq.insertTeste(v);
				}

				redirectAttributes.addFlashAttribute("message",
						"Voc?? enviou com sucesso '" + files.getOriginalFilename() + "'");

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

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/gerandoRelatorio";
	}

	@GetMapping("/gerandoRelatorio")
	public String write() throws Exception {

		try {
			ArquivoDAO arqDao = new ArquivoDAO();

			String caminho = caminhoArquivo + File.separator + "relat??rio-" + nomeArquivo;
			FileWriter arq = new FileWriter(caminho);
			PrintWriter gravarArq = new PrintWriter(arq);
			List<Vulnerabilidades> relatorio = arqDao.bucarRelatorio(nomeArquivo);
			
			gravarArq.println(relatorio);
			gravarArq.close();

			System.out.println("Arquivo salvo com sucesso!");

		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Erro ao salvar o arquivo!");
		}

		return "redirect:/pesquisaVulnerabilidade";
	}

	@GetMapping("/pesquisaVulnerabilidade")
	public ModelAndView insertBancoVulnerabilidade(Model model, HttpServletResponse response) {

		ArquivoDAO arqDao = new ArquivoDAO();

		try {
			List<Vulnerabilidades> vulnerabilidadeNaoCadastradas = arqDao
					.bucarVulnerabilidadeNaoCadastradas(nomeArquivo);

			if (vulnerabilidadeNaoCadastradas.size() >= 1) {
				model.addAttribute("listVulnerabilidade", vulnerabilidadeNaoCadastradas);
				return new ModelAndView("vulnerabilidaExistente");
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		model.addAttribute("message", "Voc?? enviou com sucesso " + nomeArquivo);
		model.addAttribute("sucesso", "Relat??rio gerado com sucesso - Verifique a pasta Donwload");
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public HttpEntity<byte[]> download() throws IOException {

		byte[] arquivo = Files.readAllBytes(Paths.get(caminhoArquivo + "relat??rio-" + nomeArquivo));

		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add("Content-Disposition",
				"attachment;filename=" + File.separator + "relat??rio-" + nomeArquivo + File.separator);

		HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);

		return entity;
	}

}
