package kr.co.waglewagle.users.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.waglewagle.auctions.service.AuctionsService;
import kr.co.waglewagle.domain.UsersVO;
import kr.co.waglewagle.users.service.UsersService;


@Controller
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	@Autowired
	private AuctionsService auctionsService;
	
	// 테스트용 세션 추가
	@GetMapping("/add/session/{users_id}")
	public String usersInfo(HttpSession sess, @PathVariable("users_id") Integer users_id) {
		sess.setAttribute("users_info", service.userInfo(users_id));
		return "home";
	}
	
	// 관심지역 수정
	@ResponseBody
	@PostMapping("/update/favor_areas")
	public boolean updateAreas(HttpSession sess, @RequestBody Map<String, Object> areas) {
		Map<String, Object> dataForAreaUpdate = new HashMap<>();
		UsersVO vo = (UsersVO) sess.getAttribute("users_info");
		dataForAreaUpdate.put("users_id", vo.getUsers_id());
		dataForAreaUpdate.put("areas", areas.get("addr"));
		
		int result = service.updateAreas(dataForAreaUpdate);
		
		return result == 1 ? true : false;
	}
	
	// 비밀번호 변경 시, 비밀번호가 맞는지 여부 확인
	@ResponseBody
	@PostMapping("/pwd/check")
	public boolean pwdCheck(HttpSession sess, @RequestBody Map<String, Object> pwd) {
		Map<String, Object> dataForValidPwd = new HashMap<>();
		UsersVO vo = (UsersVO) sess.getAttribute("users_info");
		dataForValidPwd.put("users_id", vo.getUsers_id());
		dataForValidPwd.put("users_pwd", pwd.get("data"));
		return service.validPwd(dataForValidPwd) == 1 ? true : false;
	}
	// 비밀번호 변경
	@ResponseBody
	@PostMapping("/pwd/change")
	public boolean pwdChange(HttpSession sess, @RequestBody Map<String, Object> pwd) {
		Map<String, Object> dataForValidPwd = new HashMap<>();
		UsersVO vo = (UsersVO) sess.getAttribute("users_info");
		dataForValidPwd.put("users_id", vo.getUsers_id());
		dataForValidPwd.put("users_pwd", pwd.get("data"));	
		return service.pwdChange(dataForValidPwd) == 1 ? true : false;
	}
	
	// 로그아웃
	@GetMapping("/users/logout")
	public String logout(HttpSession sess) {
		sess.removeAttribute("users_info");
		return "redirect:/boards/qnas/write";
	}
	
	// 회원탈퇴
	@ResponseBody
	@PostMapping("/users/delete")
	public Map<String, Integer> deleteAccount(HttpSession sess, @RequestBody Map<String, Object> pwd) {
		System.out.println();
		
		Map<String, Object> dataForValidPwd = new HashMap<>();
		UsersVO vo = (UsersVO) sess.getAttribute("users_info");
		dataForValidPwd.put("users_id", vo.getUsers_id());
		dataForValidPwd.put("users_pwd", pwd.get("data"));

		Map<String, Integer> result = new HashMap<>();
		if(service.validPwd(dataForValidPwd) == 1) {
			int auctionsCount = 0;
			auctionsCount += auctionsService.countAuctions(vo.getUsers_id());
			auctionsCount += auctionsService.countAuctionsIng(vo.getUsers_id());
			if(auctionsCount == 0) {
				if(service.deleteAccount(vo.getUsers_id()) == 1) {
					result.put("resultCode", 0);
				} else {
					result.put("resultCode", 3);
				}
			} else {
				result.put("resultCode", 2);
			}
		} else {
			result.put("resultCode", 1);
		}
		return result;
	}
	
	// 마이페이지 - 목록
	@GetMapping("/mypage/{pageName}")
	public String mypageListPages(@PathVariable("pageName") String pageName, Model model, HttpSession sess, Integer page, Integer scroll) {
		// 총 게시글 수 계산
		UsersVO vo = (UsersVO) sess.getAttribute("users_info");
		int itemsNumPerPage = 5;
		int totalAuctionsNum = 0;
		if("auctions".equals(pageName)) { // 경매 중
			totalAuctionsNum = auctionsService.countAuctions(vo.getUsers_id());
		} else if("auctions_ing".equals(pageName)) { // 거래 중
			totalAuctionsNum = auctionsService.countAuctionsIng(vo.getUsers_id());
		} else if("auctions_end_sold".equals(pageName)) { // 판매 완료
			totalAuctionsNum = auctionsService.countAuctionsEndSold(vo.getUsers_id());
		} else if("auctions_end_bought".equals(pageName)) { // 구매 완료
			totalAuctionsNum = auctionsService.countAuctionsEndBought(vo.getUsers_id());
		} else if("auctions_break".equals(pageName)) { // 파기 상품
			totalAuctionsNum = auctionsService.countAuctionsBreak(vo.getUsers_id());
		} else if("auctions_fail".equals(pageName)) { // 유찰 상품
			totalAuctionsNum = auctionsService.countAuctionsFail(vo.getUsers_id());
		} else if("favors_list".equals(pageName)) { // 찜 상품
			totalAuctionsNum = auctionsService.countFavors(vo.getUsers_id());
		} else if("qnas".equals(pageName)) { // 문의 내역
			totalAuctionsNum = service.countQnas(vo.getUsers_id());
			itemsNumPerPage = 10;
		}
		totalAuctionsNum = (int) Math.ceil(((double)totalAuctionsNum) / itemsNumPerPage);
		int nowPage1 = 0;
		int nowPage10 = 0;
		
		// 거래중 게시글 조회에 필요한 값(유저 id, 페이지 번호) 저장
		Map<String, Integer> auctionsVal = new HashMap<String, Integer>();
		auctionsVal.put("users_id", vo.getUsers_id());
		if(page == null || page > totalAuctionsNum) {
			auctionsVal.put("start_page", 0);
			nowPage1 = 1;
			nowPage10 = 0;
		} else {
			auctionsVal.put("start_page", (page-1)*itemsNumPerPage);
			nowPage1 = page;
			nowPage10 = ((int)(page-1)/10) * 10;
		}
		
		// 글 목록, 메뉴 index 저장
		if("auctions".equals(pageName)) { // 경매 중
			model.addAttribute("ListData", auctionsService.checkAuctions(auctionsVal));
			model.addAttribute("menuTab", 0);
			model.addAttribute("menuNum", 0);
		} else if("auctions_ing".equals(pageName)) { // 거래 중
			model.addAttribute("ListData", auctionsService.checkAuctionsIng(auctionsVal));
			model.addAttribute("menuTab", 0);
			model.addAttribute("menuNum", 1);
		} else if("auctions_end_sold".equals(pageName)) { // 판매 완료
			model.addAttribute("ListData", auctionsService.checkAuctionsEndSold(auctionsVal));
			model.addAttribute("menuTab", 0);
			model.addAttribute("menuNum", 2);
		} else if("auctions_end_bought".equals(pageName)) { // 구매 완료
			model.addAttribute("ListData", auctionsService.checkAuctionsEndBought(auctionsVal));
			model.addAttribute("menuTab", 0);
			model.addAttribute("menuNum", 3);
		} else if("auctions_break".equals(pageName)) { // 파기 상품
			model.addAttribute("ListData", auctionsService.checkAuctionsBreak(auctionsVal));
			model.addAttribute("menuTab", 0);
			model.addAttribute("menuNum", 4);
		} else if("auctions_fail".equals(pageName)) { // 유찰 상품
			model.addAttribute("ListData", auctionsService.checkAuctionsFail(auctionsVal));
			model.addAttribute("menuTab", 0);
			model.addAttribute("menuNum", 5);
		} else if("favors_list".equals(pageName)) { // 찜 상품
			model.addAttribute("ListData", auctionsService.checkFavors(auctionsVal));
			model.addAttribute("menuTab", 0);
			model.addAttribute("menuNum", 6);
		} else if("qnas".equals(pageName)) { // 문의 내역
			model.addAttribute("ListData", service.checkQnas(auctionsVal));
			model.addAttribute("menuTab", 1);
			model.addAttribute("menuNum", 2);
		}
		
		// 페이지 번호
		model.addAttribute("pageNum1", nowPage1);
		model.addAttribute("pageNum10", nowPage10);
		model.addAttribute("pageNumMax", totalAuctionsNum);
		
		model.addAttribute("scrollY", scroll);
		
		return "mypage/main";
	}
	
	// 마이페이지 - 관심지역 수정으로 이동
	@GetMapping("/mypage/favors_list/fix")
	public String mypageFavorsListFix(Model model, HttpSession session) {
		model.addAttribute("menuTab", 1);
		model.addAttribute("menuNum", 0);
		
		UsersVO vo = (UsersVO) session.getAttribute("users_info");
		String areasStr = service.checkFavorAreas(vo.getUsers_id());
		System.out.println("areasStr: " + areasStr);
		String[] areas = areasStr != null ? areasStr.split(",") : null;
		model.addAttribute("favor_areas", areas); 
		model.addAttribute("favor_areas_count", areas != null ? areas.length : 0);
		
		return "mypage/main";
	}
	
	// 마이페이지 - 비밀번호 수정으로 이동
	@GetMapping("/mypage/pwd/change")
	public String mypagePwdChange(Model model, HttpSession session) {
		model.addAttribute("menuTab", 1);
		model.addAttribute("menuNum", 1);
		return "mypage/main";
	}
}
