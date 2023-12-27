package kr.co.waglewagle.auctions.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.RequestParam;


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
import kr.co.waglewagle.payment.mapper.PaymentMapper;
import kr.co.waglewagle.goods.mapper.GoodsMapper;


@Service
public class AuctionsServiceImpl implements AuctionsService {

	@Autowired
	private AuctionsMapper mapper;
	@Autowired
	private PaymentMapper payMapper;

	@Override
	public Map<String, String> getAuctionIngGoods(Integer users_id, Integer goods_id) {
		// 물품 정보 가져오기
		return mapper.getAuctionIngInfo(goods_id);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void completeAuction(Integer goods_id) {
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
	public boolean saveReport(ReportsVO vo, Integer user_id) {

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
		breakVO.setAuctions_break_reason(Integer.parseInt(vo.getReports_content().split(" - ")[0]));
		breakVO.setAuctions_break_detail(vo.getReports_content().split(" - ")[1]);
		breakVO.setGoods_id(ingVO.getGoods_id());
		mapper.saveAuctionBreak(breakVO);
		// 거래 중 테이블에서 데이터 삭제
		mapper.deleteAuctionIngData(ingVO.getAuctions_ing_id());
		// 신고자에게 낙찰가 돌려주기 (신고자랑 거래 중 낙찰자랑 일치해야 함)
		if (user_id == ingVO.getAuctions_ing_buyer()) {
			Map<String, Integer> backToBuyer = new HashMap<>();
			backToBuyer.put("user_id", user_id);
			backToBuyer.put("price", ingVO.getAuctions_ing_price());
			mapper.returnToBuyer(backToBuyer);
		}
		return true;
	}

	@Override
	public Map<String, Integer> countAllAuctions(Integer users_id) {
		return mapper.countAllAuctions(users_id);
	}

	@Override
	public Integer countAuctions(Integer users_id) {
		return mapper.countAuctions(users_id);
	}

	@Override
	public List<SomeoneAuctionsVO> checkAuctions(Map<String, Integer> auctionsVal) {
		return mapper.checkAuctions(auctionsVal);
	}

	@Override
	public Integer countAuctionsIng(Integer users_id) {
		return mapper.countAuctionsIng(users_id);
	}

	@Override
	public List<SomeoneAuctionsIngVO> checkAuctionsIng(Map<String, Integer> auctionsIngVal) {
		return mapper.checkAuctionsIng(auctionsIngVal);
	}

	@Override
	public Integer countAuctionsEndSold(Integer users_id) {
		return mapper.countAuctionsEndSold(users_id);
	}

	@Override
	public List<SomeoneAuctionsEndVO> checkAuctionsEndSold(Map<String, Integer> auctionsIngVal) {
		return mapper.checkAuctionsEndSold(auctionsIngVal);
	}

	@Override
	public Integer countAuctionsEndBought(Integer users_id) {
		return mapper.countAuctionsEndBought(users_id);
	}

	@Override
	public List<SomeoneAuctionsEndVO> checkAuctionsEndBought(Map<String, Integer> auctionsIngVal) {
		return mapper.checkAuctionsEndBought(auctionsIngVal);
	}

	@Override
	public Integer countAuctionsBreak(Integer users_id) {
		return mapper.countAuctionsBreak(users_id);
	}

	@Override
	public List<SomeoneAuctionsBreakVO> checkAuctionsBreak(Map<String, Integer> auctionsIngVal) {
		return mapper.checkAuctionsBreak(auctionsIngVal);
	}

	@Override
	public Integer countAuctionsFail(Integer users_id) {
		return mapper.countAuctionsFail(users_id);
	}

	@Override
	public List<SomeoneAuctionsBreakVO> checkAuctionsFail(Map<String, Integer> auctionsIngVal) {
		return mapper.checkAuctionsFail(auctionsIngVal);
	}

	@Override
	public Integer countFavors(Integer users_id) {
		return mapper.countFavors(users_id);
	}

	@Override
	public List<SomeoneFavorsVO> checkFavors(Map<String, Integer> auctionsIngVal) {
		return mapper.checkFavors(auctionsIngVal);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean auctionEnd(Map<String, Object> paramMap) throws Exception {

		// 1번 auction_end에 등록
		Integer insertAuctionsEndResult = mapper.insertAuctionEnd(paramMap);

		// 2번 auction_buyer에 포인트 auction_seller의 총포인트 + 가용포인트에 올리기
		Integer updateAuctionSellerPointResult = payMapper.updateSellerPoint(paramMap);
		// 3번 auction_buyer 토탈 에 포인트 차감
		Integer updateAuctionBuyerPointResult = payMapper.updateBuyerPoint(paramMap);

		// 4번 글 지우기
		Integer deleteResult = mapper.deleteAuctionIng(paramMap);

		if (insertAuctionsEndResult == 0 ||updateAuctionSellerPointResult ==0 
				|| updateAuctionBuyerPointResult ==0 || deleteResult ==0 ) {
			throw new Exception("경매 등록 중 문제 발생");
		}

		return true;
	}
	
	@Override
	public boolean updateAuctionIngView(Map<String,Object> map) {
		
		System.out.println(map);
		System.out.println(map.get("memberType"));
		System.out.println(map.get("auctions_seller_read") instanceof Boolean);
		//일단 셀러인지 바이어인지 구분해야함 
		
		if(map.get("memberType").equals("seller") && ((boolean)map.get("auctions_seller_read")==false)) {
			mapper.updateAuctionIngView(map);
		}else if(map.get("memberType").equals("buyer") && ((boolean)map.get("auctions_buyer_read"))==false){
			mapper.updateAuctionIngView(map);
		}
		
		return true;
	}

	
}
