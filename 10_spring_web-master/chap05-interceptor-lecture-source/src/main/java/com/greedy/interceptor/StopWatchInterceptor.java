package com.greedy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
 * 인터셉터를 사용하는 경우(목적)
 * : 로그인 체크, 권한 체크, 프로그램 실행시간 계산 작업 로그 처리, 업로드 파일 처리, 로케일(지역) 설정 등
 */
public class StopWatchInterceptor implements HandlerInterceptor{
	
	/* 필터와 달리 인터셉터는 빈을 활용할 수 있다.(생성자 주입 활용함) */
	private final MemberService mService;
	
	@Autowired
	public StopWatchInterceptor(MemberService mService) {
		this.mService = mService;
	}
	
	/* 반환값의 boolean형에 따라 핸들러 메소드가 실행 될 수도 안 될 수도 있도록 핸들러 메소드 호출 이전에 동작 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) {
		System.out.println("핸들러 메소드 가기 전...");
		
		long startTime = System.currentTimeMillis();
		
		request.setAttribute("startTime", startTime);
		
		return true;
	}
	
	/* 해당 View Template이 렌더링 되기 전에 ModelAndView를 수정할 수 있도록 매개변수에 ModelAndView 제공 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, @Nullable ModelAndView modelAndView) {
		System.out.println("핸들러 메소드 이후...");
		
		long startTime = (Long)request.getAttribute("startTime");
		
		long endTime = System.currentTimeMillis();
		
		modelAndView.addObject("interval", endTime - startTime);
	}
	
	/* 클라이언트에게 Response를 전달하기 전에 Exception을 핸들링 하거나 Response 객체를 수정할 수 있음 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, @Nullable Exception ex) {
		System.out.println("View가 렌더링 된 후(View 템플릿(JSP) 다녀온 후)...");
		
		mService.method();
	}
	
}
