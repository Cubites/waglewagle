package kr.co.waglewagle.admins.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.waglewagle.admins.mapper.AdminsMapper;

@Service
public class AdminsServiceImpl implements AdminsService {
	
	@Autowired
	private AdminsMapper mapper;

}
