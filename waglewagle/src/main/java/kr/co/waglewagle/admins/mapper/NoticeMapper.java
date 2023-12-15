package kr.co.waglewagle.admins.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.waglewagle.domain.NoticesVO;

@Mapper
public interface NoticeMapper {	
	List<NoticesVO> noticeList();
	void Noticewrite(NoticesVO vo);
	Object Noticeview(int notices_id);
	NoticesVO detail(int notices_id); 
}
