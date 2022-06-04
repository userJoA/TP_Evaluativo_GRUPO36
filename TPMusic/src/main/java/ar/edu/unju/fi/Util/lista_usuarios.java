package ar.edu.unju.fi.Util;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.Model.Usuario;

@Component
public class lista_usuarios {
	
	private ArrayList<Usuario> usuarios;

	public lista_usuarios() {		
		  usuarios=new ArrayList<>();			
		  Usuario usuario1= new Usuario("Jonatan", "Vera", "jana@mail.com",LocalDate.of(1992,04,8)); 
		  Usuario usuario2= new Usuario("jona","V", "j@mail.com", LocalDate.of(2001,02,9));		  
		  usuarios.add(usuario1);
		  usuarios.add(usuario2);		 
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}	
	
}
