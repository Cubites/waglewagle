package kr.co.waglewagle.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.waglewagle.admins.mapper.NoticeMapper;
import kr.co.waglewagle.board.mapper.UsernoticesMapper;
import kr.co.waglewagle.domain.NoticesVO;

@Service
public class UsernoticesServiceImpl implements UsernoticesSerivce {

	@Autowired
	private NoticeMapper mapper;
	
	@Autowired
	private UsernoticesMapper mapper2;
	
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








	
}
