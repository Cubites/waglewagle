package kr.co.waglewagle.admins.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import kr.co.waglewagle.domain.NoticesVO;

public interface NoticeService {
	public List<NoticesVO> noticeList();
//	public void Noticemodify(NoticesVO vo);
	List<NoticesVO> Noticewrite(NoticesVO vo, HttpServletRequest request);
	public Object list(NoticesVO vo);
	public NoticesVO Noticeview(int notices_id);
	public void Noticedelete(int notices_id); //삭제하기
	public Object Noticeview(NoticesVO vo, boolean b);
	public NoticesVO Noticemodify(int notices_id);
	
	public int NoticeModifyUpdate(NoticesVO vo, HttpServletRequest request);

}
