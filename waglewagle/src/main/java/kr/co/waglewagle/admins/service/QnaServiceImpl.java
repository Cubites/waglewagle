package kr.co.waglewagle.admins.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.waglewagle.admins.mapper.QnaMapper;
import kr.co.waglewagle.domain.QnasVO;

@Service
public class QnaServiceImpl implements QnaService {

	@Autowired
	private QnaMapper mapper;
	
	//문의리스트
	@Override
	public List<QnasVO> QnaList(){
		return mapper.QnaList();
	}
	@Override
	public Object list(QnasVO vo) {
		return null;
	}
	
	//문의보기
	@Override
	public QnasVO Qnaview(int qnas_id) {
		return mapper.detail(qnas_id);
	}
	
	@Override
	public Object Qnaview(QnasVO vo, boolean b) {
		return null;
	}
	
	
	//문의 삭제하기
	@Override
	public void qnaDelete(int qnas_id) {
		mapper.qnaDelete(qnas_id);
	}
	
//	@Override
//	public void delReply(int qnas_id) {
//		mapper.delReply(qnas_id);
//	}
	
	@Override
	public int delReply(QnasVO vo, HttpServletRequest request) {
		int r = mapper.delReply(vo);
		return r;
	}
	
	//답변 등록하기
	@Override
	public void writeReply(QnasVO vo, HttpServletRequest request) {
		mapper.writeReply(vo);
		
	}
	
	//답변 달기
	@Override
	public QnasVO qnaReply(int qnas_id) {
		
		return null;
	}
	
	//답변 수정하기
	@Override
	public void modifyReply(QnasVO vo, HttpServletRequest request) {
		mapper.modifyReply(vo);
	}


}
