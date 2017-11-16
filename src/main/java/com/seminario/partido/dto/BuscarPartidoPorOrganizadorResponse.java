package com.seminario.partido.dto;

import com.seminario.model.Partido;

public class BuscarPartidoPorOrganizadorResponse {

	private int codigoRespuesta;
	private String mensaje;
	private Partido partido;
	
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
	public Partido getPartido() {
		return partido;
	}
	public void setPartidos(Partido partido) {
		this.partido = partido;
	}
	
}
