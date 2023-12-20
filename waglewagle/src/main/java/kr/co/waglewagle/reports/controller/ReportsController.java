package kr.co.waglewagle.reports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.waglewagle.domain.GoodsVO;
import kr.co.waglewagle.domain.ReportsVO;
import kr.co.waglewagle.goods.service.GoodsService;
import kr.co.waglewagle.reports.service.ReportsService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ReportsController {

	@Autowired
	private ReportsService service;
	@Autowired
	private GoodsService goodsService;
	
	//신고글과 신고 상품 받음
	@GetMapping("/report/{goods_id}/{users_id}")
	public String reportPage(
			@PathVariable("goods_id") Integer goods_id
			,@PathVariable("users_id") Integer users_id
			,Model model, RedirectAttributes rs) {
		
		//신고상품정보 찾아옴
		GoodsVO reportedGoods = goodsService.getGoods(goods_id);
		
		//만약 자신의 글에 신고하는거면 막음
		if(reportedGoods.getUsers_id() == users_id ) {
			rs.addFlashAttribute("rejectmsg", "본인이 올린 글은 신고할 수 없습니다.");
			rs.addAttribute("goods_id", reportedGoods.getGoods_id());
			return "redirect:/goods/{goods_id}";
		}
		
		//redirect하면서 goods정보 가지고감
		rs.addFlashAttribute("goods", goodsService.getGoods(goods_id));
		
		
		
		return "redirect:/report/reportForm";
	}
	
	@GetMapping("/report/reportForm")
	public String reportForm() {
		return "/report/reportForm";
	}
	
	@PostMapping("/report/goods")
	public String report(@ModelAttribute ReportsVO report, RedirectAttributes rs,Model model) {
		
		log.info("report post ReportsVO {}",report);

		
		boolean result = service.reportGoods(report);
		model.addAttribute("result", "신고등록에 성공했습니다.");
		model.addAttribute("goods_id", report.getGoods_id());
		return "/report/reportForm";
	}
	
}
