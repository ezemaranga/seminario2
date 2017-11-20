package com.seminario.model;

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
	private String direccion;
	private ValoracionJugador ataja;
	private ValoracionJugador defensa;
	private ValoracionJugador ataque;
	private ValoracionJugador estado;
	private ValoracionJugador habilidad;
	private ValoracionJugador tactica;
	private int reputacion;
	private String video;

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
	public int getReputacion() {
		return reputacion;
	}
	public void setReputacion(int reputacion) {
		this.reputacion = reputacion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	
}
