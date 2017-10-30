package com.seminario.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.partido.dto.AceptarJugadorRequest;
import com.seminario.partido.dto.AceptarJugadorResponse;
import com.seminario.partido.dto.BuscarPartidoRequest;
import com.seminario.partido.dto.BuscarPartidoResponse;
import com.seminario.partido.dto.CrearPartidoResponse;
import com.seminario.partido.dto.PostularmePartidoRequest;
import com.seminario.partido.dto.PostularmePartidoResponse;

@RestController
@RequestMapping("/partidos")
public class PartidoController {
	
	@RequestMapping(value = "/buscar", method = RequestMethod.POST)
    public BuscarPartidoResponse buscar(@RequestBody BuscarPartidoRequest request) {
		BuscarPartidoResponse response = new BuscarPartidoResponse();
		
		return response;
    }
	
	@RequestMapping(value = "/creacion", method = RequestMethod.POST)
    public CrearPartidoResponse crear(@RequestBody BuscarPartidoRequest request) {
		CrearPartidoResponse response = new CrearPartidoResponse();
		
		return response;
    }
	
	@RequestMapping(value = "/postularme", method = RequestMethod.POST)
    public PostularmePartidoResponse postularme(@RequestBody PostularmePartidoRequest request) {
		PostularmePartidoResponse response = new PostularmePartidoResponse();
		
		return response;
    }
	
	@RequestMapping(value = "/aceptar", method = RequestMethod.POST)
    public AceptarJugadorResponse postularme(@RequestBody AceptarJugadorRequest request) {
		AceptarJugadorResponse response = new AceptarJugadorResponse();
		
		return response;
    }

}
