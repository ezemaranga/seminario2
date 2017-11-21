package com.seminario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.model.Partido;
import com.seminario.repository.PartidoRepository;

@RestController
@RequestMapping("/websocket")
public class WebSocketMessageSender {
	@Autowired
	private SimpMessagingTemplate template;
	
	@Autowired
	private PartidoRepository partidoRepository;
	
	@RequestMapping(value = "/acceptUserForMatch")
	public Partido acceptUserForMatch(@RequestParam String userId, @RequestParam String matchId) {
		//Envio al usuario: http://localhost:8080/websocket/acceptUserForMatch?userId=5a131aeaeb557505e1cd4f34&matchId=test
		Partido p = partidoRepository.findById("5a13605fa92f1718c0b43f6a"); //Harcode hasta que ande esto
		template.convertAndSend("/topic/user" + userId, p);
		return p;
	}
	
	@RequestMapping(value = "/refreshMatches")
	public String refreshMatches() {
		template.convertAndSend("/topic/all", "{}");
		return "Refresh matches";
	}
	
}
