package com.greedy.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

	@GetMapping("/simple-null")
	public String simpleNullPointerExceptionTest() {
		String str = null;
		char ch = str.charAt(0);	// NullPointerException(NPE) 발생!
		
		return "main";
	}
	
	@GetMapping("/simple-user")
	public String simpleUserExceptionTest() throws MemberRegistException{
		
		boolean flag = true;
		if(flag)
		throw new MemberRegistException("미안하지만 당신은 회원 가입이 안됨");
		
		return "main";
	}
	
	/* 클래스별 예외 핸들링 */
	/* 전역 예외 핸들링이 있더라도 현재 이 클래스에서 발생 할 예외는 이 메소드가 핸들링 함 */
	@ExceptionHandler(NullPointerException.class)
	public String nullPointerExceptionHandler(NullPointerException exception) {
		System.out.println("이 메소드를 쓰는지 확인");
		return "error/nullPointer";
	}
	
	/* 
	 * 전역 예외 핸들링은 발생한 예외를 exception이라는 키로 requestScope로 던져주지만
	 * @ExceptionHandler 메소드는 매개변수까지만 넘겨주기 때문에 다시 model에 담아 넘겨 주어야
	 * 해당 뷰 템플릿에까지 전달 된다.
	 */
	@ExceptionHandler(MemberRegistException.class)
	public String userExceptionHandler(Model model, MemberRegistException exception) {	
		
		model.addAttribute("exception", exception);
		model.addAttribute("exceptionMessage", "회원가입 당신은 안된다니깐!");
		return "error/memberRegist";
	}
}





