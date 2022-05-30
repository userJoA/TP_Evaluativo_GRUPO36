package ar.edu.unju.fi.Util;

import java.time.LocalDate;
import java.util.ArrayList;

import ar.edu.unju.fi.Model.Usuario;

public class lista_usuarios {
	private ArrayList<Usuario> Usuarios;

	public lista_usuarios() {
		
		  Usuarios=new ArrayList<>(); 
			
			  Usuario usuario1= new Usuario("Jonatan", "Vera", "jana@mail.com",LocalDate.of(2020,04,20), 28, 3, true); 
			  Usuario usuario2= new Usuario("jona","V", "j@mail.com", LocalDate.of(2013,04,20), 21, 2, false);
			  
			  
			  Usuarios.add(usuario1); Usuarios.add(usuario2);
			  
			 
		 
	}

	public ArrayList<Usuario> getUsuarios() {
		return Usuarios;
	}

	
	public void setUsuarios(ArrayList<Usuario> usuarios) {
		Usuarios = usuarios;
	}
	
	
	
	
	
}
