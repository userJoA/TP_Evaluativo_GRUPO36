package ar.edu.unju.fi.Controller;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.Model.Candidato;
import ar.edu.unju.fi.Model.Usuario;
import ar.edu.unju.fi.service.ICandidatoService;
import ar.edu.unju.fi.service.IUsuarioService;

@Controller
@RequestMapping("/votacion")

public class VotacionController {
	
	@Autowired
	@Qualifier("candidatoServiceList")
	private ICandidatoService candidatoService;
	
	@Autowired
	@Qualifier("usuarioServiceList")
	private IUsuarioService usuarioService;
	
	private static final Log LOGGER = LogFactory.getLog(VotacionController.class);
	

	/* redireccion a la pagina de votacion desde el formulario de nuevo_usario */
	@PostMapping("/vista")
	public ModelAndView getVotacion(@Validated @ModelAttribute("usuario") Usuario usuario,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			LOGGER.info("ocurrió un error "+usuario);
			ModelAndView mav = new ModelAndView("nuevo_usuario");
			mav.addObject("usuario", usuario);
			return mav;
		}
		/* validacion de email de usuario */
		if(usuarioService.buscarEmail(usuario.getEmail()) ) {
			LOGGER.info("Email asignado 2 veces" );
			ModelAndView mav = new ModelAndView("nuevo_usuario");
			mav.addObject("usuario", usuario);
			return mav;
			}else {
				
				ModelAndView mav = new ModelAndView("Votacion");	
				usuarioService.guardarUsuario(usuario);
				LOGGER.info("Se guardo "+usuario);
				
				mav.addObject("stars", this.candidatoService.listaCandidatos().getCandidatos());
				mav.addObject("usuario", usuario);
				
				return mav;
			
			}

	}
	
	
	/* redireccion a pagina star_choiced desde la pagina de votacion */
	@GetMapping("/eleccion2/{codigo}/{email}")
	public ModelAndView pagGracias( @PathVariable (value="codigo") int codigo,
									@PathVariable (value="email") String email) {
		
		ModelAndView modelAndView = new ModelAndView("star_choiced");	    	
    	
		Optional<Candidato> artista = this.candidatoService.listaCandidatos().getCandidatos().stream().filter(candidato ->codigo == candidato.getCodigo()).findFirst();    	    	
    	artista.get().setVotos(artista.get().getVotos()+1);
    	
    	Usuario u=usuarioService.buscarUsarioPorEmail(email);
    	u.setVoto(u.getVoto()+1);
    	LOGGER.info("El usuario "+u.getNombre()+" Voto a: "+artista.get().getNombre());
    	modelAndView.addObject("elegido", artista.get()); 
    	modelAndView.addObject("usuario", u); 
		return modelAndView;
		
	}
	
}
