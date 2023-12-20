package kr.co.waglewagle.auctions.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.waglewagle.auctions.service.AuctionsService;
import kr.co.waglewagle.auctions.won.AuctionIng;
import kr.co.waglewagle.domain.ReportsVO;
import kr.co.waglewagle.domain.UsersVO;

@Controller
public class AuctionsController {

	@Autowired
	private AuctionsService service;
	
	@GetMapping("/auctions/{goods_id}")
	public String goAuctions(@SessionAttribute("users_info") UsersVO loginUser,HttpSession session,
			@AuctionIng Integer goods_id, Model model) {
		if(goods_id == null) {
			return "";
		}
		Map<String, String> vo = service.getAuctionIngGoods(loginUser.getUsers_id(), goods_id);
		if (vo == null) {
			return "";
		}
		// 세션에 판매자 id 저장
		session.setAttribute("auctions_ing_seller", vo.get("auctions_ing_seller"));
		model.addAttribute("auction_ing_info", vo);
		return "auctions/mobileAuctions";
	}
	
	@ResponseBody
	@PostMapping("/auctions/{goods_id}")
	public String completeAuction(@PathVariable("goods_id") int goods_id) {
		// 거래 완료 처리하기
		service.completeAuction(goods_id);
		System.out.println();
		System.out.println("여기 안옴?????");
		System.out.println();
		return "";
	}
	
	@GetMapping("/auctions/complete")
	public String goAuctionsComplete() {
		return "auctions/mobileAuctionsComplete";
	}
	
	@GetMapping("/auctions/report/{goods_id}")
	public String goAuctionsReport(@SessionAttribute("users_info") UsersVO loginUser, @SessionAttribute("auctions_ing_seller") int seller_id,
			@PathVariable("goods_id") int goods_id, Model model) {
		return "auctions/mobileAuctionsReport";
	}
	
	@ResponseBody
	@PostMapping("/auctions/report/{goods_id}")
	public String saveAuctionsReport(@SessionAttribute("users_info") UsersVO loginUser, @SessionAttribute("auctions_ing_seller") int seller_id,
			@PathVariable("goods_id") int goods_id, HttpServletRequest request) {
		ReportsVO vo = new ReportsVO();
		vo.setGoods_id(goods_id);
		vo.setUsers_id(seller_id);
		vo.setReports_content(request.getParameter("report_data"));
		// 신고하는 동작 실행
		if (!service.saveReport(vo)) {
			return "no";
		} else {
			return "ok";			
		}
	}
	
	@GetMapping("/auctions/report/complete")
	public String goAuctionsReportComplete() {
		return "auctions/mobileAuctionsReportComplete";
	}
}
