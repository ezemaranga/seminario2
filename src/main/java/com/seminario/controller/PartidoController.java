package com.seminario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.model.Partido;
import com.seminario.model.Postulacion;
import com.seminario.partido.dto.AceptarJugadorRequest;
import com.seminario.partido.dto.AceptarJugadorResponse;
import com.seminario.partido.dto.BorrarPartidoRequest;
import com.seminario.partido.dto.BorrarPartidoResponse;
import com.seminario.partido.dto.BuscarPartidoPorOrganizadorRequest;
import com.seminario.partido.dto.BuscarPartidoPorOrganizadorResponse;
import com.seminario.partido.dto.CrearPartidoRequest;
import com.seminario.partido.dto.CrearPartidoResponse;
import com.seminario.partido.dto.PostulacionesPartidoResponse;
import com.seminario.partido.dto.PostularmePartidoRequest;
import com.seminario.partido.dto.PostularmePartidoResponse;
import com.seminario.repository.PartidoRepository;
import com.seminario.repository.PostulacionRepository;
import com.seminario.usuario.dto.GetAllPartidosResponse;

@RestController
@RequestMapping("/partidos")
public class PartidoController {
	

	@Autowired
	private PartidoRepository partidoRepository;
	
	@Autowired
	private PostulacionRepository postulacionRepository;
	
	@RequestMapping(value = "/buscarporid", method = RequestMethod.POST)
    public BuscarPartidoPorOrganizadorResponse buscarPorId(@RequestBody BuscarPartidoPorOrganizadorRequest request) {
		BuscarPartidoPorOrganizadorResponse response = new BuscarPartidoPorOrganizadorResponse();
		Partido partido = partidoRepository.findByIdUsuarioOrganizador(request.getIdUsuarioOrganizador());
		response.setPartidos(partido);
		return response;
    }
	
	@RequestMapping(value = "/creacion", method = RequestMethod.POST)
    public CrearPartidoResponse crear(@RequestBody CrearPartidoRequest request) {
		CrearPartidoResponse response = new CrearPartidoResponse();
		partidoRepository.save(request.getPartido());
		
		response.setCodigoRespuesta(1);
		response.setMensaje("OK");
		
		return response;
    }
	
	@RequestMapping(value = "/postularme", method = RequestMethod.POST)
    public PostularmePartidoResponse postularme(@RequestBody PostularmePartidoRequest request) {
		PostularmePartidoResponse response = new PostularmePartidoResponse();
		
		Postulacion postulacion = new Postulacion();
		postulacion.setIdJugador(request.getIdJugador());
		postulacion.setIdPartido(request.getIdPartido());
		
		postulacionRepository.save(postulacion);
		
		response.setCodigoRespuesta(10);
		response.setMensaje("OK");
		
		return response;
    }
	
	@RequestMapping(value = "/postulaciones", method = RequestMethod.GET)
    public PostulacionesPartidoResponse postulaciones(@RequestParam String idPartido) {
		PostulacionesPartidoResponse response = new PostulacionesPartidoResponse();
		
		List<Postulacion> postulaciones = postulacionRepository.findByIdPartido(idPartido);
		
		response.setCodigoRespuesta(11);
		response.setMensaje("OK");
		response.setPostulaciones(postulaciones);
		
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
		
		Postulacion postulacion = postulacionRepository.findById(request.getIdPostulacion());
		
		Partido partido = partidoRepository.findById(postulacion.getIdPartido());
		partido.setIdUsuarioJugador(postulacion.getIdJugador());
		
		partidoRepository.save(partido);
		
		response.setCodigoRespuesta(12);
		response.setMensaje("OK");
		
		return response;
    }
	
	@RequestMapping(value = "/borrar", method = RequestMethod.POST)
    public BorrarPartidoResponse borrarPartido (@RequestBody BorrarPartidoRequest request) {
		BorrarPartidoResponse response = new BorrarPartidoResponse();
		
		Partido partido  = partidoRepository.findById(request.getIdPartido());
		
		partidoRepository.delete(partido);
		
		response.setCodigoRespuesta(12);
		response.setMensaje("OK");
		
		return response;
    }


}
