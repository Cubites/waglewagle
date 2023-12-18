package kr.co.waglewagle.bids.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.waglewagle.bids.service.BidsService;
import kr.co.waglewagle.bids.won.BidsException;
import kr.co.waglewagle.bids.won.BidsFormVO;
import kr.co.waglewagle.domain.UsersVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BidsController {

	@Autowired
	private BidsService service;
	
	
	@PostMapping("/bids/askPrice")
	@ResponseBody
	public ResponseEntity<String> askPrice(@Validated BidsFormVO vo, BindingResult rs, @SessionAttribute("users_info") UsersVO loginuser ) {
			
		ResponseEntity<String> response;
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "text/plain;charset=UTF-8");
		boolean result;
		
		//자신의 글에 호가 검ㅏ
		if(vo.getUsers_id() == loginuser.getUsers_id()) {
			rs.rejectValue("users_id", null,null,"자신이 올린 글에는 호가 불가함");
		}
		//가용 포인트 검사
		if(vo.getPoint_usable()-vo.getBids_price() <0) {
			rs.reject("potinError", "가용 포인트 부족");
		}
		
		//response = new ResponseEntity<String>("제출된 가격을 확인해주세요 ",headers,HttpStatus.BAD_REQUEST);
		if(rs.hasErrors()) {
			response = new ResponseEntity<String>("제출된 가격을 확인해주세요",headers,HttpStatus.BAD_REQUEST);
		}
		
		
		
		try {
			result = service.askPrice(vo);
		} catch (BidsException e) {
			response = new ResponseEntity<String>("제출된 가격을 확인해주세요",headers,HttpStatus.BAD_REQUEST);
			return response;
		}

		response = new ResponseEntity<String>("호가 등록에 성공했습니다.",headers,HttpStatus.ACCEPTED);
		
		
		
		return response;
	}
}
