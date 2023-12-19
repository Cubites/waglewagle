package kr.co.waglewagle.admins.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.waglewagle.domain.AdminsVO;
//import kr.co.waglewagle.admins.service.ReplyService;

@Controller
public class LoginController {
	

//	@Autowired
//	private LoginService service;
//	
	
//	@GetMapping("/admin/join")
//	public String join() {
//		return "admin/join";
//	}

	
//	@PostMapping("/admin/join")
//	public String Adminjoin(AdminsVO vo, Model model) {
//		boolean r = service.Adminjoin(vo); // service -> mapper -> sql
//		if (r) { // 정상적으로 DB에 insert 
//			model.addAttribute("cmd", "move");
//			model.addAttribute("msg", "회원가입되었습니다.");
//			model.addAttribute("url", "/project/index.do");
//		} else { // 등록안됨
//			model.addAttribute("cmd", "back");
//			model.addAttribute("msg", "회원가입실패");
//		}
//		return "admin/alert";
//	}
//	
//	@GetMapping("/admin/login")
//	public String login() {
//		return "admin/login";
//	}
//	
//	@PostMapping("/admin/login")
//	public String loginProcess(AdminsVO vo, HttpSession sess, Model model) {
//		AdminsVO login = service.login(vo);
//		if (login == null) { // 로그인실패
//			model.addAttribute("msg", "아이디 비밀번호가 올바르지 않습니다.");
//			model.addAttribute("cmd", "back");
//			return "common/alert";
//		} else { // 로그인성공
//			sess.setAttribute("loginInfo", login);
//			return "redirect:/admin/noticelist";
//		}
//	}
//	
//	@GetMapping("/admin/logout")
//	public String logout(Model model, HttpSession sess) {
////		sess.removeAttribute("loginInfo");
//		sess.invalidate();
//		model.addAttribute("msg", "로그아웃되었습니다.");
//		model.addAttribute("url", "/project/index.do");
//		model.addAttribute("cmd", "move");
//		return "admin/alert";
//	}
	
	
}