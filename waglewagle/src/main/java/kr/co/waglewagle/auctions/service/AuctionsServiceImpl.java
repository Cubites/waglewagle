package kr.co.waglewagle.auctions.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.waglewagle.auctions.mapper.AuctionsMapper;
import kr.co.waglewagle.domain.AuctionsIngVO;
import kr.co.waglewagle.util.hcju.SomeoneAuctionsBreakVO;
import kr.co.waglewagle.util.hcju.SomeoneAuctionsEndVO;
import kr.co.waglewagle.util.hcju.SomeoneAuctionsIngVO;
import kr.co.waglewagle.util.hcju.SomeoneAuctionsVO;
import kr.co.waglewagle.util.hcju.SomeoneFavorsVO;
import kr.co.waglewagle.domain.ReportsVO;
import kr.co.waglewagle.payment.mapper.PaymentMapper;

@Service
public class AuctionsServiceImpl implements AuctionsService {
	
	@Autowired
	private AuctionsMapper mapper;
	@Autowired
	private PaymentMapper payMapper;
	
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

	@Override
	public Map<String, Integer> countAllAuctions(Integer users_id) {
		return mapper.countAllAuctions(users_id);
	}

	@Override
	public Integer countAuctions(Integer users_id) { return mapper.countAuctions(users_id); }
	@Override
	public List<SomeoneAuctionsVO> checkAuctions(Map<String, Integer> auctionsVal) {
		return mapper.checkAuctions(auctionsVal);
	}

	@Override
	public Integer countAuctionsIng(Integer users_id) { return mapper.countAuctionsIng(users_id); }
	@Override
	public List<SomeoneAuctionsIngVO> checkAuctionsIng(Map<String, Integer> auctionsIngVal) {
		return mapper.checkAuctionsIng(auctionsIngVal);
	}

	@Override
	public Integer countAuctionsEndSold(Integer users_id) { return mapper.countAuctionsEndSold(users_id); }
	@Override
	public List<SomeoneAuctionsEndVO> checkAuctionsEndSold(Map<String, Integer> auctionsIngVal) {
		return mapper.checkAuctionsEndSold(auctionsIngVal);
	}
	
	@Override
	public Integer countAuctionsEndBought(Integer users_id) { return mapper.countAuctionsEndBought(users_id); }
	@Override
	public List<SomeoneAuctionsEndVO> checkAuctionsEndBought(Map<String, Integer> auctionsIngVal) {
		return mapper.checkAuctionsEndBought(auctionsIngVal);
	}

	@Override
	public Integer countAuctionsBreak(Integer users_id) { return mapper.countAuctionsBreak(users_id); }

	@Override
	public List<SomeoneAuctionsBreakVO> checkAuctionsBreak(Map<String, Integer> auctionsIngVal) {
		return mapper.checkAuctionsBreak(auctionsIngVal);
	}

	@Override
	public Integer countAuctionsFail(Integer users_id) { return mapper.countAuctionsFail(users_id); }
	@Override
	public List<SomeoneAuctionsBreakVO> checkAuctionsFail(Map<String, Integer> auctionsIngVal) {
		return mapper.checkAuctionsFail(auctionsIngVal);
	}

	@Override
	public Integer countFavors(Integer users_id) { return mapper.countFavors(users_id); }

	@Override
	public List<SomeoneFavorsVO> checkFavors(Map<String, Integer> auctionsIngVal) {
		return mapper.checkFavors(auctionsIngVal);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean auctionEnd(Map<String, Integer> paramMap) throws Exception {
		
		//1번 auction_end에 등록 
		Integer insertAuctionsEndResult = mapper.insertAuctionEnd(paramMap);
		//2번 auction_buyer에 포인트 auction_seller의 총포인트 + 가용포인트에 올리기 
		Integer updateAuctionSellerPointResult = payMapper.updateSellerPoint(paramMap);
		//3번 auction_buyer에 포인트 차감 
		Integer updateAuctionBuyerPointResult = payMapper.updateBuyerPoint(paramMap);
		//4번 글 지우기 
		Integer deleteResult = mapper.deleteAuctionIng(paramMap.get("goods_id"));
		if(insertAuctionsEndResult == 0 || updateAuctionBuyerPointResult == 0 || updateAuctionBuyerPointResult == 0 || deleteResult == 0) {
			throw new Exception("경매 완료 과정 중 오류 발생");
		}
		return false;
	}
}
