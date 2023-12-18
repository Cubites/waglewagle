package kr.co.waglewagle.boards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.waglewagle.boards.mapper.BoardsMapper;

@Service
public class BoardsServiceImpl implements BoardsService {
	
	@Autowired
	private BoardsMapper mapper;

}
