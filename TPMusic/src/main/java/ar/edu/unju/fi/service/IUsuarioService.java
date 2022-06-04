package ar.edu.unju.fi.service;

import ar.edu.unju.fi.Model.Usuario;
import ar.edu.unju.fi.Util.lista_usuarios;

public interface IUsuarioService {
	
	
	public Usuario getUsuario();
	public lista_usuarios listaUsuario();
	public boolean guardarUsuario(Usuario usuario);
	public Usuario buscarUsuario(int numero);
	public void modificarUsuario(Usuario usuario);
	public void eliminarUsuario(int numero);
	public int sumaVotos();
	public int sacarEdad();
	public boolean buscarEmail(String email);
	public Usuario buscarUsarioPorEmail(String email);
	
	
}
