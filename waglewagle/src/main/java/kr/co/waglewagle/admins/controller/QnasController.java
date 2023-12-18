package kr.co.waglewagle.admins.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.co.waglewagle.admins.service.QnaService;
import kr.co.waglewagle.domain.NoticesVO;
import kr.co.waglewagle.domain.QnasVO;
//import kr.co.waglewagle.admins.service.ReplyService;

@Controller
public class QnasController {
	
	@Autowired
	private QnaService service;

	//문의 목록 
	@GetMapping("/admin/qnalist")
	public String qnaString(Model model) {
		System.out.println("여기는 문의목록입니다..");
		List<QnasVO> list = service.QnaList();
		model.addAttribute("list",list); 
		return "/admin/qnalist";
	}
	
	//문의 내용 보기
	@GetMapping("/admin/qnalist/{qnas_id}")
	public String qnaListDetail(Model model, @PathVariable("qnas_id") int qnas_id) {
		QnasVO a = service.Qnaview(qnas_id);
		model.addAttribute("a",a); //jsp로
		return "admin/qnaview";
	}
	
	//문의 삭제하기
	@GetMapping("/admin/qnadelete/{qnas_id}")
	public String qnaDelete(@PathVariable("qnas_id") int qnas_id,Model model,HttpServletRequest request) {
		service.qnaDelete(qnas_id);
		return "redirect:/admin/qnalist";
	}
	
	
}