package com.greedy.section02.annotation;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value="/annotation-lifecycle")
public class LifeCycleTestServlet extends HttpServlet{
	private static final long serialVersionUID = -5632648787843109446L;
	
	public LifeCycleTestServlet() {
		System.out.println("annotation 방식 기본생성자 실행!");
	}
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("annotation 매핑 init() 메소드 호출");
	}
	
	public void destroy() {
		System.out.println("annotation 매핑 destroy() 메소드 호출");
	}
	
	public void service(ServletRequest request, ServletResponse response)
		throws ServletException, IOException {
		System.out.println("annotation 매핑 service() 메소드 호출");
	}

}
