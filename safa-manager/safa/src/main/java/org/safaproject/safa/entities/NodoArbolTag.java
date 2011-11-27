package org.safaproject.safa.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class NodoArbolTag {
	
	@Id
	@GeneratedValue
	private Long idNodoArbolTag;
	
	@OneToMany
	@JoinColumn(name = "hijoId")	
	private List<NodoArbolTag> hijos;

	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="padreId")
	// La idea seria que no pueda modificar el padre...
	private NodoArbolTag padre;

	@NotNull
	private String nombreNodo;

	@ManyToOne
	@NotNull
	Tag miTag;

	public List<NodoArbolTag> getChildren() {
		return hijos;
	}

	public NodoArbolTag getParent() {
		return padre;
	}

	public void setChildren(List<NodoArbolTag> children) {
		this.hijos = children;
	}

	public void setParent(NodoArbolTag parent) {
		this.padre = parent;
	}

	public List<NodoArbolTag> getHijos() {
		return hijos;
	}

	public void setHijos(List<NodoArbolTag> hijos) {
		this.hijos = hijos;
	}

	public NodoArbolTag getPadre() {
		return padre;
	}

	public void setPadre(NodoArbolTag padre) {
		this.padre = padre;
	}

	public String getNombreNodo() {
		return nombreNodo;
	}

	public void setNombreNodo(String nombreNodo) {
		this.nombreNodo = nombreNodo;
	}

	public Tag getMiTag() {
		return miTag;
	}

	public void setMiTag(Tag miTag) {
		this.miTag = miTag;
	}

	public void setIdNodoArbolTag(Long idNodoArbolTag) {
		this.idNodoArbolTag = idNodoArbolTag;
	}

	public Long getIdNodoArbolTag() {
		return idNodoArbolTag;
	}
}
