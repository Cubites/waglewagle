package kr.co.waglewagle.admins.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import kr.co.waglewagle.domain.GoodsVO;

public interface GoodsManageService {

	List<GoodsVO> goodsList();
//	void goodsDelete(int goods_id);
	int goodsAccess(Map<String, Integer> goodsInfo);
	List<Map<String, Object>> adminGoodsList();


}
