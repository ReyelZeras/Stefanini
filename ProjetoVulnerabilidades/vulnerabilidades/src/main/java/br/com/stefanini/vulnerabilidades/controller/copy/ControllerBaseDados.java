package br.com.stefanini.vulnerabilidades.controller.copy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.stefanini.vulnerabilidades.entity.BaseDados;
import br.com.stefanini.vulnerabilidades.persistence.BaseDadosDAO;

@RequestMapping("bd/control")
@Controller
public class ControllerBaseDados {

	@PostMapping("insercaoBanco")
	public ModelAndView insertBancoVulnerabilidade(
			@RequestParam(value = "nome", required = false) String nomeVulnerabilidade,
			@RequestParam(value = "criticidade", required = false) String criticidade,
			@RequestParam(value = "hora", required = false) String hora, Model model) {

		BaseDadosDAO bdDao = new BaseDadosDAO();
		System.out.println("nome: " + nomeVulnerabilidade);
		try {
			BaseDados vulnerabilidadeExistente = bdDao.findByCriticidade(nomeVulnerabilidade);
			System.out.println(vulnerabilidadeExistente);

			if (vulnerabilidadeExistente.equals(null)) {
				BaseDados bd = new BaseDados(nomeVulnerabilidade, criticidade, hora);
				bdDao.insertTeste(bd);
				System.out.println("\n\nVulnerabilidade cadastrada no banco");
				model.addAttribute("alertCadastro", "Vulnerabilidade cadastrada com sucesso!");
			} else {
//				System.out.println(String.valueOf(vulnerabilidadeExistente).equalsIgnoreCase(nomeVulnerabilidade));
				System.out.println("\n\nVulnerabilidade já existente no banco!");
				model.addAttribute("alertCadastro", "Vulnerabilidade já existente no banco!");
			}
		} catch (Exception e) {
			System.out.println("\n\nFui para o catch");
			e.printStackTrace();
		}

		return new ModelAndView("index");
	}

}
