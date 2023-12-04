package kr.co.waglewagle.goods.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.waglewagle.domain.CategoryVO;

@Mapper
public interface GoodsMapper {
	List<CategoryVO> allCategory();
}
