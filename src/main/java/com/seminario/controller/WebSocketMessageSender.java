package com.seminario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.model.Calificacion;
import com.seminario.model.Partido;
import com.seminario.model.Usuario;
import com.seminario.repository.PartidoRepository;
import com.seminario.repository.UserRepository;

@RestController
@RequestMapping("/websocket")
public class WebSocketMessageSender {
	@Autowired
	private SimpMessagingTemplate template;
	
	@Autowired
	private PartidoRepository partidoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/acceptUserForMatch")
	public Partido acceptUserForMatch(@RequestParam String userId, @RequestParam String matchId) {
		//Envio al usuario: http://localhost:8080/websocket/acceptUserForMatch?userId=5a131aeaeb557505e1cd4f34&matchId=test
		Partido p = partidoRepository.findById(matchId);
		template.convertAndSend("/topic/user" + userId, p);
		return p;
	}
	
	@RequestMapping(value = "/showReview")
	public Calificacion showReview(@RequestParam String userId,@RequestParam String calificacion) {
		Usuario usuario = userRepository.findById(userId);
		Calificacion c = new Calificacion(usuario,calificacion);
		template.convertAndSend("/topic/user/review" + userId, c);
		return c;
	}
	
	@RequestMapping(value = "/refreshMatches")
	public String refreshMatches(@RequestParam String userId) {
		template.convertAndSend("/topic/all" + userId, "{ \"test\" : \"test\"}");
		return "Refresh matches";
	}
	
}
