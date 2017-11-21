package com.seminario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/websocket")
public class WebSocketTest {
	@Autowired
	private SimpMessagingTemplate template;
	
	@RequestMapping(value = "/sendMessageToUser")
	public String test(@RequestParam String userId) {
		template.convertAndSend("/topic/user" + userId, "{\"test\":\"testing\"}");
		//template.convertAndSendToUser(username, "/queue/reply", "{\"test\":\"testing\"}");
		return "Test";
	}
}
