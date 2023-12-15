package kr.co.waglewagle.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.waglewagle.domain.CategoryVO;
import kr.co.waglewagle.domain.PointVO;
import kr.co.waglewagle.domain.UsersVO;
import kr.co.waglewagle.users.mapper.UsersMapper;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersMapper mapper;

	@Override
	public UsersVO userInfo(Integer users_id) {
		return mapper.userInfo(users_id);
	}

	@Override
	public List<CategoryVO> allCategory() {
		return mapper.allCategory();
	}

	@Override
	public PointVO getPoint(Integer users_id) {
		PointVO userPoint = mapper.selectPointByUsersId(users_id);
		return userPoint;
	}

}
