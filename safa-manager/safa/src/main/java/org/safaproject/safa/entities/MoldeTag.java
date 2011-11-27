package org.safaproject.safa.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class MoldeTag implements Serializable{
	
	@Id	
	private String nombreTag;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="nombreTag")
	private Set<Tag> tags;

	public void setNombreTag(String nombreTag) {
		this.nombreTag = nombreTag;
	}
	
	public String getNombreTag() {
		return nombreTag;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void addTag(Tag tag) {
		tags.add(tag);
	}

}
