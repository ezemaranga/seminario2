package com.seminario.usuario.dto;


public class ValorarUsuarioRequest {
	
	private String idPartido;
	private String idUsuarioOrganizador;
	private String idUsuarioJugador;
	private boolean jugado;
	private int reputacion;
	
	public String getIdUsuarioJugador() {
		return idUsuarioJugador;
	}
	public void setIdUsuarioJugador(String idUsuarioJugador) {
		this.idUsuarioJugador = idUsuarioJugador;
	}
	public int getReputacion() {
		return reputacion;
	}
	public void setReputacion(int reputacion) {
		this.reputacion = reputacion;
	}
	public String getIdUsuarioOrganizador() {
		return idUsuarioOrganizador;
	}
	public void setIdUsuarioOrganizador(String idUsuarioOrganizador) {
		this.idUsuarioOrganizador = idUsuarioOrganizador;
	}
	public String getIdPartido() {
		return idPartido;
	}
	public void setIdPartido(String idPartido) {
		this.idPartido = idPartido;
	}
	public boolean isJugado() {
		return jugado;
	}
	public void setJugado(boolean jugado) {
		this.jugado = jugado;
	}

}
