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
	
	
	
	//lista candidato
	
	@GetMapping("/lista")
	public String getlistaCandidatos(Model model) {
		model.addAttribute("candidatos", candidatoService.listaCandidatos().getCandidatos());
		LOGGER.info("Redirigiendo a al lista de candidatos");
		return "lista_candidatos";
	}
	
	
	//carga candidato
	
	@GetMapping("/nuevo")
	public String getFormulario(Model model) {
		model.addAttribute("candidato",candidatoService.getCandidato());
		return "nuevo_candidato";
	}
	
	
	@PostMapping("/guardar")
	public ModelAndView getListaCandidatos(@Validated @ModelAttribute("candidato")Candidato candidato,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			LOGGER.info("No se cumple con los requisitos de Validacion");
			ModelAndView mav = new ModelAndView("nuevo_candidato");
			mav.addObject("candidato", candidato);
			return mav;
		}
		//comprueba que no haya otro candidato con el mismo codigo
		if(candidatoService.buscarCan(candidato.getCodigo())==true) {
			ModelAndView mav = new ModelAndView("nuevo_candidato");
			mav.addObject("candidato", candidato);
			return mav;
		}
		else {
				ModelAndView mav = new ModelAndView("redirect:/candidato/lista");
				if(candidatoService.guardarCandidato(candidato)) {
					LOGGER.info("Se agregó " + candidato.getNombre()+" a la lista");
					}
				return mav;
		}
	}
	
	
	//voto candidato
	
	@GetMapping("/voto/{codigo}")
	public ModelAndView votoCandidato(@PathVariable (value="codigo") int codigo) {
		ModelAndView mav = new ModelAndView("lista_candidatos");
		candidatoService.buscarCandidato(codigo).setVotos(candidatoService.buscarCandidato(codigo).getVotos()+1);
		String nombre= candidatoService.buscarCandidato(codigo).getNombre();
		mav.addObject("candidatos",candidatoService.listaCandidatos().getCandidatos());
		LOGGER.info("Se registro un voto a " + nombre );
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
	String nombre=candidatoService.buscarCandidato(codigo).getNombre();
	candidatoService.eliminarCandidato(codigo);
	LOGGER.info("se ha eliminado a "+ nombre + " de la lista de candidatos" );
	return mav;
			
		
	}
	
	@GetMapping("/estado")
	public ModelAndView getEstadoVotacion(Model model) {
		//if(candidatoService.sumaVotos()==0)
		//{	
			//LOGGER.info("No hay votos" );
			//ModelAndView mav= new ModelAndView("redirect:/principal");
			//return mav;
		//}
		//else {
			ModelAndView mav = new ModelAndView("estado_votacion");
			mav.addObject("candidatos", candidatoService.listaCandidatos().getCandidatos());
			mav.addObject("suma",candidatoService.sumaVotos());
			LOGGER.info("Ver Estado de Votacion" );
			return mav;
		//}
	}
	
	
}
