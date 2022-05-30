package ar.edu.unju.fi.Model;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
@Component
public class Usuario {
	
	private int numero;
	@NotEmpty(message="El nombre no puede ser vacío")
	@Size(min=1,max=68,message="El tamaño del nombre no es valido")
	@Pattern(
			regexp ="^([a-zA-Z\\w]+(\\s[a-zA-Z\\w]+)*)$", 
			message ="No se admiten espacios como único, primer, o último caracter"
			)
	private String nombre;
	@NotEmpty(message="El nombre es requerido")
	@Size(min=1,max=24,message="El tamaño del apellido no es valido")
	@Pattern(
			regexp ="^([a-zA-Z\\w]+(\\s[a-zA-Z\\w]+)*)$", 
			message ="No se admiten espacios como único, primer, o último caracter"
			)
	private String apellido;
	@NotEmpty(message="El correo es requerido")
	@Email(message = "Correo es no valido", regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$")
	private String email;
	@NotNull @Past(message="La fecha debe ser anterio a la actual")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;
	private int edad;
	private int voto;
	private boolean fin_voto;
	
	public Usuario() {
		super();
	}
	
	/**
	 * Constructor con todos los atributos del objeto Alumno
	 * @param id numero
	 * @param nombre Nombres del usuario
	 * @param apellido Apellidos del usuario
	 * @param email Correo electrónico del usuario
	 * @param fecha De nacimiento
	 * @param edad De usuario
	 * @param votos De usuario
	 * @param boolean De fin de votos (True = 3 votos, False < 3 votos)
	 */
	public Usuario(int numero, String nombre, String apellido, String email, LocalDate fecha, int edad, int voto, boolean fin_voto) {
		super();
		this.numero = numero;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fecha = fecha;
		this.edad = edad;
		this.voto = voto;
		this.fin_voto = fin_voto;
	}

	public void setNumero(int numero) {
		this.numero=numero;
	}
	public int getNumero() {
		return numero;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public int getVoto() {
		return voto;
	}
	
	public void setVoto(int voto) {
		this.voto = voto;
	}
	public boolean getFinVoto() {
		return fin_voto;
	}
	
	public void setFinVoto(boolean fin_voto) {
		this.fin_voto = fin_voto;
	}
}
