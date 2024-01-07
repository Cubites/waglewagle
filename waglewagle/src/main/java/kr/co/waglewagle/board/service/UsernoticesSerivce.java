package kr.co.waglewagle.board.service;

import kr.co.waglewagle.domain.NoticesVO;

public interface UsernoticesSerivce {
	public Object list(NoticesVO vo);
	public NoticesVO Noticeview(int notices_id);
	public Object Noticeview(NoticesVO vo, boolean b);
}
