package kr.co.waglewagle.chat.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.waglewagle.chat.service.ChatService;
import kr.co.waglewagle.chat.won.MemberType;
import kr.co.waglewagle.domain.ChatVO;
import kr.co.waglewagle.domain.UsersVO;
import kr.co.waglewagle.goods.service.GoodsService;
import kr.co.waglewagle.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

	private final ChatService service;
	private final UsersService userService;
	private final GoodsService goodsService;

	@GetMapping("/s1")
	public void session1(HttpSession session) {
		session.setAttribute("users_info", userService.userInfo(111));
	}

	@GetMapping("/s2")
	public void session2(HttpSession session) {
		session.setAttribute("users_info", userService.userInfo(110));
	}

	@GetMapping("/{goodsId}")

	public String showChatPage(Model model, @PathVariable("goodsId") Integer goodsId,
			@SessionAttribute("users_info") UsersVO loginUser) {

		

		// 굿즈 아이디로 옥션 정보 가져오기 맵에는 seller와 buyer id값으로 유저 정보 저장되어 있다.
		List <Map<String, Object>> auction = service.getAuctionInfoByGoodsId(goodsId);
		
		
		// 특정 굿즈에 옥션 참여 인원이 아니면, 홈으로 돌려보내기 + chatPage에 보내줄 여러 정보 담음
		if (!assignUser(model,loginUser.getUsers_id(),auction)) {
			// 옥션에 참여하는 멤바가 아니면 나가야지
			return "redirect:/main";
		}

		// 모든 챗팅 리스트
		List<ChatVO> chats = service.getAllChat(loginUser.getUsers_id(), goodsId);
		model.addAttribute("chats", chats);
		model.addAttribute("images", goodsService.getImages(goodsId));

		return "/chat/chatPage";
	}

	private boolean assignUser(Model model, Integer users_id, List<Map<String, Object>> auction) {
		
		

		boolean result =true;
		System.out.println(MemberType.Seller.getType());
		
		Map<String, Object> sellerMap;
		Map<String,Object> buyerMap;
		
		
		//List에서 seller 와 buyer 구분 
		if((Integer)auction.get(0).get("auctions_ing_seller") == (Integer) auction.get(0).get("users_id")) {
			sellerMap = auction.get(0);
			buyerMap = auction.get(1);
		}else {
			sellerMap = auction.get(1);
			buyerMap  = auction.get(0);
		}
		
		//맵에 상태 저장하기
		sellerMap.put("memberType", "seller");
		buyerMap.put("memberType", "buyer");
		
		//내가 셀러인지 바이어인지, 구분 상대방 아이디도 넣기
		if((Integer)sellerMap.get("users_id") == users_id) {
			model.addAttribute("me",sellerMap);
			model.addAttribute("oppsite", buyerMap);
		}else if((Integer) buyerMap.get("users_id") == users_id){
			model.addAttribute("me", buyerMap);
			model.addAttribute("oppsite", sellerMap);
		}else {
			//유저는 아무곳에도 없음! 나가!
			return false;
		}
		

		return result;

	}

	@PostMapping("/send")
	@ResponseBody
	public boolean sendChat(ChatVO chat) {
		
		service.saveChat(chat);
		return true;
	}

	

}
