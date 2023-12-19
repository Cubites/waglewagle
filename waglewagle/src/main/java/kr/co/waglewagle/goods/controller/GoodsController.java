package kr.co.waglewagle.goods.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.waglewagle.domain.GoodsVO;
import kr.co.waglewagle.domain.UsersVO;
import kr.co.waglewagle.goods.service.GoodsService;
import kr.co.waglewagle.goods.won.FileStore;
import kr.co.waglewagle.goods.won.GoodsFormVO;
import kr.co.waglewagle.goods.won.UploadImage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Controller
@Slf4j
public class GoodsController {

	
     	//생성자주입 - file 저장을 돕는 객체
		private final FileStore fileStore;
		private final GoodsService service;
		
		//상품 등록 form으로 이동
		@GetMapping("/goods/regist")
		public String goForm(GoodsFormVO vo) {
			return "goods/regist";
		}
		
		@PostMapping("/goods/regist")
		public String regist(@Validated GoodsFormVO vo, BindingResult br, 
							 Model model,
							 @SessionAttribute(name = "loginUser",required = false) UsersVO LoginUserId) {
							 //Session에서 로그인한 유저 정보 가져옴 required false 
			
			
			
			//Spring: 태그를 이용해서 출력할 수도 있지만..그냥 br담아서 출력하는 것도 나쁘지않은 선택!
			//글로벌 오류 출력하기 위해 담음
			model.addAttribute("error",br);
			
			//파일 아무것도 첨부 안했을 때 실행되는 로직
			if(vo.getImages() == null || "".equals(vo.getImages().get(0).getOriginalFilename())) {
				
				br.reject("listError", new Object[] {}, "상품 이미지는 최소 1개이상 등록해주세요");
			}
			
			if(br.hasErrors()) { //에러가 있다면, 다시 goods/regist에 form으로 돌아감 
				return "goods/regist";
			}
			
			//정상처리 로직
			//DB에 넣기전 가공 로직
			List<UploadImage> list = new ArrayList<>();
			list = fileStore.storeFiles(vo.getImages());
			String th_path = fileStore.thumbNailImagePath(list, vo.getGoods_th_img());
			//fullPath가 필요한것 이름은 이제 필요 없어서!! 다시 set해줌
			vo.setGoods_th_img(th_path);
			
			//로그인 유저id받아야함 추후에 반드시 고칠 것 !
			//sess.getAttribute(th_path); 
			vo.setUsers_id(1000); //테스트용 1000
			
			//추후 컨버터로 등록할 것 -> 현재 Form - service 올바르지 못하다! 단,date -> Timestamp로 고쳐야함
			//GoodsVO registedGoodsVO = convertGoodsFormToGoods(vo,LoginUserId);
			
			int resultOfGoodsRegist = service.registGoods(vo); 
			//이미지 파일들을 DB에 저장하기 위해선 상품id와 사진 경로가 필요함!
			int resultOfImageRegist = service.registImages(vo.getGoods_id(),list);
		
			
			
			return "home";
		}

		private GoodsVO convertGoodsFormToGoods(GoodsFormVO vo, UsersVO loginUserId) {
			GoodsVO goods = new GoodsVO();
			
			//goodsVO form vo의 값 담을 것
			//추후의 로그인 유저 id로 고칠 것
			if(loginUserId == null) {
			goods.setUsers_id(1000);
			}else {
				goods.setUsers_id(loginUserId.getUsers_id());
			}
			goods.setGoods_address(vo.getGoods_addr());
			goods.setGoods_comment(vo.getGoods_comment());
			//goods.setGoods_exp(vo.getGoods_exp());
			goods.setGoods_start_price(vo.getGoods_start_price());
			goods.setGoods_title(vo.getGoods_title());
			goods.setGoods_avg_price(vo.getGoods_start_price());
			goods.setGoods_th_img(vo.getGoods_th_img());
			
			
			return null;
		}

		
		
		
}
