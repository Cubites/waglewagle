package kr.co.waglewagle.users.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.waglewagle.domain.UsersVO;
import kr.co.waglewagle.users.service.UsersService;
import kr.co.waglewagle.users.ty.mailauth.MailService;


@Controller
public class UsersController {
	
	@Autowired
	private UsersService service;
	@Autowired
	private MailService ms;
	
	//MailConfig mailConfig = new MailConfig();
	//MailService ms = new MailService(mailConfig.javaMailSender());
	
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
		return "redirect:login";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "users/login";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, UsersVO vo) {
		//db에 있는 정보
		UsersVO login = service.login(vo);
		boolean isValid = false;
		
		if(login == null) {
			System.out.println("회원정보없음");
		}else {
			System.out.println("회원정보있음");
			isValid = BCrypt.checkpw(vo.getUsers_pwd(), login.getUsers_pwd()); 
		}
		
		
		if(isValid) {
			System.out.println("비번맞음");
			session.setAttribute("users_info", login);
			return "redirect:/index";
		}else {
			System.out.println("비번틀림");
			return "redirect:/login";
		}
	}
	
	@ResponseBody
	@GetMapping("/emaildup")
	public String emailDupCheck(String users_email) {
		return String.valueOf(service.isEmailDup(users_email));
	}
	

	@ResponseBody
	@PostMapping("/send_authnum")
	public String sendAuthNum(HttpSession session, String users_email) {
		try {
			String authNum = ms.sendSimpleMessage(users_email);
			session.setAttribute("authNum", authNum);
			return authNum;
		} catch(Exception e) {
			e.printStackTrace();
			return "FAILED";
		}
	}
	
	@ResponseBody
	@PostMapping("/check_authnum")
	public String checkAuthNum(HttpSession session, String validNum){
		String authNum = (String)session.getAttribute("authNum");

		return validNum.equals(authNum) ? "true" : "false";
	}
	
	@ResponseBody
	@GetMapping("/nickcheck")
	public String nickCheck(String users_nick) {
		return String.valueOf(service.isNickDup(users_nick));
	}
	
	
	@GetMapping("find_info")
	public String findInfo() {
		return "users/findInfo";
	}
	
	@PostMapping("find_id")
	public String findId(Model model, String users_name, String users_phone ) {
		Map<String, String> user_info = new HashMap<>();
		user_info.put("users_name", users_name);
		user_info.put("users_phone", users_phone);
		
		UsersVO vo = service.findId(user_info);
		if(vo == null) {
			model.addAttribute("users_email", "NOT_EXIST");
		}else {
			String users_email = vo.getUsers_email();
			int atIdx = users_email.indexOf("@");
			String maskedEmail = users_email.substring(0,3) + "*****" + users_email.substring(atIdx); 
			System.out.println(maskedEmail);
			model.addAttribute("users_email", maskedEmail);
		}
		model.addAttribute("result_type", "find_id");
		
		return "users/findResult";
	}
	@PostMapping("find_pwd")
	public String findPwd(Model model, String users_name, String users_email ) {
		Map<String, String> user_info = new HashMap<>();
		user_info.put("users_name", users_name);
		user_info.put("users_email", users_email);
		
		UsersVO vo = service.findPwd(user_info);
		if(vo == null) {
			model.addAttribute("result_type", "find_pwd");
			return "users/findResult";
		}else {
			model.addAttribute("vo", vo);
			return "users/changePwd";
		}
		
	}

	@GetMapping("find_result")
	public String findResult() {
		return "users/findResult";
	}
	
	@PostMapping("change_pwd")
	public void changePwd(HttpServletResponse response, Model model, String users_pwd, String users_id ) throws IOException{
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		Map<String, String> user_info = new HashMap<>();
		user_info.put("users_pwd", users_pwd);
		user_info.put("users_id", users_id);
		boolean pwdIsChanged = service.changePwd(user_info);
		System.out.println("비밀번호 변경됨? : " + pwdIsChanged);
		if(pwdIsChanged) {
			out.println("<script>alert('비밀번호가 변경되었습니다.');"+ "location.href='login';</script>");
			out.flush();
		}else {
			out.println("<script>alert('비밀변호 변경에 실패했습니다.');"+ "location.href='#';</script>");
			out.flush();
		}
	}

	
}
