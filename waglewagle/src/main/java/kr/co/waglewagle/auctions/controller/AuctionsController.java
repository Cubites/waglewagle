package kr.co.waglewagle.auctions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.co.waglewagle.auctions.service.AuctionsService;

@Controller
public class AuctionsController {

	@Autowired
	private AuctionsService service;
	
}
