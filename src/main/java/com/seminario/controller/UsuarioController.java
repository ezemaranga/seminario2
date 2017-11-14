package com.seminario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.model.Partido;
import com.seminario.model.Premio;
import com.seminario.model.PremioNombre;
import com.seminario.model.Usuario;
import com.seminario.repository.PartidoRepository;
import com.seminario.repository.PremioRepository;
import com.seminario.repository.UserRepository;
import com.seminario.usuario.dto.EditarPerfilUsuarioRequest;
import com.seminario.usuario.dto.EditarPerfilUsuarioResponse;
import com.seminario.usuario.dto.GetAllUsuariosResponse;
import com.seminario.usuario.dto.GetPremiosResponse;
import com.seminario.usuario.dto.LoginUsuarioRequest;
import com.seminario.usuario.dto.LoginUsuarioResponse;
import com.seminario.usuario.dto.RegistroUsuarioRequest;
import com.seminario.usuario.dto.RegistroUsuarioResponse;
import com.seminario.usuario.dto.ValorarUsuarioRequest;
import com.seminario.usuario.dto.ValorarUsuarioResponse;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PremioRepository premioRepository;
	
	@Autowired
	private PartidoRepository partidoRepository;
	
	@RequestMapping(value = "/registracion", method = RequestMethod.POST)
    public RegistroUsuarioResponse registrar(@RequestBody RegistroUsuarioRequest request) {
		RegistroUsuarioResponse response = new RegistroUsuarioResponse();
		
		Usuario usuario = new Usuario();
		usuario.setUsername(request.getUsername());
		usuario.setEmail(request.getEmail());
		usuario.setPassword(request.getPassword());
		userRepository.save(usuario);
		
		response.setCodigoRespuesta(1);
		response.setMensaje("OK");
        return response;
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginUsuarioResponse login(@RequestBody LoginUsuarioRequest request) {
		LoginUsuarioResponse response = new LoginUsuarioResponse();
		
		Usuario usuario = userRepository.findByEmail(request.getEmail());
		if (usuario != null) {
			if(usuario.getPassword().equals(request.getPassword())) {
				response.setUsuario(usuario);
				response.setCodigoRespuesta(1);
				response.setMensaje("Usuario logueado exitosamente.");
			} else {
				response.setCodigoRespuesta(2);
				response.setMensaje("La clave es incorrecta.");
			}
		} else {
			response.setCodigoRespuesta(3);
			response.setMensaje("El email no se encuentra registrado.");
		}
		
        return response;
    }
	
	@RequestMapping(value = "/perfil", method = RequestMethod.POST)
    public EditarPerfilUsuarioResponse editarPerfil(@RequestBody EditarPerfilUsuarioRequest request) {
		EditarPerfilUsuarioResponse response = new EditarPerfilUsuarioResponse();
		
		userRepository.save(request.getUsuario());
		response.setCodigoRespuesta(4);
		response.setMensaje("OK");
		
        return response;
    }
	
	@RequestMapping(value = "/valorar", method = RequestMethod.POST)
    public ValorarUsuarioResponse valorar(@RequestBody ValorarUsuarioRequest request) {
		ValorarUsuarioResponse response = new ValorarUsuarioResponse();

		Partido partido = partidoRepository.findById(request.getIdPartido());
		partido.setJugado(request.isJugado());
		partidoRepository.save(partido);
		
		Usuario usuario = userRepository.findById(partido.getIdUsuarioJugador());
		
		
		List<Partido> partidos = partidoRepository.findByIdUsuarioJugador(partido.getIdUsuarioJugador());
		
		int nuevaReputacion = calcularReputacion(partidos.size(), usuario.getReputacion(), request.getReputacion());
		usuario.setReputacion(nuevaReputacion);
		userRepository.save(usuario);
		
		if (partidos != null) { 
			PremioNombre premioNombre = null;
			boolean nuevoPremio = false;
			if (partidos.size() == 1) {
				premioNombre = PremioNombre.DEBUTANTE;
				nuevoPremio = true;
			} else if (partidos.size() == 10 && usuario.getReputacion() >= 6) {
				premioNombre = PremioNombre.CUMPLIDOR;
				nuevoPremio = true;
			} else if (partidos.size() == 20 && usuario.getReputacion() >= 8) {
				premioNombre = PremioNombre.CRACK_MUNDIAL;
				nuevoPremio = true;
			}
			
			if(nuevoPremio) {
				Premio premio = new Premio();
				premio.setIdUsuario(partido.getIdUsuarioJugador());
				premio.setPremioNombre(premioNombre);
				premioRepository.save(premio);
			}
		}
		
		response.setCodigoRespuesta(5);
		response.setMensaje("OK");
        return response;
    }
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
    public GetAllUsuariosResponse getAll() {
		GetAllUsuariosResponse response = new GetAllUsuariosResponse();
		List<Usuario> usuarios = userRepository.findAll();
		response.setUsuarios(usuarios);
        return response;
    }
	
	@RequestMapping(value = "/removeAll", method = RequestMethod.GET)
    public void removeAll() {
		userRepository.deleteAll();
    }
	
	@RequestMapping(value = "/premios", method = RequestMethod.GET)
    public GetPremiosResponse getPremios(@RequestParam String usuarioId) {
		GetPremiosResponse response = new GetPremiosResponse();
		response.setPremios(premioRepository.findByIdUsuario(usuarioId));
		return response;
    }

	private int calcularReputacion(int cantPartidos, int reputacionActual, int nuevaValoracion) {
		int nuevaReputacion = (((cantPartidos - 1) * reputacionActual) + nuevaValoracion) / cantPartidos;
		return nuevaReputacion;		
	}
	
	
}
