package kr.co.waglewagle.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.waglewagle.domain.UsersVO;
import kr.co.waglewagle.users.service.UsersService;

@Component
public class RelCaculateInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UsersService service;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,Object handler) throws Exception {
		UsersVO vo = (UsersVO) req.getSession().getAttribute("users_info");
		int rel = service.relCalculate(vo.getUsers_id());
		if(rel < 0) {
			rel = 0;
		} else if(rel > 74) {
			rel = 74;
		}
		Map<String, Integer> dataForRelUpdate = new HashMap<>();
		dataForRelUpdate.put("users_id", vo.getUsers_id());
		dataForRelUpdate.put("rel", rel);
		int result = service.relUpdate(dataForRelUpdate);
		return true;
	}
}
