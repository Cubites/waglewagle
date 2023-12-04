package kr.co.waglewagle.bids.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.waglewagle.bids.mapper.BidsMapper;

@Service
public class BidsServiceImpl implements BidsService {
	
	@Autowired
	private BidsMapper mapper;

}
