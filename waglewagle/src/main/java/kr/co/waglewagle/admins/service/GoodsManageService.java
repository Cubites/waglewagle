package kr.co.waglewagle.admins.service;

import java.util.List;
import java.util.Map;

import kr.co.waglewagle.domain.GoodsVO;

public interface GoodsManageService {
	List<GoodsVO> goodsList();
	int goodsAccess(Map<String, Integer> goodsInfo);
	Map<String, Object> adminGoodsList(GoodsVO param);
}
