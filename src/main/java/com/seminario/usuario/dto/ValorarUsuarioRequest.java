package com.seminario.usuario.dto;


public class ValorarUsuarioRequest {
	
	private String idPartido;
	private boolean jugado;
	private int reputacion;
	
	public int getReputacion() {
		return reputacion;
	}
	public void setReputacion(int reputacion) {
		this.reputacion = reputacion;
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
