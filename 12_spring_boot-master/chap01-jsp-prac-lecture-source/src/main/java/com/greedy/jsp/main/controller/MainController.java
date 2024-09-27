package com.greedy.jsp.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String main() {
		System.out.println("잘 오니?");
		return "main";
	}
}
