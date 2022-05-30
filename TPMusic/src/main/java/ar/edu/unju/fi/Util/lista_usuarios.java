package ar.edu.unju.fi.Util;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.Model.Usuario;
@Component
public class lista_usuarios {
	private ArrayList<Usuario> Usuarios;

	public lista_usuarios() {
		
		  Usuarios=new ArrayList<>(); 
			
			  Usuario usuario1= new Usuario(1,"Jonatan", "Vera", "jana@mail.com",LocalDate.of(2,04,8), 28, 3, true); 
			  Usuario usuario2= new Usuario(2,"jona","V", "j@mail.com", LocalDate.of(1,02,9), 21, 2, false);
			  
			  
			  Usuarios.add(usuario1); Usuarios.add(usuario2);
			  
			 
		 
	}

	public ArrayList<Usuario> getUsuarios() {
		return Usuarios;
	}

	
	public void setUsuarios(ArrayList<Usuario> usuarios) {
		Usuarios = usuarios;
	}
	
	
	
	
	
}
