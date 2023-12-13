package kr.co.waglewagle.goods.service;

import java.util.List;

import kr.co.waglewagle.domain.CategoryVO;
import kr.co.waglewagle.domain.GoodsVO;
import kr.co.waglewagle.domain.ImagesVO;
import kr.co.waglewagle.goods.won.GoodsFormVO;
import kr.co.waglewagle.goods.won.UploadImage;

public interface GoodsService {
	List<CategoryVO> allCategory();
	List<CategoryVO> getLowerCategory(Integer lower);
	Integer registGoods(GoodsFormVO vo);
	Integer registImages(Integer goods_id, List<UploadImage> list);
	GoodsVO getGoods(Integer goodsId);
	List<ImagesVO> getImages(Integer goodsId);
	Integer getFavorsCnt(int goodsId);
	
}
