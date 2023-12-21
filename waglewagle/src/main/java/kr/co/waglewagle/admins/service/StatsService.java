package kr.co.waglewagle.admins.service;

import java.util.List;
import java.util.Map;

public interface StatsService {
	List<Map<String, Object>> countGoodsByCategory();
	List<Map<String, Object>> countBidsByCategory();

	List<Map<String, Object>> countGoodsByCategoryAll();
	List<Map<String, Object>> countBidsByCategoryAll();
}

