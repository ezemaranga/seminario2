package com.seminario.usuario.dto;

import com.seminario.model.Usuario;

public class EditarPerfilUsuarioResponse {
	
	private int codigoRespuesta;
	private String mensaje;
	private Usuario usuario;
	
	public int getCodigoRespuesta() {
		return codigoRespuesta;
	}
	public void setCodigoRespuesta(int codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
