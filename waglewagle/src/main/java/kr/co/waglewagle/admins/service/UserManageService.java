package kr.co.waglewagle.admins.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import kr.co.waglewagle.domain.UsersVO;


public interface UserManageService {

	List<UsersVO> userList();
	int usersAccess(Map<String,Integer> usersInfo); 
	List<Map<String, Object>> usersReport();
}
