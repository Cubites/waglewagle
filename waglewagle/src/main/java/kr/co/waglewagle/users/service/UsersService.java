package kr.co.waglewagle.users.service;

import java.util.List;
import java.util.Map;

import kr.co.waglewagle.domain.PointVO;
import kr.co.waglewagle.domain.QnasVO;
import kr.co.waglewagle.domain.UsersVO;

public interface UsersService {
	UsersVO userInfo(Integer users_id);
	PointVO checkPoint(Integer users_id);
	
	String checkFavorAreas(Integer users_id);
	int updateAreas(Map<String, Object> dataForAreaUpdate);

	int validPwd(Map<String, Object> dataForValidPwd);
	int pwdChange(Map<String, Object> dataForValidPwd);

	int countQnas(Integer users_id);
	List<QnasVO> checkQnas(Map<String, Integer> dataForCheckQnas);
	
	int deleteAccount(Integer users_id);
}
