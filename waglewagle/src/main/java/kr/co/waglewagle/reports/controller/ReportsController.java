package kr.co.waglewagle.reports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.co.waglewagle.reports.service.ReportsService;

@Controller
public class ReportsController {

	@Autowired
	private ReportsService service;
	
}
