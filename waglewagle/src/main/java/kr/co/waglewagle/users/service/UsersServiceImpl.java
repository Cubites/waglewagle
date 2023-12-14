package kr.co.waglewagle.users.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.waglewagle.domain.GoodsVO;
import kr.co.waglewagle.domain.PointVO;
import kr.co.waglewagle.domain.UsersVO;
import kr.co.waglewagle.users.mapper.UsersMapper;
import kr.co.waglewagle.util.hcju.SomeoneAuctionsVO;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersMapper mapper;

	@Override
	public UsersVO userInfo(Integer users_id) {
		return mapper.userInfo(users_id);
	}

	@Override
	public PointVO checkPoint(Integer users_id) {
		return mapper.checkPoint(users_id);
	}

	@Override
	public Map<String, Integer> countAuctions(Integer users_id) {
		return mapper.countAuctions(users_id);
	}

	@Override
	public List<SomeoneAuctionsVO> checkAuctions(Map<String, Integer> auctionsVal) {
		return mapper.checkAuctions(auctionsVal);
	}

}
