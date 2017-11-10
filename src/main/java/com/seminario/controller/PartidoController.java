package com.seminario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.model.Partido;
import com.seminario.partido.dto.AceptarJugadorRequest;
import com.seminario.partido.dto.AceptarJugadorResponse;
import com.seminario.partido.dto.BuscarPartidoRequest;
import com.seminario.partido.dto.BuscarPartidoResponse;
import com.seminario.partido.dto.CrearPartidoRequest;
import com.seminario.partido.dto.CrearPartidoResponse;
import com.seminario.partido.dto.PostularmePartidoRequest;
import com.seminario.partido.dto.PostularmePartidoResponse;
import com.seminario.repository.PartidoRepository;
import com.seminario.usuario.dto.GetAllPartidosResponse;

@RestController
@RequestMapping("/partidos")
public class PartidoController {
	

	@Autowired
	private PartidoRepository partidoRepository;
	
	@RequestMapping(value = "/buscar", method = RequestMethod.POST)
    public BuscarPartidoResponse buscar(@RequestBody BuscarPartidoRequest request) {
		BuscarPartidoResponse response = new BuscarPartidoResponse();
		
		return response;
    }
	
	@RequestMapping(value = "/creacion", method = RequestMethod.POST)
    public CrearPartidoResponse crear(@RequestBody CrearPartidoRequest request) {
		CrearPartidoResponse response = new CrearPartidoResponse();
		partidoRepository.save(request.getPartido());
		
		response.setCodigoRespuesta(1);
		response.setMensaje("El partido se guardo correctamente.");
		
		return response;
    }
	
	@RequestMapping(value = "/postularme", method = RequestMethod.POST)
    public PostularmePartidoResponse postularme(@RequestBody PostularmePartidoRequest request) {
		PostularmePartidoResponse response = new PostularmePartidoResponse();
		
		return response;
    }
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
    public GetAllPartidosResponse getAll() {
		GetAllPartidosResponse response = new GetAllPartidosResponse();
		List<Partido> partidos = partidoRepository.findAll();
		response.setPartidos(partidos);
        return response;
    }
	
	@RequestMapping(value = "/aceptar", method = RequestMethod.POST)
    public AceptarJugadorResponse postularme(@RequestBody AceptarJugadorRequest request) {
		AceptarJugadorResponse response = new AceptarJugadorResponse();
		
		return response;
    }

}
