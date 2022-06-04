package ar.edu.unju.fi.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.Model.Candidato;
import ar.edu.unju.fi.Model.Usuario;
import ar.edu.unju.fi.Util.lista_usuarios;
import ar.edu.unju.fi.service.IUsuarioService;
@Service("usuarioServiceList")
public class UsuarioServiceImp implements IUsuarioService {
 
	@Autowired
	private lista_usuarios listaUsuarios;
	

	
	@Override
	public Usuario buscarUsuario(int numero) {
		 Optional <Usuario> usuario= listaUsuarios.getUsuarios().stream().filter(a->a.getNumero() == numero).findFirst();
		 return usuario.get();
		
	}



	@Override
	public Usuario getUsuario() {
		
		return new Usuario();
	}



	@Override
	public lista_usuarios listaUsuario() {
		
		return listaUsuarios;
	}



	@Override
	public boolean guardarUsuario(Usuario usuario) {
		
		return listaUsuarios.getUsuarios().add(usuario);
	}



	@Override
	public void modificarUsuario(Usuario usuario) {
		for(Usuario c:listaUsuarios.getUsuarios()) {
			if(c.getNumero()==usuario.getNumero()) {
				c.setNombre(usuario.getNombre());
				c.setApellido(usuario.getApellido());
				c.setFecha(usuario.getFecha());
				
			}
		}
		
	}



	@Override
	public void eliminarUsuario(int numero) {
		
		for(Usuario c: listaUsuarios.getUsuarios()) {
			if(c.getNumero()==numero) {
				listaUsuarios.getUsuarios().remove(c);
				break;
			}
			
		}
		
		

	}

	@Override
	public int sumaVotos() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int sacarEdad() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public boolean buscarEmail(String email) {
		boolean band=false;
		for(Usuario user:listaUsuarios.getUsuarios()) {
			if(user.getEmail().equalsIgnoreCase(email)) {
				band=true;
				break;
			}
		}
		return band;
	}



	@Override
	public Usuario buscarUsarioPorEmail(String email) {
		Optional <Usuario> user= listaUsuarios.getUsuarios().stream().filter(usuario -> usuario.getEmail().equalsIgnoreCase(email)).findFirst();
		return user.get();
	}



	

	



	



	


	

}
