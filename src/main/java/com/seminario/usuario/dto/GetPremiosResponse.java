package com.seminario.usuario.dto;

import java.util.List;

import com.seminario.model.Premio;

public class GetPremiosResponse {
	
	private List<Premio> premios;

	public List<Premio> getPremios() {
		return premios;
	}

	public void setPremios(List<Premio> premios) {
		this.premios = premios;
	}

}
