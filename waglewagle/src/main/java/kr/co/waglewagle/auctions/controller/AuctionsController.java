package kr.co.waglewagle.auctions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.waglewagle.auctions.service.AuctionsService;

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
	
	@GetMapping("/auctions/report")
	public String goAuctionsReport() {
		return "auctions/mobileAuctionsReport";
	}
	
	@GetMapping("/auctions/report/complete")
	public String goAuctionsReportComplete() {
		return "auctions/mobileAuctionsReportComplete";
	}
}
