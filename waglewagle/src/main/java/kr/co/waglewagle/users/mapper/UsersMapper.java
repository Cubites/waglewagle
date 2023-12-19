package kr.co.waglewagle.users.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.waglewagle.domain.PointVO;
import kr.co.waglewagle.domain.QnasVO;
import kr.co.waglewagle.domain.UsersVO;

@Mapper
public interface UsersMapper {
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
