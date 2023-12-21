package kr.co.waglewagle.admins.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.waglewagle.admins.service.UserManageService;
import kr.co.waglewagle.domain.UsersVO;

@Controller
public class UsersManageController {
	@Autowired
	private UserManageService service;

	//문의 목록
	@GetMapping("/admin/userManageList")
	public String userList(Model model) {
//		System.out.println("여기는 문의목록입니다..");
//		List<UsersVO> list = service.userList();
		List<Map<String, Object>> cnt = service.usersReport();
		model.addAttribute("cnt",cnt); 
		System.out.println(cnt.get(0).toString());
		return "/admin/userManageList";
		
	}
	
	//1차로 이렇게
//	@ResponseBody //이 어노테이션이 return값을 바로 jsp로 넘겨주는 엄청난 아이
//	@PostMapping("/admin/usersStatus")
//	public boolean userStatus(Model model,@RequestBody Map<String,Integer> status) {
//		status.get("users_id"); //string타입이니까 문자열로 작성해줘하고
//		return true;
//	}
	
	@ResponseBody  //이 어노테이션이 return값을 바로 jsp로 넘겨주는 엄청난 아이
	@PostMapping("/admin/usersStatus")
	public Integer userStatus(Model model,@RequestBody Map<String,Integer> status) {
		//status.get("users_id"); //string타입이니까 문자열로 작성해줘하고
		int a = service.usersAccess(status);
		return a==1 ? status.get("users_status") : (status.get("users_status")==0 ? 1:0);
	}	
	
	
	
	
	
	
}