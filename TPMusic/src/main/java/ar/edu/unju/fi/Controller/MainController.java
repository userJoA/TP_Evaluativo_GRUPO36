package ar.edu.unju.fi.Controller;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;

public class MainController {
	@GetMapping("/")
	public String getIndex() {		
return "index";
	
}
	
}
