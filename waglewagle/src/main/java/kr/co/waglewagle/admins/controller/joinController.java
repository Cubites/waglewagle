package kr.co.waglewagle.admins.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//import kr.co.waglewagle.admins.service.ReplyService;
import kr.co.waglewagle.domain.NoticesVO;

@Controller
public class joinController {
//	@Autowired
//	private NoticeService service;
	
	@GetMapping("/admin/join")
	public String index(Model model,NoticesVO vo) {
		//서비스 호출하고 map return 받을거니까...
//		model.addAttribute("map",service.list(vo));
		return "/admin/join";
	}
	
	
	
}