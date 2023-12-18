package kr.co.waglewagle.bids.service;

import kr.co.waglewagle.bids.won.BidsException;
import kr.co.waglewagle.bids.won.BidsFormVO;

public interface BidsService {

	Integer getBidsCnt(int goodsId);

	boolean askPrice(BidsFormVO vo) throws BidsException;
	
}
