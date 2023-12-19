package kr.co.waglewagle.auctions.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.waglewagle.domain.ReportsVO;

@Mapper
public interface AuctionsMapper {
	// 경매중인 물품인지 확인하기
	int checkAuctionIng(ReportsVO vo);
	// 신고된 물품인지 확인하기
	int checkReport(ReportsVO vo);
	// 신고 접수하기
	int saveReport(ReportsVO vo);
}
