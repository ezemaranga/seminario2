package com.seminario.partido.dto;

import java.util.List;

import com.seminario.model.Postulacion;

public class PostulacionesPartidoResponse {
	
	private int codigoRespuesta;
	private String mensaje;
	private List<Postulacion> postulaciones;
	
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
	public List<Postulacion> getPostulaciones() {
		return postulaciones;
	}
	public void setPostulaciones(List<Postulacion> postulaciones) {
		this.postulaciones = postulaciones;
	}

}
