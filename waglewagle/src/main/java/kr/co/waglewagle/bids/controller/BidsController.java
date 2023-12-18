package kr.co.waglewagle.bids.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.co.waglewagle.bids.service.BidsService;

@Controller
public class BidsController {

	@Autowired
	private BidsService service;
	
}
