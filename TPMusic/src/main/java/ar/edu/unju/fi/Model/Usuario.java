package ar.edu.unju.fi.Model;

import java.time.LocalDate;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Usuario {
	
	@NotEmpty(message="El título no puede ser vacío")
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
	@NotEmpty(message="El correo es requerido")
	@Pattern(
			regexp ="^((\\d{2,4}|[(]\\d{2,4}[)])(\\s(15))?\\s?\\d{6,8}|(\\d{2,4}|[(]\\d{2,4}[)])(15)?\\d{6,8})$", 
			message ="Debe tener código de área de 2 a 4 dígitos entre parentesis o sin ellos, separado del resto con un espacio, usar u omitir el 15, separado o junto al resto de 6 a 8 dígitos. Ejemplo: (999) 15 8887777, (999) 158887777 (999)158887777, 999 15 8887777, 999 158887777, 9998887777"
			)
	@NotNull (message="Fecha de Nacimiento")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate fecha;
	private int edad;
	private int voto;
	private boolean fin_voto;
	
	public Usuario() {
		
	}
	
	/**
	 * Constructor con todos los atributos del objeto Alumno
	 * @param dni Nro de DNI del alumno
	 * @param nombre Nombres del alumno
	 * @param apellido Apellidos del alumno
	 * @param email Correo electrónico del alumno
	 * @param telefono Telefono fijo o celular del alumno
	 */
	public Usuario(String nombre, String apellido, String email, LocalDate fecha, int edad, int voto, boolean fin_voto) {
		super();
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fecha = fecha;
		this.edad = edad;
		this.voto = voto;
		this.fin_voto = fin_voto;
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
