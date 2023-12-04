package kr.co.waglewagle.reports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.waglewagle.reports.mapper.ReportsMapper;

@Service
public class ReportsServiceImpl implements ReportsService {
	
	@Autowired
	private ReportsMapper mapper;

}
