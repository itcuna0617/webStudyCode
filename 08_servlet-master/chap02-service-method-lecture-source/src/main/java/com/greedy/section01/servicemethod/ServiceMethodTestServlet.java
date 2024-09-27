package com.greedy.section01.servicemethod;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/request-service")
public class ServiceMethodTestServlet extends HttpServlet{
	private static final long serialVersionUID = 5157944641552040328L;

	public void service(ServletRequest request, ServletResponse response)
				throws ServletException, IOException {
		
		/*
		 * HttpServletRequest는 ServletRequest 타입을 상속 받아서 구현하였으며,
		 * Http 프로토콜의 정보를 담고 있기 때문에 실제 사용 시에는
		 * HttpServletRequest타입으로 다운 캐스팅해서 사용해야 한다.
		 */
		System.out.println(request);
		System.out.println(response);
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		String httpMethod = httpRequest.getMethod();
//		System.out.println("httpMethod 방식: " + httpMethod);
		
		if("GET".equals(httpMethod)) {
//			System.out.println("GET 요청이다.");
			doGet(httpRequest, httpResponse);
		} else if("POST".equals(httpMethod)) {
//			System.out.println("POST 요청이다.");
			doPost(httpRequest, httpResponse);
		} else if("PUT".equals(httpMethod)) {
			doPut(httpRequest, httpResponse);
		}
	} 
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		
		/* GET 요청을 처리 할 메소드로 요청과 응답 정보가 전달 된다. */
		System.out.println("GET 요청을 처리 할 메소드 호출 됨...");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		
		/* POST 요청을 처리 할 메소드로 요청과 응답 정보가 전달 된다. */
		System.out.println("POST 요청을 처리 할 메소드 호출 됨...");
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		System.out.println("PUT 요청을 처리 할 메소드 호출 됨...");
	}
	
}












