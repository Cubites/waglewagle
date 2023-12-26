package kr.co.waglewagle.goods.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.waglewagle.bids.service.BidsService;
import kr.co.waglewagle.domain.GoodsVO;
import kr.co.waglewagle.domain.UsersVO;
import kr.co.waglewagle.goods.service.GoodsService;
import kr.co.waglewagle.goods.won.FileStore;
import kr.co.waglewagle.goods.won.GoodsFormVO;
import kr.co.waglewagle.goods.won.GoodsPageVO;
import kr.co.waglewagle.goods.won.UploadImage;
import kr.co.waglewagle.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class GoodsController {

	// 생성자주입 - file 저장을 돕는 객체
	private final FileStore fileStore;
	private final GoodsService goodsService;
	private final BidsService bidsService;
	private final UsersService usersService;

	// 상품 등록 form으로 이동
	@GetMapping("/goods/regist")
	public String goForm(GoodsFormVO vo) {
		return "goods/regist";
	}

	@PostMapping("/goods/regist")
	public String regist(@Validated GoodsFormVO vo, BindingResult br, Model model,
			@SessionAttribute(name = "users_info", required = false) UsersVO LoginUserId
			,RedirectAttributes rs) {
		
		

		// Spring: 태그를 이용해서 출력할 수도 있지만..그냥 br담아서 출력하는 것도 나쁘지않은 선택!
		// 글로벌 오류 출력하기 위해 담음
		model.addAttribute("error", br);
		
		// 파일 아무것도 첨부 안했을 때 실행되는 로직
		if (vo.getImages() == null || "".equals(vo.getImages().get(0).getOriginalFilename())) {

			br.reject("listError", new Object[] {}, "상품 이미지는 최소 1개이상 등록해주세요");
		}

		if (br.hasErrors()) { // 에러가 있다면, 다시 goods/regist에 form으로 돌아감
			return "goods/regist";
		}

		// 정상처리 로직//////////////////////////////////////////////////////////////////////////////
		// DB에 넣기전 가공 로직
		List<UploadImage> list = new ArrayList<>();
		list = fileStore.storeFiles(vo.getImages());
		String th_path = fileStore.thumbNailImagePath(list, vo.getGoods_th_img());
		// fullPath가 필요한것 이름은 이제 필요 없어서!! 다시 set해줌
		vo.setGoods_th_img(th_path);

		
		vo.setUsers_id(LoginUserId.getUsers_id()); 

		
		// GoodsVO registedGoodsVO = convertGoodsFormToGoods(vo,LoginUserId);
		int resultOfGoodsRegist = goodsService.registGoods(vo);
		// 이미지 파일들을 DB에 저장하기 위해선 상품id와 사진 경로가 필요함!
		int resultOfImageRegist = goodsService.registImages(vo.getGoods_id(), list);

		//model.addAttribute("goodsId", vo.getGoods_id());
		// post로 설정해둬서 뒤로가기하면 다시 같은 상품이 등록 될 수 있어서 막기 위해 경우 페이지 지정
		
		rs.addAttribute("goods_id", vo.getGoods_id());
		return "redirect:/goods/{goods_id}";
	}

	// 상품 등록 후 상품 상세화면으로 이동
	@GetMapping("/goods/{goods_id}")
	public String testDetail(Model model, @SessionAttribute(name = "users_info", required = false) UsersVO loginUser,
			@PathVariable("goods_id") Integer goodsId) {

		// 추후 성능문제 발생시 쿼리 합칠 것
		// 같이 가지고 올 수 있는게 없네
		
		GoodsVO goods = goodsService.getGoods(goodsId);
		UsersVO seller = usersService.userInfo(goods.getUsers_id());
				
		model.addAttribute("seller",seller);
		// 굿즈아이디
		model.addAttribute("goods", goods);
		// 이미지 가져오기
		model.addAttribute("images", goodsService.getImages(goodsId));
		
		// 찜수 가져오기
		model.addAttribute("favorsCnt", goodsService.getFavorsCnt(goodsId));
		// 경매 참여인원 가져오기
		model.addAttribute("bidsCnt", bidsService.getBidsCnt(goodsId));
		
		
		// 로그인한 유저의 정보 가져오기
		// 유저 point가져오기
		model.addAttribute("usersPoint", usersService.getPoint(loginUser.getUsers_id()));

		model.addAttribute("userFavor", goodsService.isFavoritGoods(loginUser.getUsers_id(), goodsId));
		// 최대 호가 금액 가져오기
		model.addAttribute("maxBids", bidsService.selectMaxBidsByUsersId(loginUser.getUsers_id(), goodsId));
		
		
		
		
//					
//					

		return "goods/goodsDetail";
	}

	// 상품 검색 (검색어 이용)
	@GetMapping("/goods/search/word")
	public String searchGoodsByWord(@RequestParam(value = "goods_title", required = false, defaultValue = "#") String searchWord,
			Model model) {
		// 상품 제목에 포함되어 있으면 다 불러오기
		List<GoodsVO> goodsList = goodsService.getGoodsByWord(searchWord);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("searchWord", searchWord);
		return "goods/search";
	}
	
	// 상품 검색 (카테고리 선택)
	@GetMapping("/goods/search/category")
	public String searchGoodsByCategory(@RequestParam(value = "category_id", required = false, defaultValue = "#") Integer categoryId,
			Model model) {
		// 선택한 카테고리에 해당하는 상품 다 불러오기
		List<GoodsVO> goodsList = goodsService.getGoodsByCategory(categoryId);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("searchCategory", categoryId);
		return "goods/search";
	}
	
	// 상품 검색 (카테고리 & 검색어)
	@GetMapping("/goods/search")
	public String searchGoodsByBoth(@RequestParam(value = "category_id", required = false, defaultValue = "#") Integer categoryId,
			@RequestParam(value = "goods_title", required = false, defaultValue = "#") String searchWord,
			Model model) {
		
		System.out.println();
		System.out.println(">>> 검색할거야 ---->   " +categoryId+ searchWord);
		System.out.println();
		
		// 선택한 카테고리에 해당하는 상품 다 불러오기
		List<GoodsVO> goodsList = goodsService.getGoodsByBoth(categoryId, searchWord);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("searchCategory", categoryId);
		model.addAttribute("searchWord", searchWord);
		return "goods/search";
	}

	
	
	///장원 수정
		@GetMapping("/goods/search2")
		public String searchGoods(
				@RequestParam(required = false,defaultValue = "1")Integer pageNum
				,@RequestParam(required=false,defaultValue = "")String searchWord
				,@RequestParam(required=false,defaultValue="")Integer category_id
				,Model model) {
			
			log.info("검색어 {}",searchWord);
			log.info("{} 카테고리 id",category_id);
			//초기 값 받아서 처리 
			GoodsPageVO page = new GoodsPageVO(pageNum,category_id,searchWord);
			List<Map<String,Object>>  list = goodsService.searchGoods2(page);
			if(list.size() > 0) {
			page.setTotalrowsCnt((Long)list.get(0).get("totalCnt"));
			}
			page.setGoodsList(list);
			model.addAttribute("page", page);
			
			log.info("page {}",page);
			
			return  "goods/search";
		}
	 
		
		
		
		@PostMapping(path = "/goods/searchScroll", produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public GoodsPageVO searchGoodsAjax(
				@RequestParam(required = false,defaultValue = "1")Integer pageNum
				,@RequestParam(required=false,defaultValue = "")String searchWord
				,@RequestParam(required=false,defaultValue="")Integer category_id) {
			
		
			
			//값 받아서 처리 
			GoodsPageVO page = new GoodsPageVO(pageNum,category_id,searchWord);
			System.out.println("검색 page ="+page);
			List<Map<String,Object>>  list = goodsService.searchGoods2(page);
			if(list.size() >0) {
			page.setTotalrowsCnt((Long)list.get(0).get("totalCnt"));
			}
			page.setGoodsList(list);
			
			
			log.info("page {}",page);
			
			return  page;
		}
		///장원 수정

}
