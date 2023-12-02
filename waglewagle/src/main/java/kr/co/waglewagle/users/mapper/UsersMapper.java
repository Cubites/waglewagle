package kr.co.waglewagle.users.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.waglewagle.domain.UsersVO;

@Mapper
public interface UsersMapper {
	UsersVO userInfo(Integer user_id);
}
