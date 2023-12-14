package kr.co.waglewagle.users.service;

import java.util.List;
import java.util.Map;

import kr.co.waglewagle.domain.GoodsVO;
import kr.co.waglewagle.domain.PointVO;
import kr.co.waglewagle.domain.UsersVO;
import kr.co.waglewagle.util.hcju.SomeoneAuctionsVO;

public interface UsersService {
	UsersVO userInfo(Integer users_id);
	PointVO checkPoint(Integer users_id);
	
	Map<String, Integer> countAuctions(Integer users_id);
	
	List<SomeoneAuctionsVO> checkAuctions(Map<String, Integer> auctionsVal);
}
