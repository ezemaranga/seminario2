package com.seminario.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class Usuario {
	
	@Id
    public String id;
	
	private String username;
	private String password;
	private String email;
	private Integer edad;
	private double latitud;
	private double longitud;
	private ValoracionJugador ataja;
	private ValoracionJugador defensa;
	private ValoracionJugador ataque;
	private ValoracionJugador estado;
	private ValoracionJugador habilidad;
	private ValoracionJugador tactica;
	private List<Partido> jugados;
	private List<Partido> organizados;
	private Reputacion reputacion;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public ValoracionJugador getAtaja() {
		return ataja;
	}
	public void setAtaja(ValoracionJugador ataja) {
		this.ataja = ataja;
	}
	public ValoracionJugador getDefensa() {
		return defensa;
	}
	public void setDefensa(ValoracionJugador defensa) {
		this.defensa = defensa;
	}
	public ValoracionJugador getAtaque() {
		return ataque;
	}
	public void setAtaque(ValoracionJugador ataque) {
		this.ataque = ataque;
	}
	public ValoracionJugador getEstado() {
		return estado;
	}
	public void setEstado(ValoracionJugador estado) {
		this.estado = estado;
	}
	public ValoracionJugador getHabilidad() {
		return habilidad;
	}
	public void setHabilidad(ValoracionJugador habilidad) {
		this.habilidad = habilidad;
	}
	public ValoracionJugador getTactica() {
		return tactica;
	}
	public void setTactica(ValoracionJugador tactica) {
		this.tactica = tactica;
	}
	public List<Partido> getJugados() {
		if (jugados == null) {
			jugados = new ArrayList<>();
		}
		return jugados;
	}
	public void setJugados(List<Partido> jugados) {
		this.jugados = jugados;
	}
	public List<Partido> getOrganizados() {
		if (organizados == null) {
			organizados = new ArrayList<>();
		}
		return organizados;
	}
	public void setOrganizados(List<Partido> organizados) {
		this.organizados = organizados;
	}
	public Reputacion getReputacion() {
		return reputacion;
	}
	public void setReputacion(Reputacion reputacion) {
		this.reputacion = reputacion;
	}
	
}
