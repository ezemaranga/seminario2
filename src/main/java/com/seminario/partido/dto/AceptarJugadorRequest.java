package com.seminario.partido.dto;

public class AceptarJugadorRequest {
	
	private String idPartido;
	private String idUsuarioJugador;
	
	public String getIdPartido() {
		return idPartido;
	}
	public void setIdPartido(String idPartido) {
		this.idPartido = idPartido;
	}
	public String getIdUsuarioJugador() {
		return idUsuarioJugador;
	}
	public void setIdUsuarioJugador(String idUsuarioJugador) {
		this.idUsuarioJugador = idUsuarioJugador;
	}

}
