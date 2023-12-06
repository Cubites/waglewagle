package kr.co.waglewagle.users.service;

import java.util.List;

import kr.co.waglewagle.domain.CategoryVO;
import kr.co.waglewagle.domain.UsersVO;

public interface UsersService {
	UsersVO userInfo(Integer users_id);
	List<CategoryVO> allCategory();
	boolean join(UsersVO vo);
}
