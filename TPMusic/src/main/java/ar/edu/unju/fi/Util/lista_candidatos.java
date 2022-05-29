package ar.edu.unju.fi.Util;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.Model.Candidato;

@Component
public class lista_candidatos {
	private ArrayList<Candidato> Candidatos;

	public lista_candidatos() {
		
		  Candidatos=new ArrayList<>(); 
			
			  Candidato candidato1= new Candidato(1,"Rolling Stone", "Rock",
			  "Legendaria banda inglesa", 0); Candidato candidato2= new
			  Candidato(2,"Bob Marley", "Reggae", "Basicamente el fundador del reggae", 0);
			  Candidato candidato3= new Candidato(3,"Daddy Yankee", "Reggaeton",
			  "Maximo exponente del genero mas popular del mundo", 0); Candidato
			  candidato4= new Candidato(4,"Ninguno", "-_-", ":(", 0);
			  
			  Candidatos.add(candidato1); Candidatos.add(candidato2);
			  Candidatos.add(candidato3); Candidatos.add(candidato4);
			 
		 
	}

	public ArrayList<Candidato> getCandidatos() {
		return Candidatos;
	}

	
	public void setCandidatos(ArrayList<Candidato> candidatos) {
		Candidatos = candidatos;
	}
	
	
	
	
	
}
