package ar.edu.unju.fi.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.Model.Candidato;
import ar.edu.unju.fi.Util.lista_candidatos;
import ar.edu.unju.fi.service.ICandidatoService;
@Service("candidatoServiceList")
public class CandidatoServiceImp implements ICandidatoService {
 
	@Autowired
	private lista_candidatos listaCandidatos;
	

	
	@Override
	public Candidato buscarCandidato(int codigo) {
		 Optional <Candidato> candidato= listaCandidatos.getCandidatos().stream().filter(a->a.getCodigo() == codigo).findFirst();
		 return candidato.get();
		
	}



	@Override
	public Candidato getCandidato() {
		
		return new Candidato();
	}



	@Override
	public lista_candidatos listaCandidatos() {
		
		return listaCandidatos;
	}



	@Override
	public boolean guardarCandidato(Candidato candidato) {
		//agregar un candidato
		return listaCandidatos.getCandidatos().add(candidato);
	}



	@Override
	public void modificarCandidato(Candidato candidato) {
		for(Candidato c:listaCandidatos.getCandidatos()) {
			if(c.getCodigo()==candidato.getCodigo()) {
				c.setNombre(candidato.getNombre());
				c.setGenero(candidato.getGenero());
				c.setDescripcion(candidato.getDescripcion());
				c.setVotos(candidato.getVotos());
				
			}
		}
		
	}



	@Override
	public void eliminarCandidato(int codigo) {
		
		for(Candidato c: listaCandidatos.getCandidatos()) {
			if(c.getCodigo()==codigo) {
				listaCandidatos.getCandidatos().remove(c);
				break;
			}
			
		}
		
		

	}



	@Override
	public int sumaVotos() {
		int suma=0;
		for(Candidato c: listaCandidatos.getCandidatos()) {
			suma+=c.getVotos();
		}
		return suma;
	}



	@Override
	public boolean buscarCan(int codigo) {
		boolean band=false;
		for(Candidato candidato: listaCandidatos.getCandidatos()) {
			if(candidato.getCodigo()==codigo)
				{	
					band=true;
					break;
				}
		}
		
		return band;
	}



	



	



	


	

}
