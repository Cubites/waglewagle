package kr.co.waglewagle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestMainController {
	
	@GetMapping("/testIndex.do")
	public String mainPage() {
		return "testIndex";
	}
}
