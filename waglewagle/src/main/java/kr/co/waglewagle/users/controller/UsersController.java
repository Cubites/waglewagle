package kr.co.waglewagle.users.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.waglewagle.users.service.UsersService;


@Controller
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	// 테스트용 세션 추가
	@GetMapping("/add/session/{users_id}")
	public String usersInfo(HttpSession sess, @PathVariable("users_id") Integer users_id) {
		sess.setAttribute("users_info", service.userInfo(users_id));
		return "home";
	}
	
	// 비밀번호 변경 시, 비밀번호가 맞는지 여부 확인
	@ResponseBody
	@PostMapping("/pwd/change")
	public String pwdChange() {
		
		return "";
	}
}
