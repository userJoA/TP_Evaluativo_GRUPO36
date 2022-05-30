package ar.edu.unju.fi.Controller;


import org.springframework.web.bind.annotation.GetMapping;



public class MainController {
	@GetMapping("/")
	public String getIndex() {		
return "index";
	
}
	
}
