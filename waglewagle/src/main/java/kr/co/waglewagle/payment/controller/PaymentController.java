package kr.co.waglewagle.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.waglewagle.payment.service.PaymentService;

@Controller
public class PaymentController {

	@Autowired
	private PaymentService service;
	
	// 포인트 충전 페이지로 이동
	@GetMapping("/payment")
	public String goPayment() {
		return "payment/payment";
	}
	
//	// 포인트 충전
//	@PostMapping("/payment")
//	public String 
}
