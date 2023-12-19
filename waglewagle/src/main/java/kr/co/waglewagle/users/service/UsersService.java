package kr.co.waglewagle.users.service;

import java.util.List;
import java.util.Map;

import kr.co.waglewagle.domain.CategoryVO;
import kr.co.waglewagle.domain.UsersVO;

public interface UsersService {
	UsersVO userInfo(Integer users_id);
	List<CategoryVO> allCategory();
	boolean join(UsersVO vo);
	int selectUsersId(String users_email);
	boolean createPoint(int users_id);
	boolean isEmailDup(String users_email);
	boolean isNickDup(String users_nick);
	UsersVO login(UsersVO vo);
	UsersVO findId(Map<String, String> user_info);
	UsersVO findPwd(Map<String, String> user_info);
	boolean changePwd(Map<String, String> user_info);
}
