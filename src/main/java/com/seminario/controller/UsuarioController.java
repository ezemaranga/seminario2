package com.seminario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.model.Usuario;
import com.seminario.repository.UserRepository;
import com.seminario.usuario.dto.EditarPerfilUsuarioRequest;
import com.seminario.usuario.dto.EditarPerfilUsuarioResponse;
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
	
	@RequestMapping(value = "/registracion", method = RequestMethod.POST)
    public RegistroUsuarioResponse registrar(@RequestBody RegistroUsuarioRequest request) {
		RegistroUsuarioResponse response = new RegistroUsuarioResponse();
		
		Usuario usuario = new Usuario();
		usuario.setUsername(request.getUsername());
		usuario.setEmail(request.getEmail());
		usuario.setPassword(request.getPassword());
		userRepository.save(usuario);
		
		response.setCodigoRespuesta(1);
		response.setMensaje("Todo ok...");
        return response;
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginUsuarioResponse login(@RequestBody LoginUsuarioRequest request) {
		LoginUsuarioResponse response = new LoginUsuarioResponse();
		
        return response;
    }
	
	@RequestMapping(value = "/perfil", method = RequestMethod.POST)
    public EditarPerfilUsuarioResponse editarPerfil(@RequestBody EditarPerfilUsuarioRequest request) {
		EditarPerfilUsuarioResponse response = new EditarPerfilUsuarioResponse();
		
        return response;
    }
	
	@RequestMapping(value = "/valorar", method = RequestMethod.POST)
    public ValorarUsuarioResponse valorar(@RequestBody ValorarUsuarioRequest request) {
		ValorarUsuarioResponse response = new ValorarUsuarioResponse();
		
        return response;
    }
	

}
