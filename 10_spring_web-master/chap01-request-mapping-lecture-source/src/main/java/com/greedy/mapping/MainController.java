package com.greedy.mapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	/*
	 * "/"요청에 대해서 main()메소드가 키와 밸류 형태로 handler mapping에 세팅된다.
	 * 이 때 요청에 매핑 된 main()메소드를 핸들러 메소드라고 한다.
	 */
//	@RequestMapping(value="/", method=RequestMethod.GET)
//	@RequestMapping("/")
//	public String main() {
//		return "main";
//	}
	
	/* index.jsp 추가 후 */
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
}
