package kr.co.waglewagle.admins.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.waglewagle.admins.mapper.GoodsManageMapper;
import kr.co.waglewagle.domain.GoodsVO;

@Service
public class GoodsManageServiceImpl implements GoodsManageService {

	@Autowired
	private GoodsManageMapper mapper;

	@Override
	public List<GoodsVO> goodsList() {
		return mapper.goodsList();
	}

//	@Override
//	public void goodsDelete(int goods_id) {
//		mapper.goodsDelete(goods_id);
//	}

	@Override
	public int goodsAccess(Map<String, Integer> goodsInfo) {
		System.out.println(">>> 게시물 상태 조회 ->>> "+mapper.goodsAccess(goodsInfo));
		return mapper.goodsAccess(goodsInfo);
	}

	
	  @Override 
	  public List<Map<String, Object>> adminGoodsList() { 
		  return mapper.adminGoodsList(); 
		  }
	 



	
	


	
}
