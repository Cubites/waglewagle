package kr.co.waglewagle.admins.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.runtime.Inner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotation.Adapt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.waglewagle.admins.service.NoticeService;
import kr.co.waglewagle.domain.AdminsVO;
//import kr.co.waglewagle.admins.service.ReplyService;
import kr.co.waglewagle.domain.NoticesVO;

@Controller
public class NoticesController {
	@Autowired
	private NoticeService service;

	//공지 목록
//	@GetMapping("/admin/noticelist")
//	public String noticeList(Model model) {
//		System.out.println("여기가 공지목록입니다.");
//		List<NoticesVO> list = service.noticeList();
//		model.addAttribute("list",list); //jsp로 다시 보내주는거지
//		return "/admin/noticelist";
//	}
	
	//공지 페이징 + 검색
	@GetMapping("/admin/noticelist")
	public String noticeSearch(Model model,NoticesVO vo) {
		int count = service.count(vo); // 총개수
        // 총페이지수
        int totalPage = count / 10;
        if (count % 10 > 0) totalPage++;
        
        List<NoticesVO> list = service.noticeList(vo);
        
        // 하단에 페이징처리
        int endPage = (int)(Math.ceil(vo.getPage()/10.0)*10);
        int startPage = endPage - 9;
        if (endPage > totalPage) endPage = totalPage;
        boolean prev = startPage > 1;
        boolean next = endPage < totalPage;
        
        // model에 담음(페이징 정보)
        model.addAttribute("endPage", endPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("prev", prev);
        model.addAttribute("next", next);
		// model에 담음(공지 정보)
		model.addAttribute("list",list);
		return "admin/noticelist";
	}

	
	//공지 등록 화면 띄우기
	@GetMapping("/admin/noticewrite")
	public void getNoticewrite() {}

	//작성한글 등록하기
	@PostMapping("/admin/noticewrite")
	public String postNoticewrtie(NoticesVO vo, HttpServletRequest request,Model model,HttpSession session) {
		
		AdminsVO sess = (AdminsVO) session.getAttribute("admin_info");
		vo.setAdmins_id(sess.getAdmins_id());
//		vo.setAdmins_id(1); //아직 로그인 기능 미완성이라서 admins_id 고정해둔것이고
		service.Noticewrite(vo,request);
		return "redirect:/admin/noticelist";
	}
	
	//공지 상세 내용 보기 ( 주소창 수정 버전 )
	@GetMapping("/admin/noticelist/{notices_id}")
	public String noticeListDetail(Model model, @PathVariable("notices_id") int notices_id) {
//		System.out.println("====>"+notices_id);
		NoticesVO a = service.Noticeview(notices_id);
//		System.out.println("내가 가지고 온 공지내용"+a.getNotices_title());
		model.addAttribute("a",a); //jsp로 보내주느거야아아아
		return "admin/noticeview";
	}

	
	//공지 삭제하기
	@GetMapping("/admin/noticedelete/{notices_id}")
	public String Noticedelete(@PathVariable("notices_id") int notices_id, Model model,HttpServletRequest request) {
		System.out.println("======>"+notices_id);
		service.Noticedelete(notices_id);
		return "redirect:/admin/noticelist";
	}
	
	//공지 수정하기
	@GetMapping("/admin/noticemodify/{notices_id}")
	public String Noticemodify(NoticesVO vo,Model model,@PathVariable("notices_id") int notices_id) {
		NoticesVO a = service.Noticemodify(notices_id);
		model.addAttribute("a",a);
//		model.addAttribute("vo",service.Noticeview(vo,false));
//		System.out.println("===>"+notices_id);
		return "admin/noticemodify";
	}
	
	@PostMapping("/admin/noticeModify")
	public String NoticeModifyUpdate(Model model, NoticesVO vo, HttpServletRequest request) {
		vo.setAdmins_id(1);
		int r = service.NoticeModifyUpdate(vo,request);
		model.addAttribute(request);
		return "redirect:/admin/noticelist";
	}


}