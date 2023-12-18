package kr.co.waglewagle.admins.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.co.waglewagle.admins.service.AdminsService;

@Controller
public class AdminsController {

	@Autowired
	private AdminsService service;
	
}
