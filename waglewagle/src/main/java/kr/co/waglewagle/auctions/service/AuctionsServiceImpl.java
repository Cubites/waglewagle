package kr.co.waglewagle.auctions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.waglewagle.auctions.mapper.AuctionsMapper;
import kr.co.waglewagle.domain.ReportsVO;

@Service
public class AuctionsServiceImpl implements AuctionsService {
	
	@Autowired
	private AuctionsMapper mapper;
	
	@Override
	public boolean saveReport(ReportsVO vo) {
		
		// 1. 신고 가능한 지 확인하기
		// 1-1. 경매 중인 물품인지 확인하기 -> 경매 중이어야 함.
		int checkAuctionIng = mapper.checkAuctionIng(vo);
		// 1-2. 이미 신고된 물품인지 확인하기 -> 신고되지 않은 물품이어야 함.
		int checkReport = mapper.checkReport(vo);
		
		// 경매 중이지 않은 물품인 것
		if (checkAuctionIng == 0) {
			return false;
		}
		
		// 이미 신고된 물품인 것
		if (checkReport >= 1) {
			return false;
		}
		
		// 조건이 모두 맞으면, 신고 접수하기
		int saveReport = mapper.saveReport(vo);
		if (saveReport != 1) {
			return false;
		}
		
		return true;
	}

}
