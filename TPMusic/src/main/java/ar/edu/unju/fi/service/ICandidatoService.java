package ar.edu.unju.fi.service;

import ar.edu.unju.fi.Model.Candidato;
import ar.edu.unju.fi.Util.lista_candidatos;

public interface ICandidatoService {
	
	
	public Candidato getCandidato();
	public lista_candidatos listaCandidatos();
	public boolean guardarCandidato(Candidato candidato);
	public Candidato buscarCandidato(int codigo);
	public void modificarCandidato(Candidato candidato);
	public void eliminarCandidato(int codigo);
	public int sumaVotos();
	
}
