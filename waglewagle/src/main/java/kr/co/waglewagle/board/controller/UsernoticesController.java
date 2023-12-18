package kr.co.waglewagle.board.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.waglewagle.board.service.UsernoticesSerivce;
//import kr.co.waglewagle.admins.service.ReplyService;
import kr.co.waglewagle.domain.NoticesVO;

@Controller
public class UsernoticesController {
	
//	@GetMapping("/board/noticelist")
//	public String index(Model model,NoticesVO vo) {
//		//서비스 호출하고 map return 받을거니까...
////		model.addAttribute("map",service.list(vo));
//		return "/board/noticelist";
//	}
	
	@Autowired
	private UsernoticesSerivce service;

	//공지 목록 
	@GetMapping("/board/noticelist")
	public String noticeList(Model model) {
//		System.out.println("여기가 공지목록입니다.");
		List<NoticesVO> list = service.noticeList();
		model.addAttribute("list",list); //jsp로 다시 보내주는거지
		
		return "/board/noticelist";
	}
	
	//공지 상세 내용 보기 ( 주소창 수정 버전 )
	@GetMapping("/board/noticelist/{notices_id}")
	public String noticeListDetail(Model model, @PathVariable("notices_id") Integer notices_id) {
//		System.out.println("====>"+notices_id);
		NoticesVO a = service.Noticeview(notices_id);
//		System.out.println("내가 가지고 온 공지내용"+a.getNotices_title());
		model.addAttribute("a",a); //jsp로 보내주느거야아아아
		return "board/noticeview";
	}
	
//	//공지 등록 화면 띄우기
//	@GetMapping("/admin/noticewrite")
//	public void getNoticewrite() {}
//
//	@PostMapping("/admin/noticewrite")
//	public String postNoticewrtie(NoticesVO vo, HttpServletRequest request,Model model) {
//		//System.out.println("--> "+vo.getNotices_title());
//		vo.setAdmins_id(1); //아직 로그인 기능 미완성이라서 admins_id 고정해둔것이고
//		service.Noticewrite(vo,request);
//		return "redirect:/admin/noticelist";
//	}

	
}