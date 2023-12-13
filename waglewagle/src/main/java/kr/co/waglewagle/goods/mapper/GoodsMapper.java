package kr.co.waglewagle.goods.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.waglewagle.domain.CategoryVO;
import kr.co.waglewagle.domain.GoodsVO;
import kr.co.waglewagle.domain.ImagesVO;
import kr.co.waglewagle.goods.won.GoodsFormVO;

@Mapper
public interface GoodsMapper {
	List<CategoryVO> allCategory();
	List<CategoryVO> getLowerCategory(Integer lowerId);
	Integer insertGoods(GoodsFormVO vo);
	Integer insertImages(Map map);
	GoodsVO selectGoodsById(Integer goodsId);
	List<ImagesVO> selectImagesById(Integer goodsId);
}
