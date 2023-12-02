package kr.co.waglewagle.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.waglewagle.domain.UsersVO;
import kr.co.waglewagle.users.mapper.UsersMapper;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersMapper mapper;

	@Override
	public UsersVO userInfo(Integer user_id) {
		return mapper.userInfo(user_id);
	}

}
