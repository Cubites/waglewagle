package kr.co.waglewagle.users.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.waglewagle.users.service.UsersService;


@Controller
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	// 테스트용 세션 추가
	@GetMapping("/add/session")
	public String usersInfo(HttpSession sess) {
		sess.setAttribute("users_info", service.userInfo(1));
		return "home";
	}
}
