package kr.co.waglewagle.admins.service;

import java.util.List;
import java.util.Map;

import kr.co.waglewagle.domain.UsersVO;


public interface UserManageService {

	List<UsersVO> userList();
	int usersAccess(Map<String,Integer> usersInfo); 
	int count(UsersVO param);
	List<UsersVO> adminUsersList(UsersVO param);
}
