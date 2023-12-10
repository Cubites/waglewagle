package kr.co.waglewagle.goods.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.waglewagle.domain.CategoryVO;
import kr.co.waglewagle.domain.GoodsVO;
import kr.co.waglewagle.goods.mapper.GoodsMapper;
import kr.co.waglewagle.goods.won.GoodsFormVO;
import kr.co.waglewagle.goods.won.UploadImage;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	private GoodsMapper mapper;

	@Override
	public List<CategoryVO> allCategory() {
		return mapper.allCategory();
	}

	@Override
	public List<CategoryVO> getLowerCategory(Integer lower) {
		List<CategoryVO> lowers = mapper.getLowerCategory(lower);
		return lowers;
	}

	@Override
	public Integer registGoods(GoodsFormVO vo) {
		GoodsVO goods = new GoodsVO();
		//결과를 GoodsVO로 받으면 되자나 
		setGoodsExpDate(vo);
		
		return mapper.insertGoods(vo);
	}


	private void setGoodsExpDate(GoodsFormVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer registImages(Integer goods_id, List<UploadImage> list) {
		
		//Thread safe한 HashMap 이미 등록하기 위해선 id와 list 둘다 필요 
		Map map = new ConcurrentHashMap();
		map.put("goods_id", goods_id);
		map.put("imagesList", list);
		
		
		return mapper.insertImages(map);
	}
	
	
}
