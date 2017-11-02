package com.seminario.model;

import org.springframework.data.annotation.Id;

public class Premio {
	
	@Id
    public String id;
	private String idUsuario;
	private PremioNombre premioNombre;
	
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public PremioNombre getPremioNombre() {
		return premioNombre;
	}
	public void setPremioNombre(PremioNombre premioNombre) {
		this.premioNombre = premioNombre;
	}

}
