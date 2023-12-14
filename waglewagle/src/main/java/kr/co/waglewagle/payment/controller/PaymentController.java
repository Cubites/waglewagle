package kr.co.waglewagle.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.co.waglewagle.payment.service.PaymentService;

@Controller
public class PaymentController {

	@Autowired
	private PaymentService service;
	
	
}
