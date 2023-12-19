package kr.co.waglewagle.admins.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.waglewagle.admins.mapper.QnaMapper;
import kr.co.waglewagle.domain.NoticesVO;
import kr.co.waglewagle.domain.QnasVO;

@Service
public class QnaServiceImpl implements QnaService {

	@Autowired
	private QnaMapper mapper;
	
	@Override
	public List<QnasVO> QnaList(){
		return mapper.QnaList();
	}
	@Override
	public Object list(QnasVO vo) {
		return null;
	}
	@Override
	public QnasVO Qnaview(int qnas_id) {
		return mapper.detail(qnas_id);
	}
	@Override
	public Object Qnaview(QnasVO vo, boolean b) {
		return null;
	}
	@Override
	public void qnaDelete(int qnas_id) {
		mapper.qnaDelete(qnas_id);
		
	}


	
}
