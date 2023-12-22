package kr.co.waglewagle.admins.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.javassist.CtNewConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.waglewagle.admins.service.GoodsManageService;
import kr.co.waglewagle.domain.GoodsVO;


@Controller
public class GoodsManageController{
	@Autowired
	private GoodsManageService service;

	//상품리스트 출력
//	@GetMapping("/admin/goodsManageList")
//	public String adminGoodsList(Model model,GoodsVO vo) {
//		List<GoodsVO> list = service.goodsList();
//		List<Map<String, Object>> list = service.adminGoodsList();
//		model.addAttribute("list",list);
//		return "/admin/goodsManageList";
//	}

	//검색 + 페이징
	@GetMapping("/admin/goodsManageList")
	public String adminGoodsList(Model model,GoodsVO vo) {
//		List<GoodsVO> list = service.goodsList();
		Map<String, Object> list = service.adminGoodsList(vo);
		model.addAttribute("list",list);
		return "/admin/goodsManageList";
	}

	//ajax이용 상태 변경
	@ResponseBody
	@PostMapping("/admin/goodsStatus")
	public Integer goodsStatus(@RequestBody Map<String, Integer> status ,Model model) {
		//status.get("goods_id"); //goods_id꺼낸거야 status는 map으로 goods_id랑 goods_status 가지고 있는데..
		int a = service.goodsAccess(status); // 서비스에서 a에 담아준거고...아래 return할때 사용할라구!
//		System.out.println("=======>"+status.get("goods_id")); //잘찍히는지 확인해보기
		return a==1 ? status.get("goods_access") :(status.get("goods_access")==0 ? 1:0 );
	}
}