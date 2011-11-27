package org.safaproject.safa.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Indicador {
	
	@Id
	@GeneratedValue
	private Long idIndicador;
	
	@ManyToOne
	@JoinColumn(name="nombreIndicador")
	private TipoIndicador tipoIndicador;
	
	@Min(0)
	@Max(10)
	private Integer valor;
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	@ManyToMany(mappedBy="indicadores")
	private Set<Apunte> apuntes;
	
	public Long getIdIndicador() {
		return idIndicador;
	}
	
	public void setIdIndicador(Long idIndicador) {
		this.idIndicador = idIndicador;
	}
	
	public TipoIndicador getTipoIndicador() {
		return tipoIndicador;
	}
	
	public void setTipoIndicador(TipoIndicador tipoIndicador) {
		this.tipoIndicador = tipoIndicador;
	}
	
	public Integer getValor() {
		return valor;
	}
	
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setApuntes(Set<Apunte> apuntes) {
		this.apuntes = apuntes;
	}

	public Set<Apunte> getApuntes() {
		return apuntes;
	}

}
