package kr.co.waglewagle.admins.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.waglewagle.admins.mapper.NoticeMapper;
import kr.co.waglewagle.domain.NoticesVO;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper mapper;
	
	@Override
	public List<NoticesVO> noticeList(){
		return mapper.noticeList();
	}
	
	//공지 글 등록하기
	@Override
	public  List<NoticesVO> Noticewrite(NoticesVO vo,HttpServletRequest request) {
		mapper.Noticewrite(vo);
		return mapper.noticeList();
	}

	//공지 수정하기
//	@Override
//	public void Noticemodify(NoticesVO vo) {
//	}
	
	//공지 삭제하기
	@Override
	public void Noticedelete(int notices_id) {
		System.out.println("====>"+notices_id);
		mapper.Noticedelete(notices_id);
	}

	@Override
	public Object list(NoticesVO vo) {
		return null;
	}

	//공지 상세보기
	@Override
	public NoticesVO Noticeview(int notices_id) {
		return mapper.detail(notices_id);
	}

	@Override
	public Object Noticeview(NoticesVO vo, boolean b) {
		return null;
	}

	@Override
	public NoticesVO Noticemodify(int notices_id) {
		return mapper.detail(notices_id);
	}

	@Override
	public int NoticeModifyUpdate(NoticesVO vo, HttpServletRequest request) {
		int r = mapper.NoticeModifyUpdate(vo);
		return r;
	}




	
}
