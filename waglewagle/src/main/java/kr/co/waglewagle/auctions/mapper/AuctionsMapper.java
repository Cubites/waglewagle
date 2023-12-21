package kr.co.waglewagle.auctions.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import kr.co.waglewagle.domain.AuctionsIngVO;
import kr.co.waglewagle.util.hcju.SomeoneAuctionsBreakVO;
import kr.co.waglewagle.util.hcju.SomeoneAuctionsEndVO;
import kr.co.waglewagle.util.hcju.SomeoneAuctionsIngVO;
import kr.co.waglewagle.util.hcju.SomeoneAuctionsVO;
import kr.co.waglewagle.util.hcju.SomeoneFavorsVO;
import kr.co.waglewagle.domain.ReportsVO;

@Mapper
public interface AuctionsMapper {
	Map<String, Integer> countAllAuctions(Integer users_id);
	
	Integer countAuctions(Integer users_id);
	List<SomeoneAuctionsVO> checkAuctions(Map<String, Integer> auctionsVal);
	
	Integer countAuctionsIng(Integer users_id);
	List<SomeoneAuctionsIngVO> checkAuctionsIng(Map<String, Integer> auctionsIngVal);
	
	Integer countAuctionsEndSold(Integer users_id);
	List<SomeoneAuctionsEndVO> checkAuctionsEndSold(Map<String, Integer> auctionsIngVal);
	
	Integer countAuctionsEndBought(Integer users_id);
	List<SomeoneAuctionsEndVO> checkAuctionsEndBought(Map<String, Integer> auctionsIngVal);

	Integer countAuctionsBreak(Integer users_id);
	List<SomeoneAuctionsBreakVO> checkAuctionsBreak(Map<String, Integer> auctionsIngVal);
	
	Integer countAuctionsFail(Integer users_id);
	List<SomeoneAuctionsBreakVO> checkAuctionsFail(Map<String, Integer> auctionsIngVal);
	
	Integer countFavors(Integer users_id);
	List<SomeoneFavorsVO> checkFavors(Map<String, Integer> auctionsIngVal);
  
  // 경매중인 물품인지 확인하기
	int checkAuctionIng(ReportsVO vo);
	// 신고된 물품인지 확인하기
	int checkReport(ReportsVO vo);
	// 신고 접수하기
	int saveReport(ReportsVO vo);

	Integer checkgoodsInAuctionIng(Integer goods_id);
	
	//신고 받은 물품 break테이블에 넣기
	Integer insertAuctionsBreak(Map<String, Object> map);

	Integer updateBreakRefund(Map<String,Object> paramMap);

	Integer deleteAuctionIng(Integer object);

	Integer insertAuctionEnd(Map<String, Integer> paramMap);
}