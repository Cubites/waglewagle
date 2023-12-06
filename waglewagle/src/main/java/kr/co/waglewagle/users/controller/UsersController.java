package kr.co.waglewagle.users.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.waglewagle.domain.UsersVO;
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
	
	// 회원가입 페이지 이동
	@GetMapping("/join")
	public String joinForm() {
		return "users/join";
	}
	
	// 회원가입 정보 제출 후 login page 이동
	@PostMapping("/join")
	public String joinProcess(UsersVO vo) {
		boolean r = service.join(vo);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "users/login";
	}
}
