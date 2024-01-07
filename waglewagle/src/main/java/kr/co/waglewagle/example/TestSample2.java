package kr.co.waglewagle.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.co.waglewagle.users.service.UsersService;

@Controller
public class TestSample2 {
	
	private UsersService service;
	
	@Autowired
	public TestSample2(UsersService service) {
		this.service = service;
	}
	
	/*
	 * Autowired 어노테이션 이외에 service bean 주입 방법 2
	 * 생성자에 Autowired 어노테이션을 사용
	 * 필드에 Autowired를 사용하는 것 보다 테스트에 용이
	 */
}
