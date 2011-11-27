package org.safaproject.safa.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class TipoIndicador {
	
	@Id
	private String nombreIndicador;
	
	@OneToMany
	@JoinColumn(name="tipoRecurso")
	private Set<Indicador> indicadores;

	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}

	public String getNombreIndicador() {
		return nombreIndicador;
	}

	public void setIndicadores(Set<Indicador> indicadores) {
		this.indicadores = indicadores;
	}

	public Set<Indicador> getIndicadores() {
		return indicadores;
	}

}
