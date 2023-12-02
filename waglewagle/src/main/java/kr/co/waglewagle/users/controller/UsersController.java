package kr.co.waglewagle.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.waglewagle.users.service.UsersService;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	@GetMapping("/users/info")
	public String usersInfo(Model model, Integer user_id) {
		System.out.println("user_id: " + user_id);
		model.addAttribute("userInfo", service.userInfo(user_id));
		return "mypage/main";
	}
	
	@GetMapping("/mypage")
	public String mypage(Model model) {
		return "mypage/main";
	}
}
