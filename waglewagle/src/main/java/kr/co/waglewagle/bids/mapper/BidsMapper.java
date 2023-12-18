package kr.co.waglewagle.bids.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.waglewagle.bids.won.BidsFormVO;

@Mapper
public interface BidsMapper {

	Integer countBidsByGoodsId(int goodsId);

	int checkPoint(BidsFormVO vo);

	int insertBids(BidsFormVO vo);

	int UpdateUsablePoint(BidsFormVO vo);

	int checkInitial(BidsFormVO vo);

	Integer selectMaxBidsPriceByUsersId(Map userAndgoods);
	
}
