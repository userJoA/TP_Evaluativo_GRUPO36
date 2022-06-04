package ar.edu.unju.fi.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import ar.edu.unju.fi.Model.Usuario;
import ar.edu.unju.fi.service.IUsuarioService;
@Controller
@RequestMapping("/usuario")

public class UsuarioController {
	@Autowired
	@Qualifier("usuarioServiceList")
	private IUsuarioService usuarioService;
	private static final Log LOGGER = LogFactory.getLog(UsuarioController.class);
	
	
	  @GetMapping("lista") 
	  public String paginaListaUsuarios(Model model) {  
	  model.addAttribute("usuarios", usuarioService.listaUsuario().getUsuarios());
	  LOGGER.info("Redirigiendo a al lista de usuarios");
	  return "lista_usuarios"; }
	 
	
	@GetMapping("/nuevo")
	public String getFormUsuarioPage(Model model) {
		model.addAttribute("usuario", usuarioService.getUsuario());
		LOGGER.info("Redirigiendo a Cargarnuevo usuario");
		return "nuevo_usuario";
	}
	
	
	@PostMapping("/guardar")
	public ModelAndView guardarUsuario(@Validated @ModelAttribute("usuario") Usuario usuario,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			LOGGER.info("ocurrió un error "+usuario);
			ModelAndView mav = new ModelAndView("nuevo_usuario");
			mav.addObject("usuario", usuario);
			return mav;
		}
		ModelAndView mav = new ModelAndView("redirect:/usuario/lista");

		usuarioService.guardarUsuario(usuario);
		LOGGER.info("Se guardo "+usuario);
		return mav;
	}
	
	@PostMapping("/modificar")
	public ModelAndView modificarUsuario(@Validated @ModelAttribute("usuario") Usuario usuario,BindingResult bindingResult) {
		
		  if(bindingResult.hasErrors()) {
		  LOGGER.info("ocurrió un error "+usuario.getNombre()); ModelAndView mav = new ModelAndView("editar_usuario"); mav.addObject("usuario", usuario); 
		  return mav; 
		  }
		 
		ModelAndView mav = new ModelAndView("redirect:/usuario/lista");
		usuarioService.modificarUsuario(usuario);
		LOGGER.info("Se Modifico "+usuario.getNombre());
		
		return mav;
	}
	
	@GetMapping("/editar/{email}")
	public ModelAndView getEditarUsuario(@PathVariable ("email") String email) {
		ModelAndView mav = new ModelAndView("editar_usuario");
		Usuario usuario=usuarioService.buscarUsarioPorEmail(email);
		mav.addObject("usuario", usuario);
		return mav;
	}
	
	
	
	@GetMapping("/eliminar/{email}")
	public ModelAndView eliminarUsuario(@PathVariable("email")String email) {
	ModelAndView mav= new ModelAndView("redirect:/usuario/lista");	
	String nombre=usuarioService.buscarUsarioPorEmail(email).getNombre();
	usuarioService.eliminarUsuario(email);
	LOGGER.info("se ha eliminado a "+ nombre + " de la lista de Usuarios" );
	return mav;
	}
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(       Date.class,     
	                         new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));   
	}
}
