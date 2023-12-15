package kr.co.waglewagle.users.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.waglewagle.domain.CategoryVO;
import kr.co.waglewagle.domain.PointVO;
import kr.co.waglewagle.domain.UsersVO;

@Mapper
public interface UsersMapper {
	UsersVO userInfo(Integer users_id);
	List<CategoryVO> allCategory();
	PointVO selectPointByUsersId(Integer users_id);
}
