package kr.co.waglewagle.auctions.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping("/auctions/end/{goods_id}")
	@ResponseBody
	public ResponseEntity<String> auctionEnd(@RequestParam Map<String,Integer> paramMap,@AuctionIng Integer goodsId) {
		System.out.println(paramMap + "파라미터맵");
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
			boolean result = service.auctionEnd(paramMap);
		} catch (Exception e) {
			return new ResponseEntity<String>(msg,header,HttpStatus.BAD_REQUEST);
		}
		
		
		
		
		return new ResponseEntity<String>("경매를 완료했습니다.",header,HttpStatus.ACCEPTED);
	}
	
	//@PostMapping("/auctions/")
	
}
