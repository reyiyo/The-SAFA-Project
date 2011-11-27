package org.safaproject.safa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class PedidoTag {
	
	@Id
	@GeneratedValue
	private Long idPedidoTag;
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	private String nombreTag;
	
	private String valorTag;
	
	@ManyToOne
	@NotNull
	private Apunte apunte; // El tag nuevo se va a asociar a un apunte... no? -> Sep
	
	public Apunte getApunte() {
		return apunte;
	}
	public void setApunte(Apunte apunte) {
		this.apunte = apunte;
	}
	public Long getIdPedidoTag() {
		return idPedidoTag;
	}
	public void setIdPedidoTag(Long idPedidoTag) {
		this.idPedidoTag = idPedidoTag;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getNombreTag() {
		return nombreTag;
	}
	
	public void setNombreTag(String nombreTag) {
		this.nombreTag = nombreTag;
	}
	
	public String getValorTag() {
		return valorTag;
	}
	
	public void setValorTag(String valorTag) {
		this.valorTag = valorTag;
	}

}
