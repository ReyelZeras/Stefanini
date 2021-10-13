package br.com.stefanini.vulnerabilidades.controller.copy;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.stefanini.vulnerabilidades.entity.BaseDados;
import br.com.stefanini.vulnerabilidades.persistence.BaseDadosDAO;

@Controller
public class ControllerIndex {

	@GetMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index");
	}

	@RequestMapping("/tabela")
	public String tabela(Model model) {
		try {
			BaseDadosDAO bdDao = new BaseDadosDAO();
			List<BaseDados> listTabela = bdDao.findAllBaseDados();
			model.addAttribute("listTabela", listTabela);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tabela";
	}

}
