package ar.edu.unju.fi.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.Util.lista_candidatos;

@Controller
@RequestMapping("/candidatos")
public class candidatoController {
	lista_candidatos listaCandidatos = new lista_candidatos();
	
	
	@GetMapping("/lista")
	public String getlistaCandidatos(Model model) {
		model.addAttribute("candidatos", listaCandidatos.getCandidatos());
		return "lista_candidatos";
	}
	
}
