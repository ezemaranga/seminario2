package com.seminario.partido.dto;

import java.util.List;

import com.seminario.model.Partido;
import com.seminario.model.Usuario;

public class PostulacionesPartidoResponse {
	
	private int codigoRespuesta;
	private String mensaje;
	private Partido partido;
	private List<Usuario> postulados;
	
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
	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	public List<Usuario> getPostulados() {
		return postulados;
	}
	public void setPostulados(List<Usuario> postulados) {
		this.postulados = postulados;
	}
	
}
