package ar.edu.unju.fi.service;

import ar.edu.unju.fi.Model.Candidato;
import ar.edu.unju.fi.Util.lista_candidatos;

public interface ICandidatoService {
	
	public Candidato getCandidato();
	public void votosCandidato();
	public lista_candidatos getListaCandidatos();
	public Candidato buscarCandidato(int codigo);
	
}
