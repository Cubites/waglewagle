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
}
