package kr.co.waglewagle.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.co.waglewagle.goods.service.GoodsService;

@Controller
public class GoodsController {

	@Autowired
	private GoodsService service;
	
}
