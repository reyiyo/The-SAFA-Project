package org.safaproject.safa.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class TipoRecurso {

	@Id
	private String nombre;
	
	@OneToMany
	@JoinColumn(name="idApunte")
	private Set<Url> recursos;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Set<Url> getRecursos() {
		return recursos;
	}
	
	public void setRecursos(Set<Url> recursos) {
		this.recursos = recursos;
	}
	
}
