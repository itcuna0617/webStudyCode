package com.greedy.parameter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
//	@RequestMapping("/main")
//	public String showMain() {
//		return "main";
//	}
	
	@RequestMapping("/main")
	public void showMain() {}
}
