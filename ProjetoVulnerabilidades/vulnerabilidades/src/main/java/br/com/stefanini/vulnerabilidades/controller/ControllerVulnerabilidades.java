package br.com.stefanini.vulnerabilidades.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
						"Você enviou com sucesso '" + files.getOriginalFilename() + "'");

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

			String caminho = caminhoArquivo + File.separator + "relatório-" + nomeArquivo;
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
//		String arquivoTxt = "relatório-" + nomeArquivo;
//		if (arquivoTxt.indexOf(".txt")>-1) response.setContentType("application/txt");
//	      response.setHeader("Content-Disposition", "attachment; filename=" +arquivoTxt);
//	      response.setHeader("Content-Transfer-Encoding", "binary");
//		try {
//			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
//			FileInputStream fis = new FileInputStream(caminhoArquivo + File.separator + "relatório-" + nomeArquivo);
//			int len;
//			byte[] buf = new byte[1024];
//			while ((len = fis.read(buf)) > 0) {
//				bos.write(buf, 0, len);
//			}
//			bos.close();
//			response.flushBuffer();
//		} catch (IOException e) {
//			e.printStackTrace();
//
//		}

		return "redirect:/pesquisaVulnerabilidade";
	}

	@GetMapping("/pesquisaVulnerabilidade")
//	@ResponseBody
	public ModelAndView insertBancoVulnerabilidade(Model model, HttpServletResponse response) {

		ArquivoDAO arqDao = new ArquivoDAO();

		try {
			List<Vulnerabilidades> vulnerabilidadeNaoCadastradas = arqDao
					.bucarVulnerabilidadeNaoCadastradas(nomeArquivo);
			
//			String arquivoTxt = "relatório-" + nomeArquivo;
//			if (arquivoTxt.indexOf(".txt") > -1) response.setContentType("application/txt");
//			response.setHeader("Content-Disposition", "attachment; filename=" + arquivoTxt);
//			response.setHeader("Content-Transfer-Encoding", "binary");
//
////			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
////			FileInputStream fis = new FileInputStream(caminhoArquivo + File.separator + "relatório-" + nomeArquivo);
////			int len;
////			byte[] buf = new byte[1024];
////			while ((len = fis.read(buf)) > 0) {
////				bos.write(buf, 0, len);
////			}
////			bos.close();
//			response.flushBuffer();

			if (vulnerabilidadeNaoCadastradas.size() >= 1) {
				model.addAttribute("listVulnerabilidade", vulnerabilidadeNaoCadastradas);
				return new ModelAndView("vulnerabilidaExistente");
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		model.addAttribute("message", "Você enviou com sucesso " + nomeArquivo);
		model.addAttribute("sucesso", "Relatório gerado com sucesso - Verifique a pasta Donwload");
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public HttpEntity<byte[]> download() throws IOException {

		byte[] arquivo = Files.readAllBytes(Paths.get(caminhoArquivo + "relatório-" + nomeArquivo));

		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add("Content-Disposition",
				"attachment;filename=" + File.separator + "relatório-" + nomeArquivo + File.separator);

		HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);

		return entity;
	}

//    @RequestMapping("/file/{fileName}")
//	@ResponseBody
//	public void show(@PathVariable("fileName") String fileName, HttpServletResponse response) {
//
////	      if (fileName.indexOf(".doc")>-1) response.setContentType("application/msword");
////	      if (fileName.indexOf(".docx")>-1) response.setContentType("application/msword");
////	      if (fileName.indexOf(".xls")>-1) response.setContentType("application/vnd.ms-excel");
////	      if (fileName.indexOf(".csv")>-1) response.setContentType("application/vnd.ms-excel");
////	      if (fileName.indexOf(".ppt")>-1) response.setContentType("application/ppt");
////	      if (fileName.indexOf(".pdf")>-1) response.setContentType("application/pdf");
//	      if (fileName.indexOf(".txt")>-1) response.setContentType("application/txt");
//	      response.setHeader("Content-Disposition", "attachment; filename=" +fileName);
//	      response.setHeader("Content-Transfer-Encoding", "binary");
//	      try {
//	    	  BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
//	    	  FileInputStream fis = new FileInputStream(folderPath+fileName);
//	    	  int len;
//	    	  byte[] buf = new byte[1024];
//	    	  while((len = fis.read(buf)) > 0) {
//	    		  bos.write(buf,0,len);
//	    	  }
//	    	  bos.close();
//	    	  response.flushBuffer();
//	      }
//	      catch(IOException e) {
//	    	  e.printStackTrace();
//	    	  
//	      }
//	}

}
