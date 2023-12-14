package kr.co.waglewagle.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.waglewagle.domain.UsersVO;
import kr.co.waglewagle.users.service.UsersService;

@Component
public class MypageInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UsersService service;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		HttpSession sess = req.getSession();
		UsersVO vo = (UsersVO) req.getSession().getAttribute("users_info");
		
		System.out.println("vo.getUsers_id(): " + vo.getUsers_id());
		
		req.setAttribute("pointInfo", service.checkPoint(vo.getUsers_id()));
		req.setAttribute("countAuctions", service.countAuctions(vo.getUsers_id()));
		return true;
	}

}