package kr.co.waglewagle.mainpage.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.waglewagle.domain.GoodsVO;
import kr.co.waglewagle.domain.UsersVO;
import kr.co.waglewagle.mainpage.service.MainpageService;

@Controller
public class MainpageController {

	@Autowired
	private MainpageService service;
	
	@GetMapping("/main")
	public String index(Model model, HttpSession session) {
		model.addAttribute("popular_list", service.popularList());
		model.addAttribute("new_list", service.newRegList());
		if(session.getAttribute("users_info") != null) {
			UsersVO login = (UsersVO)session.getAttribute("users_info");
			List<String> users_addrList = Arrays.asList(login.getUsers_addr_list().split(","));
			model.addAttribute("near_list", service.nearList(users_addrList));
			
			//시구동 -> 동
			for(int i=0; i<users_addrList.size(); i++) {
				users_addrList.set(i, users_addrList.get(i).substring(users_addrList.get(i).lastIndexOf(" ")));
			}
			model.addAttribute("dong_list", users_addrList);
		}else {
			List<String> users_addrList = Arrays.asList("서교동");
			model.addAttribute("dong_list", users_addrList);
			model.addAttribute("near_list", service.nearList(null));
		}
		
		return "mainpage/mainpage";
	}
	
}
