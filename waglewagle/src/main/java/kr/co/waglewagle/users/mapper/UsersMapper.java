package kr.co.waglewagle.users.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.waglewagle.domain.GoodsVO;
import kr.co.waglewagle.domain.PointVO;
import kr.co.waglewagle.domain.UsersVO;
import kr.co.waglewagle.util.hcju.SomeoneAuctionsVO;

@Mapper
public interface UsersMapper {
	UsersVO userInfo(Integer users_id);
	PointVO checkPoint(Integer users_id);
	
	Map<String, Integer> countAuctions(Integer users_id);
	
	List<SomeoneAuctionsVO> checkAuctions(Map<String, Integer> auctionsVal);
}
