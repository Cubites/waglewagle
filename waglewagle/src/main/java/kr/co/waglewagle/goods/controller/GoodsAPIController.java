package kr.co.waglewagle.goods.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.waglewagle.domain.CategoryVO;
import kr.co.waglewagle.goods.service.GoodsService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class GoodsAPIController {
	@Autowired
	private GoodsService service;
	
	@PostMapping(path = "/goods/categories",produces = "application/json")
	
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
}
