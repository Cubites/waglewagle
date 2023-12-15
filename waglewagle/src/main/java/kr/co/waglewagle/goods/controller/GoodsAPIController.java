package kr.co.waglewagle.goods.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.waglewagle.domain.CategoryVO;
import kr.co.waglewagle.domain.UsersVO;
import kr.co.waglewagle.goods.service.GoodsService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class GoodsAPIController {
	@Autowired
	private GoodsService service;
	
	@PostMapping(path = "/goods/categories",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CategoryVO> lowerCategories(@RequestBody String upper){
	
		List<CategoryVO> lowers;
		//@RequestBody로 값을 받으면 String형태로 upper=5이런식임 
		//따라서 원하는 Integer로 바꾸기 위해 파싱이 필요함 =로 substring
		String upperString = upper.substring(upper.lastIndexOf("=")+1);
		if("".equals(upperString) || upperString == null) {
			//1차 카테고리 24,25 혹은 다시 1차카테고리 클릭시 처리를 위해 
			lowers =service.getLowerCategory(-1);
		}else {
			
		Integer upperInt = Integer.parseInt(upperString);
		lowers = service.getLowerCategory(upperInt);
		
		}
		return lowers;
	}
	
	@PostMapping(path="/goods/favor")
	public void goodsFavor(@SessionAttribute(name = "users_info",required = false) UsersVO loginUser
											,Integer goodsId
											,boolean status) {
			
		 log.info("여기까진 왔다 {}, {}",loginUser, goodsId);
		 service.addGoodsFavor(loginUser.getUsers_id(),goodsId,status);
		 
		
	}
	
}
