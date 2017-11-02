package com.seminario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.model.Usuario;
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
	
	@RequestMapping(value = "/registracion", method = RequestMethod.POST)
    public RegistroUsuarioResponse registrar(@RequestBody RegistroUsuarioRequest request) {
		RegistroUsuarioResponse response = new RegistroUsuarioResponse();
		
		Usuario usuario = new Usuario();
		usuario.setUsername(request.getUsername());
		usuario.setEmail(request.getEmail());
		usuario.setPassword(request.getPassword());
		userRepository.save(usuario);
		
		response.setCodigoRespuesta(1);
		response.setMensaje("Usuario registrado correctamente.");
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
		response.setMensaje("El usuario se guardo correctamente.");
		
        return response;
    }
	
	@RequestMapping(value = "/valorar", method = RequestMethod.POST)
    public ValorarUsuarioResponse valorar(@RequestBody ValorarUsuarioRequest request) {
		ValorarUsuarioResponse response = new ValorarUsuarioResponse();
		
        return response;
    }
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
    public GetAllUsuariosResponse getAll() {
		GetAllUsuariosResponse response = new GetAllUsuariosResponse();
		List<Usuario> usuarios = userRepository.findAll();
		response.setUsuarios(usuarios);
        return response;
    }
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
    public void remove() {
		userRepository.deleteAll();
    }
	
	@RequestMapping(value = "/premios", method = RequestMethod.GET)
    public GetPremiosResponse getPremios(@RequestParam String usuarioId) {
		GetPremiosResponse response = new GetPremiosResponse();
		response.setPremios(premioRepository.findByIdUsuario(usuarioId));
		return response;
    }

}
