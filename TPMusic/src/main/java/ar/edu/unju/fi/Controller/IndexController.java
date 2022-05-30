package ar.edu.unju.fi.Controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.Model.Candidato;
import ar.edu.unju.fi.Model.Usuario;
import ar.edu.unju.fi.Util.ListaUsuario;
import ar.edu.unju.fi.service.ICandidatoService;

@Controller
public class IndexController {
	
	ListaUsuario listaUsuarios= new ListaUsuario();
	
	@Autowired
	@Qualifier("candidatoServiceList")
	private ICandidatoService candidatoService;
	
	Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	
	
	@GetMapping("/")
	public String getIndexPage(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "index";
	}
	
	
	@GetMapping("/ingreso")
	public ModelAndView verificarDatosUsuario(@Validated @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult,Model model) {
		String pagina = "";
		ModelAndView mav = new ModelAndView("");
		LOGGER.info("usuario: " + usuario.getEmail());
		for(int i=0; i<listaUsuarios.getUsuarios().size(); i++) {
			if(listaUsuarios.getUsuarios().get(i).getEmail().equals(usuario.getEmail())) {				
				LOGGER.info("usu "+listaUsuarios.getUsuarios().get(i).getEmail());
				pagina="redirect:/principal";
				mav.addObject("user",usuario);
				model.addAttribute("stars", this.candidatoService.listaCandidatos().getCandidatos());    	
		    	LOGGER.info("GET: /principal - METHOD: getPrincipalPage() - INFO: Mostar lista de candidatos en pág Principal");    	
		    	for(Candidato p:this.candidatoService.listaCandidatos().getCandidatos()) {
		    		LOGGER.info(p.toString());
		    	} 
			}else {
				pagina="redirect:/";
				mav.addObject("usuario", new Usuario());
			}
		}
		ModelAndView mavVerif = new ModelAndView(pagina);
		return mavVerif;
	}

}