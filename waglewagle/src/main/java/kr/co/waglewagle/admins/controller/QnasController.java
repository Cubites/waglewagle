package kr.co.waglewagle.admins.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.waglewagle.admins.service.QnaService;
import kr.co.waglewagle.domain.NoticesVO;
import kr.co.waglewagle.domain.QnasVO;

@Controller
public class QnasController {
	
	@Autowired
	private QnaService service;

	//문의 목록
	@GetMapping("/admin/qnalist")
	public String qnaString(Model model) {
		//System.out.println("여기는 문의목록입니다..");
		List<QnasVO> list = service.QnaList();
		model.addAttribute("list",list); 
		return "/admin/qnalist";
	}
	
	//문의 내용 및 답글 보기
	@GetMapping("/admin/qnalist/{qnas_id}")
	public String qnaListDetail(Model model, @PathVariable("qnas_id") int qnas_id) {
		QnasVO a = service.Qnaview(qnas_id);
		model.addAttribute("a",a); //jsp로
		return "admin/qnaview";
	}
	
	//문의 삭제하기
	@GetMapping("/admin/qnaDelete/{qnas_id}")
	public String qnaDelete(@PathVariable("qnas_id") int qnas_id, Model model,HttpServletRequest request) {
		service.qnaDelete(qnas_id);
		return "redirect:/admin/qnalist";
	}
	
	//답글 작성하기
//	@PostMapping("/admin/qnaReply/{qnas_id}")
//	public String qnaReply(@PathVariable("qnas_id") int qnas_id,Model model,HttpServletRequest request) {
//		int r = service.qnaReply(vo.request);
//		return "reditect:/admin/qnalsit/"+vo.getQnas_id();
//	}
	
	//답글 삭제하기
	@GetMapping("/admin/delReply")
	public String delReply(Model model,QnasVO vo,HttpServletRequest request) {
		int r = service.delReply(vo,request);
		return "redirect:/admin/qnalist/"+vo.getQnas_id();
	}
	
	
	//답글 작성하기 
	@PostMapping("/admin/writeReply/{qnas_id}")
	public String writeReply(Model model,QnasVO vo, HttpServletRequest request,@PathVariable("qnas_id") int qnas_id) {
		vo.setAdmins_id(1); //로그인x
		service.writeReply(vo,request);
		return "redirect:/admin/qnalist/"+qnas_id;
	}
	
	
	@PostMapping("/admin/modifyReply/{qnas_id}")
	public String modifyReply(Model model,QnasVO vo, HttpServletRequest request,@PathVariable("qnas_id") int qnas_id) {
		vo.setAdmins_id(1); //로그인 기능x
		service.writeReply(vo,request);
		return "reditect:/admin/qnalist/"+qnas_id;
	}
	
	
	
}