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
import kr.co.waglewagle.domain.ImagesVO;
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
		return mapper.insertGoods(vo);
	}


	@Override
	public Integer registImages(Integer goods_id, List<UploadImage> list) {
		
		//Thread safe한 HashMap 이미 등록하기 위해선 id와 list 둘다 필요 
		Map map = new ConcurrentHashMap();
		map.put("goods_id", goods_id);
		map.put("imagesList", list);
		
		
		return mapper.insertImages(map);
	}

	@Override
	public GoodsVO getGoods(Integer goodsId) {
		GoodsVO goods = mapper.selectGoodsById(goodsId);
		log.info("goods = {}",goods);
		return goods;
	}

	@Override
	public List<ImagesVO> getImages(Integer goodsId) {
		List<ImagesVO> imageList = mapper.selectImagesById(goodsId);
		return imageList;
	}

	@Override
	public Integer getFavorsCnt(Integer goodsId) {
		// TODO Auto-generated method stub
		Integer favorCnt = mapper.countFavorsByGoodsId(goodsId); 
		return favorCnt;
	}

	

	@Override
	public boolean isFavoritGoods(Integer usersId,Integer goodsId) {
		//유저 아이디와 굿즈 아이디를 가져와서 map으로 만들고 DB조회 
		Map<String,Integer> favorMap = new ConcurrentHashMap<String, Integer>();
		 favorMap.put("usersId", usersId);
		 favorMap.put("goodsId", goodsId);
		 
		 Integer result = mapper.countFavorsByUsersId(favorMap);
		 
		 return result >0 ? true:false;
	   
	}

	

	
	
	
}
