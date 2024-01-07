package kr.co.waglewagle.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.co.waglewagle.admins.service.NoticeService;
import kr.co.waglewagle.board.service.UsernoticesSerivce;
import kr.co.waglewagle.domain.NoticesVO;

@Controller
public class UsernoticesController {
	
	@Autowired
	private UsernoticesSerivce service;
	
	@Autowired
	private NoticeService service2;

	//공지 목록 
	@GetMapping("/board/noticelist")
	public String noticeList(Model model,NoticesVO vo) {
		
		int count = service2.count(vo); // 총개수
        // 총페이지수
        int totalPage = count / 10;
        if (count % 10 > 0) totalPage++;
        
        List<NoticesVO> list = service2.noticeList(vo);
        
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
		
		return "/board/noticelist";
	}
	
	//공지 상세 내용 보기 ( 주소창 수정 버전 )
	@GetMapping("/board/noticelist/{notices_id}")
	public String noticeListDetail(Model model, @PathVariable("notices_id") Integer notices_id) {
		NoticesVO a = service.Noticeview(notices_id);
		model.addAttribute("a",a);
		return "board/noticeview";
	}

}