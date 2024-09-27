package com.greedy.resolver;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResolverController {

	@GetMapping("/string")
	public String stringReturning(Model model) {
		model.addAttribute("message", "문자열로 뷰 이름 반환함...");
		
		return "result";
	}
	
	/*
	 * Model과 HttpServletRequest의 차이점
	 * 
	 * HttpServletRequest에 담긴 값은 Redirect 시 전환된 화면에서 활용할 수 없다.
	 * (redirect는 request객체를 유지하지 않고 새로 만든다.)
	 * 
	 * 반면, Model은 Redirect 시에는 전환된 화면에 parameter로 담긴 값을 전송하고
	 * forward 시에는 requestScope로 담긴 값을 전송한다. 
	 */
	@GetMapping("/string-redirect")
	public String stringRedirect(Model model) throws UnsupportedEncodingException {
		
//		model.addAttribute("message", "test");	// redirect 시에는 parameter의 키와 밸류가 됨
		
		/* 화면단에서는 js에서 decodeURIComponent를 써서 디코딩 해야 한글이 깨지지 않음 */
		model.addAttribute("message", URLEncoder.encode("세종대왕님이 만들어 주신 한글", "UTF-8"));	// redirect 시에는 parameter의 키와 밸류가 됨
		
		return "redirect:main";		// view의 이름이 아님, /main으로 재요청 하라는 뜻
	}
	
	/*
	 * 리다이렉트 시 URL에 노출되지 않게 flash영역에(한번의 request영역)에 담아서 redirect 할 수 있다.
	 * (이 때 Model이 아닌 RedirectAttributes를 써야 한다.)
	 * 전달 된 값을 꺼낼 때는 requestScope에서 꺼낼 수 있다.
	 * (주의할 사항은 내부적으로 sessionScope에 값을 담고 소멸시키는 과정이 있으므로 sessionScope에
	 * 동일한 키 값이 존재하지 않아야 한다.)
	 */
	@GetMapping("string-redirect-attr")
	public String stringRedirectFlashAttribute(RedirectAttributes rttr) {
		rttr.addFlashAttribute("flashMessage", "리다이렉트 attr 사용하여 redirect");
		
		return "redirect:main";
	}
	
	@GetMapping("/modelandview")
	public ModelAndView modelAndViewReturning(ModelAndView mv) {
		
		/*
		 * ModelAndView
		 * 모델(Model 객체)과 뷰(ViewTemplate 이름)를 합친 개념이다.
		 * 핸들러 어댑터가 핸들러 메소드를 호출하고 반환받은 ModelAndView를
		 * DispatcherServlet에 반환한다.
		 * DispatcherServlet은 ModelAndView에 있는 viewName값을
		 * viewResolver에게 주게 되고 prefix와 suffix를 결합하여 뷰를 생성하게 된다.
		 */
		mv.addObject("message", "ModelAndView를 이용한 모델과 뷰 반환");
		mv.setViewName("result");
		
		return mv;
	}
	
	@GetMapping("/mv-redirect")
	public ModelAndView modelAndViewRedirect(ModelAndView mv) throws UnsupportedEncodingException {
		mv.addObject("message2", URLEncoder.encode("ModelAndView를 이용한 redirect", "UTF-8"));
		mv.setViewName("redirect:main");
		
		return mv;
	}
	
	@GetMapping("mv-redirect-attr")
	public ModelAndView modelAndViewRedirectFlashAttribute(
				RedirectAttributes rttr,
				ModelAndView mv
			) {
		rttr.addFlashAttribute("flashMessage", "ModelAndView와 flashattr을 사용하여 redirect");
		mv.setViewName("redirect:main");	// ModelAndView에 View의 정보만 담아도 상관은 없다.
		
		return mv;
	}
}






