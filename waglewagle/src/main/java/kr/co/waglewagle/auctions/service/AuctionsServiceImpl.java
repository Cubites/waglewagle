package kr.co.waglewagle.auctions.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.waglewagle.auctions.mapper.AuctionsMapper;
import kr.co.waglewagle.domain.AuctionsBreakVO;
import kr.co.waglewagle.domain.AuctionsEndVO;
import kr.co.waglewagle.domain.AuctionsFailVO;
import kr.co.waglewagle.domain.AuctionsIngVO;
import kr.co.waglewagle.domain.GoodsVO;
import kr.co.waglewagle.util.hcju.SomeoneAuctionsBreakVO;
import kr.co.waglewagle.util.hcju.SomeoneAuctionsEndVO;
import kr.co.waglewagle.util.hcju.SomeoneAuctionsIngVO;
import kr.co.waglewagle.util.hcju.SomeoneAuctionsVO;
import kr.co.waglewagle.util.hcju.SomeoneFavorsVO;
import kr.co.waglewagle.domain.ReportsVO;
import kr.co.waglewagle.goods.mapper.GoodsMapper;

@Service
public class AuctionsServiceImpl implements AuctionsService {
	
	@Autowired
	private AuctionsMapper mapper;
	
	@Override
	public Map<String, String> getAuctionIngGoods(int users_id, int goods_id) {
		// 물품 정보 가져오기
		return mapper.getAuctionIngInfo(goods_id);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void completeAuction(int goods_id) {
		// 1. 거래 중 테이블에서 데이터 찾기
		AuctionsIngVO ingVO = mapper.getAunctionIngData(goods_id);
		// 2. 거래 완료 테이블에 추가
		AuctionsEndVO endVO = new AuctionsEndVO();
		endVO.setAuctions_end_seller(ingVO.getAuctions_ing_seller());
		endVO.setAuctions_end_buyer(ingVO.getAuctions_ing_buyer());
		endVO.setAuctions_end_price(ingVO.getAuctions_ing_price());
		endVO.setAuctions_end_date(ingVO.getAuctions_date());
		endVO.setGoods_id(ingVO.getGoods_id());
		mapper.saveAuctionEnd(endVO);
		// 3. 거래 중 테이블에서 해당 데이터 삭제
		mapper.deleteAuctionIngData(ingVO.getAuctions_ing_id());
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean saveReport(ReportsVO vo) {
		// 이미 신고된 물품인지 확인하기 -> 신고되지 않은 물품이어야 함.
		int goods_id = vo.getGoods_id();
		int checkReport = mapper.checkReport(goods_id);
		if (checkReport >= 1) {
			return false;
		}
		// 조건이 모두 맞으면, 신고 접수하기
		int saveReport = mapper.saveReport(vo);
		if (saveReport != 1) {
			return false;
		}
		// 거래 중 테이블에서 데이터 찾기
		AuctionsIngVO ingVO = mapper.getAunctionIngData(goods_id);
		// 경매 파기 테이블에 데이터 추가
		AuctionsBreakVO breakVO = new AuctionsBreakVO();
		breakVO.setAuctions_break_user(ingVO.getAuctions_ing_seller());
		//vo.getReports_content().split(" - ")[1]
		breakVO.setAuctions_break_reason(Integer.parseInt(vo.getReports_content().split(" - ")[0]));
		breakVO.setAuctions_break_detail(vo.getReports_content().split(" - ")[1]);
		breakVO.setGoods_id(ingVO.getGoods_id());
		mapper.saveAuctionBreak(breakVO);
		// 거래 중 테이블에서 데이터 삭제
		mapper.deleteAuctionIngData(ingVO.getAuctions_ing_id());
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
}
