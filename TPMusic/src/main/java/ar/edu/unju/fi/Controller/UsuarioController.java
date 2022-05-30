package ar.edu.unju.fi.Controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.Model.Usuario;
import ar.edu.unju.fi.Util.lista_usuarios;
@Controller
@RequestMapping("/usuario")


public class UsuarioController {
	private static final Log LOGGER = LogFactory.getLog(UsuarioController.class);
	
	
	  @GetMapping("") 
	  public String paginaListaUsuarios(Model model) { 
	  lista_usuarios listaA= new lista_usuarios(); 
	  model.addAttribute("usuarios", listaA.getUsuarios());
	  return "lista_usuarios"; }
	 
	
	@GetMapping("/nuevo")
	public String getFormUsuarioPage(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "nuevo_usuario";
	}
	
	
	@PostMapping("/guardar")
	public ModelAndView guardarUsuario(@Validated @ModelAttribute("Usuario") Usuario usuario, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			LOGGER.error("No se cumplen las reglas de validación");
			ModelAndView mav = new ModelAndView("nuevo_alumno");
			mav.addObject("usuario", usuario);
			return mav;
		}
		ModelAndView mav = new ModelAndView("lista_usuarios");
		lista_usuarios listaUsuario = new lista_usuarios();
		
		if(listaUsuario.getUsuarios().add(usuario)) {
			LOGGER.info("Se guardó un objeto alumno en la lista de alumnos");
		}
		mav.addObject("usuarios", listaUsuario.getUsuarios());
		return mav;
	}
	
	@GetMapping("/lista")
	public String getListaCursosPage(Model model) {
		lista_usuarios listaUsuario = new lista_usuarios();
		model.addAttribute("usuarios", listaUsuario.getUsuarios());
		return "lista_usuarios";
	}
	

}
