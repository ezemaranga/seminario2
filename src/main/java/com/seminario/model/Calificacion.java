package com.seminario.model;

public class Calificacion {
	Usuario usuario;
	String calificacion;
	
	public Calificacion (Usuario usuario, String calificacion) {
		this.usuario = usuario;
		this.calificacion = calificacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getcalificacion() {
		return calificacion;
	}

	public void setcalificacion(String calificacion) {
		this.calificacion = calificacion;
	}
}
