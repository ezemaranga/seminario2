package com.seminario.usuario.dto;

import java.util.List;

import com.seminario.model.Partido;

public class GetAllPartidosResponse {

	private int codigoRespuesta;
	private String mensaje;
	private List<Partido> partidos;
	
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
	public List<Partido> getPartidos() {
		return partidos;
	}
	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}
	
}
