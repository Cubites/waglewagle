package kr.co.waglewagle.admins.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.waglewagle.domain.QnasVO;

@Mapper
public interface QnaMapper {
	List<QnasVO> QnaList();
	QnasVO detail(int qnas_id);	
	Object Qnaview(int qnas_id);
	void qnaDelete(int qnas_id);
}
