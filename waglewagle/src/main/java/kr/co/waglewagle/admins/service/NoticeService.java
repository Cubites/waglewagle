package kr.co.waglewagle.admins.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import kr.co.waglewagle.domain.NoticesVO;

public interface NoticeService {
	public List<NoticesVO> noticeList();
	
	
	public void Noticemodify(NoticesVO vo);
	public void Noticedelete(int notices_id);
	List<NoticesVO> Noticewrite(NoticesVO vo, HttpServletRequest request);
	public Object list(NoticesVO vo);


	public NoticesVO Noticeview(int notices_id);
	

}
