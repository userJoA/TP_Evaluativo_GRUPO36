package ar.edu.unju.fi.Controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.Model.Candidato;
import ar.edu.unju.fi.service.ICandidatoService;

@Controller
@RequestMapping("/candidato")
public class candidatoController {
	@Autowired
	@Qualifier("candidatoServiceList")
	private ICandidatoService candidatoService;
	
	private static final Log LOGGER = LogFactory.getLog(candidatoController.class);
	//lista_candidatos listaCandidatos = new lista_candidatos();
	
	
	@GetMapping("/lista")
	public String getlistaCandidatos(Model model) {
		model.addAttribute("candidatos", candidatoService.listaCandidatos().getCandidatos());
		return "lista_candidatos";
	}
	
	
	//carga candidato
	
	@GetMapping("/nuevo")
	public String getFormulario(Model model) {
		model.addAttribute("candidato",candidatoService.getCandidato());
		return "nuevo_candidato";
	}
	
	//poner mensaje personalizado
	@PostMapping("/guardar")
	public ModelAndView getListaCandidatos(@Validated @ModelAttribute("candidato")Candidato candidato,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("nuevo_candidato");
			mav.addObject("candidato", candidato);
			return mav;
		}
		
		ModelAndView mav = new ModelAndView("redirect:/candidato/lista");
		if(candidatoService.guardarCandidato(candidato)) {
			LOGGER.info("Se agregó un objeto al arrayList de candidatos");
		}
		return mav;
	}
	
	
	
	//poner confirmacion de voto
	@GetMapping("/voto/{codigo}")
	public ModelAndView votoCandidato(@PathVariable (value="codigo") int codigo) {
		ModelAndView mav = new ModelAndView("lista_candidatos");
		candidatoService.buscarCandidato(codigo).setVotos(candidatoService.buscarCandidato(codigo).getVotos()+1);
		mav.addObject("candidatos",candidatoService.listaCandidatos().getCandidatos());
		return mav;
	}
	
	
	//editar candidato
	
	@GetMapping("/editar/{codigo}")
	public ModelAndView getEditarCandidato(@PathVariable ("codigo") int codigo) {
		ModelAndView mav = new ModelAndView("editar_candidato");
		Candidato candidato=candidatoService.buscarCandidato(codigo);
		mav.addObject("candidato", candidato);
		return mav;
	}
	
	//poner confirmacion de edcion 
	@PostMapping("/modificar")
	public ModelAndView modificarCandidato(@Validated @ModelAttribute("candidato") Candidato candidato,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			LOGGER.info("ocurrió un error "+candidato);
			ModelAndView mav = new ModelAndView("editar_candidato");
			mav.addObject("candidato", candidato);
			return mav;
		}
		ModelAndView mav = new ModelAndView("redirect:/candidato/lista");
		candidatoService.modificarCandidato(candidato);
		return mav;
	}

	
	//eliminar candidato
	
	@GetMapping("/eliminar/{codigo}")
	public ModelAndView eliminarCandidato(@PathVariable("codigo")int codigo) {
	ModelAndView mav= new ModelAndView("redirect:/candidato/lista");	
	candidatoService.eliminarCandidato(codigo);
	LOGGER.info("se ha eliminado un objeto");
	return mav;
			
		
	}
	
	@GetMapping("/estado")
	public String estadoVotacion(Model model) {
		model.addAttribute("candidatos", candidatoService.listaCandidatos().getCandidatos());
		return "estado votacion";
	}
	
	
}
