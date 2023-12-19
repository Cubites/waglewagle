package kr.co.waglewagle.admins.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import kr.co.waglewagle.domain.NoticesVO;
import kr.co.waglewagle.domain.QnasVO;

public interface QnaService {
	List<QnasVO> QnaList();
	QnasVO Qnaview(int qnas_id);
	Object list(QnasVO vo);
	public Object Qnaview(QnasVO vo, boolean b);
	void qnaDelete(int qnas_id);

}
