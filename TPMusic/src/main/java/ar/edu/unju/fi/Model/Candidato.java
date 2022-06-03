package ar.edu.unju.fi.Model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class Candidato {
	
	@Min(value=0,message="debe ser mayor o igual a 5")
	private int codigo;
	
	@Size(min=3, max=100, message="El nombre debe tener entre 3 a 100 caracteres")
	@NotEmpty(message="El nombre no pude estar vacio")
	private String nombre;
	
	@Size(min=3, max=100, message="el genero musical debe tener entre 3 y 100 letras")
	@NotEmpty(message="no puede ser vacío")
	private String genero;
	
	@Size(min=3, max=100, message="entre 3 a 100 caracteres")
	@NotEmpty(message="no puede ser vacío")
	private String descripcion;
	
	
	private int votos;
	
	
	
	public Candidato() {
		super();
	}



	public Candidato(int codigo, String nombre, String genero, String descripcion, int votos) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.genero = genero;
		this.descripcion = descripcion;
		this.votos = votos;
	}



	public int getCodigo() {
		return codigo;
	}



	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getGenero() {
		return genero;
	}



	public void setGenero(String genero) {
		this.genero = genero;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public int getVotos() {
		return votos;
	}



	public void setVotos(int votos) {
		this.votos = votos;
	}
	
	
	
}
