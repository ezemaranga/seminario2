package com.seminario.partido.dto;

import java.util.HashMap;

public class BuscarPartidosPorHabilidadesRequest {
	private HashMap<String,String> habilidades;

	public HashMap<String,String> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(HashMap<String,String> habilidades) {
		this.habilidades = habilidades;
	}
}
