package kr.co.waglewagle.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.waglewagle.domain.CategoryVO;
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
	public boolean join(UsersVO vo) {
		String pwd = vo.getUsers_pwd();
		
		//암호화
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPwd = encoder.encode(pwd);
		
		vo.setUsers_pwd(encodedPwd);
		
		return mapper.join(vo) > 0 ? true : false;
	}
	
	@Override
	public boolean isEmailDup(String users_email) {
		return mapper.isEmailDup(users_email) > 0 ? true : false;
	}
	
	@Override
	public boolean isNickDup(String users_nick) {
		return mapper.isNickDup(users_nick) > 0 ? true : false;
	}

	
	@Override
	public UsersVO login(UsersVO vo) {
		return mapper.login(vo);
	}
}
