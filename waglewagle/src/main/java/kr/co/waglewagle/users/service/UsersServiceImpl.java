package kr.co.waglewagle.users.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.waglewagle.domain.PointVO;
import kr.co.waglewagle.domain.QnasVO;
import kr.co.waglewagle.domain.UsersVO;
import kr.co.waglewagle.users.mapper.UsersMapper;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersMapper mapper;

	@Override
	public UsersVO userInfo(Integer users_id) { return mapper.userInfo(users_id); }

	@Override
	public PointVO checkPoint(Integer users_id) { return mapper.checkPoint(users_id); }


	@Override
	public String checkFavorAreas(Integer users_id) { return mapper.checkFavorAreas(users_id); }
	@Override
	public int updateAreas(Map<String, Object> dataForAreaUpdate) { return mapper.updateAreas(dataForAreaUpdate); }

	@Override
	public int validPwd(Map<String, Object> dataForValidPwd) { return mapper.validPwd(dataForValidPwd); }
	@Override
	public int pwdChange(Map<String, Object> dataForValidPwd) { return mapper.pwdChange(dataForValidPwd); }

	@Override
	public int countQnas(Integer users_id) { return mapper.countQnas(users_id); }
	@Override
	public List<QnasVO> checkQnas(Map<String, Integer> dataForCheckQnas) {
		return mapper.checkQnas(dataForCheckQnas);
	}

	@Override
	public int deleteAccount(Integer users_id) {
		return mapper.deleteAccount(users_id);
	}

}
