package kr.co.waglewagle.admins.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.waglewagle.admins.mapper.JoinMapper;
import kr.co.waglewagle.domain.AdminsVO;

@Service
public class JoinServiceImpl implements JoinService {
	
	@Autowired
	private JoinMapper mapper;

	@Override
	public Integer checkDuplicationId(String admins_email) {
		return mapper.checkDuplicationId(admins_email);
	}

	@Override
	public int addAdminAccount(AdminsVO vo) {
		return mapper.addAdminAccount(vo);
	}
}
