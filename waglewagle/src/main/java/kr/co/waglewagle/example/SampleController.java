package kr.co.waglewagle.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
	
	@GetMapping("/sample")
	public String mainPage() {
		return "sample";
	}

}
