package kr.co.waglewagle.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.waglewagle.domain.CategoryVO;
import kr.co.waglewagle.goods.mapper.GoodsMapper;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsMapper mapper;

	@Override
	public List<CategoryVO> allCategory() {
		return mapper.allCategory();
	}
	
}
