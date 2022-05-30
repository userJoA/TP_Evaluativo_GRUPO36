package ar.edu.unju.fi.Controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.Model.Candidato;
import ar.edu.unju.fi.Util.lista_candidatos;
import ar.edu.unju.fi.service.ICandidatoService;

@Controller
public class PrincipalController {
	
	@Autowired
	@Qualifier("candidatoServiceList")
	private ICandidatoService candidatoService;
	
	Logger logger = LoggerFactory.getLogger(PrincipalController.class);
	//private lista_candidatos listaCandidatos = new lista_candidatos();
	
	@GetMapping("/principal")
	public String getPrincipalPage(Model model) {
		model.addAttribute("stars", this.candidatoService.listaCandidatos().getCandidatos());    	
    	logger.info("GET: /principal - METHOD: getPrincipalPage() - INFO: Mostar lista de candidatos en pág Principal");    	
    	for(Candidato p:this.candidatoService.listaCandidatos().getCandidatos()) {
    		logger.info(p.toString());
    	}    	
    	return "principal";
	}
	
	@GetMapping("/eleccion")
	public ModelAndView visualizarPaginaPost(@RequestParam(name = "codigo") int codigo) {    	
    	ModelAndView modelAndView = new ModelAndView("star_choiced");	    	
    	Optional<Candidato> artista = this.candidatoService.listaCandidatos().getCandidatos().stream().filter(candidato ->codigo == candidato.getCodigo()).findFirst();    	    	
    	artista.get().setVotos(artista.get().getVotos()+1);
    	modelAndView.addObject("elegido", artista.get());    	
		return modelAndView;
	}
}
