package com.seminario.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "partidos")
public class Partido {
	
	@Id
    public String id;
	
	private String tipo;
	private String idUsuarioOrganizador;
	private String idUsuarioJugador;
	private Date fechaHora;
	private String superficie;
	private String direccion;
	private boolean jugado;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIdUsuarioOrganizador() {
		return idUsuarioOrganizador;
	}

	public void setIdUsuarioOrganizador(String idUsuarioOrganizador) {
		this.idUsuarioOrganizador = idUsuarioOrganizador;
	}

	public String getIdUsuarioJugador() {
		return idUsuarioJugador;
	}

	public void setIdUsuarioJugador(String idUsuarioJugador) {
		this.idUsuarioJugador = idUsuarioJugador;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getSuperficie() {
		return superficie;
	}

	public void setSuperficie(String superficie) {
		this.superficie = superficie;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public boolean isJugado() {
		return jugado;
	}

	public void setJugado(boolean jugado) {
		this.jugado = jugado;
	}

}
