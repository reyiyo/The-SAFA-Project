package org.safaproject.safa.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.URL;
import org.safaproject.safa.utils.MD5;

@Entity
public class Url {
	
	//Generado por md5
	@Id
	private String idUrl;
	
	@URL
	private String url;
	
	@ManyToOne
	@JoinColumn(name="tipoRecurso")
	private TipoRecurso tipoRecurso;
	
	@Min(0)
	private Long tamanio;
	
	private String descripcion;
	
	public void generarId(){
		this.idUrl = MD5.getStringHash(url);
	}

	public String getIdUrl() {
		return idUrl;
	}

	public void setIdUrl(String idUrl) {
		this.idUrl = idUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TipoRecurso getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(TipoRecurso tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	public Long getTamanio() {
		return tamanio;
	}

	public void setTamanio(Long tamanio) {
		this.tamanio = tamanio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
