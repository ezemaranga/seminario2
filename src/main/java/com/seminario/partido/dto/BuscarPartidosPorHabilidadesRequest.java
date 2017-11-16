package com.seminario.partido.dto;

import java.util.List;

public class BuscarPartidosPorHabilidadesRequest {
	private List<String> habilidades;

	public List<String> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<String> habilidades) {
		this.habilidades = habilidades;
	}
}
