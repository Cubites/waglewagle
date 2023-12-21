package kr.co.waglewagle.auctions.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping("/auctions/end/{goods_id}")
	@ResponseBody
	public ResponseEntity<String> auctionEnd(@RequestParam Map<String,Object> paramMap,@AuctionIng Integer goodsId) {
		
		ResponseEntity<String> response=null;
		HttpHeaders header = new HttpHeaders();
		header.add("content-type", "text/plain;charset=UTF-8");
		String msg = "경매 완료에 실패했습니다. 다시 시도해주세요";
		//경매중인 goods가 아닐 시 실패하도록 함 
		//근데 어차피 Exception 터지는거 생각하면 굳이 안잡아도 될듯?
		if(goodsId == null) {
			return new ResponseEntity<String>(msg,header,HttpStatus.BAD_REQUEST);
		}
		
		
		try {
			
			
			boolean deleteresult = service.auctionEnd(paramMap);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(msg,header,HttpStatus.BAD_REQUEST);
		}
		
		
		
		
		return new ResponseEntity<String>("경매를 완료했습니다.",header,HttpStatus.ACCEPTED);
	}
	
	//@PostMapping("/auctions/")
	
}
