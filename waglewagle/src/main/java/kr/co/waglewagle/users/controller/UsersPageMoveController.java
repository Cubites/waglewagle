package kr.co.waglewagle.users.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.waglewagle.domain.UsersVO;
import kr.co.waglewagle.users.service.UsersService;

@Controller
public class UsersPageMoveController {

	@Autowired
	private UsersService service;

	// 마이페이지 - 거래중으로 이동
	@GetMapping("/mypage/auctions")
	public String mypageAuctions(Model model, HttpSession session, HttpServletRequest req, Integer page) {
		model.addAttribute("menuTab", 0);
		model.addAttribute("menuNum", 0);
		
		UsersVO vo = (UsersVO) session.getAttribute("users_info");
		
		Map<String, Integer> auctionsVal = new HashMap<String, Integer>();
		auctionsVal.put("users_id", vo.getUsers_id());
		System.out.println("page: " + page);
		if(page == null) {
			auctionsVal.put("start_page", 0);
		} else {
			auctionsVal.put("start_page", (page-1)*5);
		}
		System.out.println("page: " + auctionsVal.get("start_page"));
		
		model.addAttribute("auctionsList", service.checkAuctions(auctionsVal));
		
		return "mypage/main";
	}
	
	// 마이페이지 - 거래중으로 이동
	@GetMapping("/mypage/auctions_ing")
	public String mypageAuctionsIng(Model model) {
		model.addAttribute("menuTab", 0);
		model.addAttribute("menuNum", 1);
		return "mypage/main";
	}
	
	// 마이페이지 - 핀메완료로 이동
	@GetMapping("/mypage/auctions_end/sold")
	public String mypageAuctionsEndSold(Model model) {
		model.addAttribute("menuTab", 0);
		model.addAttribute("menuNum", 2);
		return "mypage/main";
	}
	
	// 마이페이지 - 구매완료로 이동
	@GetMapping("/mypage/auctions_end/bought")
	public String mypageAuctionsEndBought(Model model) {
		model.addAttribute("menuTab", 0);
		model.addAttribute("menuNum", 3);
		return "mypage/main";
	}
	
	// 마이페이지 - 유찰 목록으로 이동
	@GetMapping("/mypage/auctions_fail")
	public String mypageAuctionsFail(Model model) {
		model.addAttribute("menuTab", 0);
		model.addAttribute("menuNum", 4);
		return "mypage/main";
	}
	
	// 마이페이지 - 거래파기 목록으로 이동
	@GetMapping("/mypage/auctions_break")
	public String mypageAuctionsBreak(Model model) {
		model.addAttribute("menuTab", 0);
		model.addAttribute("menuNum", 5);
		return "mypage/main";
	}
	
	// 마이페이지 - 찜 목록으로 이동
	@GetMapping("/mypage/favors_list")
	public String mypageFavorsList(Model model) {
		model.addAttribute("menuTab", 0);
		model.addAttribute("menuNum", 6);
		return "mypage/main";
	}
	
	// 마이페이지 - 관심지역 수정으로 이동
	@GetMapping("/mypage/favors_list/fix")
	public String mypageFavorsListFix(Model model) {
		model.addAttribute("menuTab", 1);
		model.addAttribute("menuNum", 0);
		return "mypage/main";
	}
	
	// 마이페이지 - 비밀번호 수정으로 이동
	@GetMapping("/mypage/pwd/change")
	public String mypagePwdChange(Model model) {
		model.addAttribute("menuTab", 1);
		model.addAttribute("menuNum", 1);
		return "mypage/main";
	}
}
