package kr.co.waglewagle.users.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.waglewagle.domain.CategoryVO;
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
	
	@GetMapping("/mypage")
	public String mypage(Model model) {
		Map<Integer, String> categoryFirst = new LinkedHashMap<>();
		Map<Integer, List<String>> categorySecond = new HashMap<>();
		List<CategoryVO> categoryData = service.allCategory();
		
		for(CategoryVO vo : categoryData) {
			if(vo.getCategory_upper_id() == null) {
				categoryFirst.put(vo.getCategory_id(), vo.getCategory_name());
				categorySecond.put(vo.getCategory_id(), new ArrayList<String>());
			} else {
				categorySecond.get(vo.getCategory_upper_id()).add(vo.getCategory_name());
			}
		}
		
		model.addAttribute("categoryFirst", categoryFirst);
		model.addAttribute("categorySecond", categorySecond);
		
		return "mypage/main";
	}
}
