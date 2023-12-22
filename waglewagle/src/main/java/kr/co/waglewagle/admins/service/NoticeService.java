package kr.co.waglewagle.admins.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.co.waglewagle.domain.NoticesVO;

public interface NoticeService {
	
//	public List<NoticesVO> noticeList(); //공지 리스트
//	public Object list(NoticesVO vo); //이코드 대신 아래 코드로 변경
	
	void Noticewrite(NoticesVO vo, HttpServletRequest request); //공지 작성
	public NoticesVO Noticeview(int notices_id); //작성 공지 확인
	public Object Noticeview(NoticesVO vo, boolean b);
	public void Noticedelete(int notices_id); //공지 삭제하기
	public NoticesVO Noticemodify(int notices_id); //공지수정
	public int NoticeModifyUpdate(NoticesVO vo, HttpServletRequest request); //공지수정 업로드
	public List<NoticesVO> noticeList(NoticesVO vo);
	
	public int count(NoticesVO param);
//	Map<String, Object> list(NoticesVO param);
	
}
