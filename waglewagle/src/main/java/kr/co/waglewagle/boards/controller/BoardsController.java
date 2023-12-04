package kr.co.waglewagle.boards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.co.waglewagle.boards.service.BoardsService;

@Controller
public class BoardsController {

	@Autowired
	private BoardsService service;
	
}
