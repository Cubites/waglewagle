package kr.co.waglewagle.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.co.waglewagle.chat.service.ChatService;

@Controller
public class ChatController {

	@Autowired
	private ChatService service;
	
}
