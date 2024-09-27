package com.greedy.section01.xml;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class LifeCycleTestServlet extends HttpServlet{
	private static final long serialVersionUID = 2433430407298550474L;

	/* 기본 생성자 */
	public LifeCycleTestServlet() {
		System.out.println("xml 방식 기본 생성자 실행!");
	}
	
	/* 서블릿의 요청이 최초인 경우 서블릿 객체를 생성하고 자동으로 호출하게 되는 메소드 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("xml 매핑 init() 메소드 호출!!!!!");
	}
	
	/* 컨테이너가 종료될 때 호출하는 메소드이며 주로 자원을 반납하는 용도로 사용한다. */
	public void destroy() {
		System.out.println("xml 매핑 destroy() 메소드 호출");
	}
	
	/*
	 * 서블릿 컨테이너에 의해 호출되며 최초 요청 시에만 init() 이후에 동작하고,
	 * 두 번째 요청부터는 바로 service()만 호출하게 된다.
	 */
	public void service(ServletRequest request, ServletResponse response) 
		throws ServletException, IOException {
		System.out.println("xml 매핑 service() 메소드 호출");
	}
	
	/* 
	 * 서블릿 객체의 라이프 사이클
	 * init() -> service() -> doGet() 또는 doPost() -> destroy() 
	 */
}
