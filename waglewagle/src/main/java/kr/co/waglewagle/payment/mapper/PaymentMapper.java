package kr.co.waglewagle.payment.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.waglewagle.domain.PaylogVO;
import kr.co.waglewagle.domain.PointVO;

@Mapper
public interface PaymentMapper {
	// 포인트 충전
	int savePayment(PaylogVO vo);
	// 포인트 변경
	int updateTotalPoint(PaylogVO vo);
}
