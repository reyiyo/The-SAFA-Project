package org.safaproject.safa.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
public class Usuario {
	
	//Va a ser el id que nos de openID
	@Id
	private Long idUsuario;
	
	@Pattern(regexp = "^[a-zA-Z0-9]+[\\.\\-_a-zA-Z0-9]+$")
	private String username;
	
	@Size(min=6,max=20)
	private String password;
	
	@Email
	private String email;
	
	@ManyToOne
	@JoinColumn(name="idUnversidad")
	private Tag universidad;
	
	@ManyToOne
	@JoinColumn(name="idFacultad")
	private Tag facultad;
	
	@ManyToOne
	@JoinColumn(name="idCarrera")
	private Tag carrera;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Rol> roles;
	
	@OneToMany
	@JoinColumn(name="idUsuario")
	private List<Apunte> historial;
	
	@Min(0)
	private Integer invitacionesLeft;
	
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public Tag getUniversidad() {
		return universidad;
	}

	public void setUniversidad(Tag universidad) {
		this.universidad = universidad;
	}

	public Tag getFacultad() {
		return facultad;
	}

	public void setFacultad(Tag facultad) {
		this.facultad = facultad;
	}

	public Tag getCarrera() {
		return carrera;
	}

	public void setCarrera(Tag carrera) {
		this.carrera = carrera;
	}

	public List<Apunte> getHistorial() {
		return historial;
	}

	public void setHistorial(List<Apunte> historial) {
		this.historial = historial;
	}

	public Integer getInvitacionesLeft() {
		return invitacionesLeft;
	}

	public void setInvitacionesLeft(Integer invitacionesLeft) {
		this.invitacionesLeft = invitacionesLeft;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public Set<Rol> getRoles() {
		return roles;
	}
	
	public void addRol(Rol rol){
		roles.add(rol);
	}

}
