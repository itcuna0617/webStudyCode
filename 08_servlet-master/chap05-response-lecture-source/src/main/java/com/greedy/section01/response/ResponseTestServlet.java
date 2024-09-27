package com.greedy.section01.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/response")
public class ResponseTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("get요청 오나");
		
		/*
		 * 서블릿이 하는 역할은 크게 3가지라고 볼 수 있다.(컨트롤러 개념)
		 * 1. 요청 받기(Http method GET/POST 요청에 따른 parameter로 전달 받은 데이터를 꺼낼 수 있다.)
		 * 2. 비즈니스 로직 처리(DB접속과 CRUD에 대한 로직 처리 -> 서비스를 호출(MVC))
		 * 3. 응답하기(문자열로 동적인 웹(HTML 페이지)페이지를 만들어서 스트림을 이용하여 내보내기)
		 */
		
		StringBuilder responseBuilder = new StringBuilder();
		responseBuilder.append("<!doctype html>\n")
					   .append("<html>\n")
					   .append("<head>\n")
					   .append("</head>\n")
					   .append("<body>\n")
					   .append("<h1>한글servlet response</h1>")
					   .append("</body>\n")
					   .append("</html>");
		
//		System.out.println(responseBuilder);
		
		/*
		 * 응답 시에 별도로 MIME 타입과 인코딩을 지정하지 않으면 기본적으로 설정된 인코딩 방식을 따르게 된다.
		 * (ISO-8859-1)
		 * 따라서 한글로 페이지를 응답하는 경우 한글이 깨지지 않도록 MIME 타입과 인코딩을 설정하고
		 * 스트림을 열어서 페이지 정보를 내보내야 한다.(PrintWriter 스트림 열기 전에 적용할 것!)
		 */
		
//		response.setContentType("text/html");
//		response.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print(responseBuilder);
		out.flush();
		out.close();
	}

}





