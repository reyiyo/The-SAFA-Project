package org.safaproject.safa.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Tag implements Serializable {
	
	@Id
	@GeneratedValue
	private Long idTag;
	
	private String valor;
	
	@ManyToOne
	@JoinColumn(name="nombreTag")
	private MoldeTag moldeTag;
	
	@ManyToMany(mappedBy="tags")
	private Set<Apunte> apuntes;
	
	public void setIdTag(Long idTag) {
		this.idTag = idTag;
	}
	
	public Long getIdTag() {
		return idTag;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setMoldeTag(MoldeTag moldeTag) {
		this.moldeTag = moldeTag;
		//moldeTag.addTag(this);
	}
	
	public MoldeTag getMoldeTag() {
		return moldeTag;
	}

	public void setApuntes(Set<Apunte> apuntes) {
		this.apuntes = apuntes;
	}
	
	public Set<Apunte> getApuntes() {
		return apuntes;
	}

}
