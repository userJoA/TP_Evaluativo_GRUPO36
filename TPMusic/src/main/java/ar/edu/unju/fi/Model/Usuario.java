package ar.edu.unju.fi.Model;

import java.time.LocalDate;
import java.time.Period;

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
	
	private boolean habilitado;
	
	/**
	 * Constructor por defecto que inicializa a la propiedad voto en cero
	 */
	public Usuario() {
		this.voto = 0;
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
	public Usuario(String nombre, String apellido, String email, LocalDate fecha) {
		super();
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fecha = fecha;
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
	
	/**
	 * Getter, función que calcula la edad del usuario en años, y lo devuelve en el atributo 'edad'
	 * @return Obtiene la edad en años
	 */
	public int getEdad() {
		LocalDate hoy = LocalDate.now();
		Period edad = Period.between(this.fecha, hoy);
		return edad.getYears();
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
	
	/**
	 * Getter, Función que indica si el usuario está habilitado para votar
	 * @return Se obtiene verdadero si la cantidad de votos es menor a tres
	 */
	public boolean getHabilitado() {
		return this.voto <= 3;
	}
	
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
}
