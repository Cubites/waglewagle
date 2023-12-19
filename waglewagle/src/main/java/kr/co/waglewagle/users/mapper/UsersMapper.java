package kr.co.waglewagle.users.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.waglewagle.domain.CategoryVO;
import kr.co.waglewagle.domain.UsersVO;

@Mapper
public interface UsersMapper {
	UsersVO userInfo(Integer users_id);
	List<CategoryVO> allCategory();
	int join(UsersVO vo);
	int selectUsersId(String users_email);
	int createPoint(int users_id);
	int isEmailDup(String users_email);
	int isNickDup(String users_nick);
	UsersVO login(UsersVO vo);
	UsersVO findId(Map<String, String> user_info);
	UsersVO findPwd(Map<String, String> user_info);
	int changePwd(Map<String, String> user_info);
}
