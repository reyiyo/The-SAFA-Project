package org.safaproject.safa.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;

@Entity
public class Apunte {
	
	//TODO: Va a ser calculado con md5
	@Id
	private String idApunte;
	
	private String titulo;
	
	private String descripcion;
	
	private Date fechaSubida;
	
	@Past
	private Date fechaApunte;
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	@ManyToMany
	private Set<Tag> tags;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Indicador> indicadores;
	
	private boolean disponible = true;
	
	@ManyToOne
	@JoinColumn(name="denunciadoPor")
	private Usuario denunciadoPor;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="idApunte")
	private Set<Url> files;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idUrlImagen")
	private Url imagen;
	
	private boolean moderado = false;

	public String getIdApunte() {
		return idApunte;
	}
	
	public void setIdApunte(String idApunte) {
		this.idApunte = idApunte;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Date getFechaSubida() {
		return fechaSubida;
	}
	
	public void setFechaSubida(Date fechaSubida) {
		this.fechaSubida = fechaSubida;
	}
	
	public Date getFechaApunte() {
		return fechaApunte;
	}
	
	public void setFechaApunte(Date fechaApunte) {
		this.fechaApunte = fechaApunte;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Set<Tag> getTags() {
		return tags;
	}
	
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	public Set<Indicador> getIndicadores() {
		return indicadores;
	}
	
	public void setIndicadores(Set<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
	
	public boolean isDisponible() {
		return disponible;
	}
	
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	public Usuario getDenunciadoPor() {
		return denunciadoPor;
	}
	
	public void setDenunciadoPor(Usuario denunciadoPor) {
		this.denunciadoPor = denunciadoPor;
	}
	
	public boolean isModerado() {
		return moderado;
	}
	
	public void setModerado(boolean moderado) {
		this.moderado = moderado;
	}
	
	public void setImagen(Url imagen) {
		this.imagen = imagen;
	}

	public Url getImagen() {
		return imagen;
	}

	public void setFiles(Set<Url> files) {
		this.files = files;
	}

	public Set<Url> getFiles() {
		return files;
	}
}
