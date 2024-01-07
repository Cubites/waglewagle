package kr.co.waglewagle.example;

import org.springframework.stereotype.Controller;

import kr.co.waglewagle.users.service.UsersService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TestSample1 {
	
	private UsersService service;
	
	/*
	 * Autowired 어노테이션 이외에 service bean 주입 방법 1
	 * @RequiredArgsConstructor 어노테이션 사용
	 * 필드에 Autowired를 사용하는 것 보다 테스트에 용이
	 */
}
