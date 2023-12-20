package kr.co.waglewagle.auctions.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.waglewagle.auctions.service.AuctionsService;
import kr.co.waglewagle.auctions.won.AuctionIng;
import kr.co.waglewagle.domain.ReportsVO;

@Controller
public class AuctionsController {

	@Autowired
	private AuctionsService service;
	
	@GetMapping("/auctions")
	public String goAuctions() {
		return "auctions/mobileAuctions";
	}
	
	@GetMapping("/auctions/complete")
	public String goAuctionsComplete() {
		return "auctions/mobileAuctionsComplete";
	}
	
	@GetMapping("/auctions/{goods_id}/report")
	public String goAuctionsReport(@PathVariable("goods_id") int goods_id) {
		//System.out.println("---> " + goods_id);
		return "auctions/mobileAuctionsReport";
	}
	
	@PostMapping("/auctions/{goods_id}/report")
	@ResponseBody
	public String saveAuctionsReport(@PathVariable("goods_id") int goods_id, HttpServletRequest request) {
		ReportsVO vo = new ReportsVO();
		vo.setGoods_id(goods_id);
		vo.setUsers_id(Integer.parseInt(request.getParameter("users_id")));
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
	
	
	//@PostMapping("/auctions/")
	
}
