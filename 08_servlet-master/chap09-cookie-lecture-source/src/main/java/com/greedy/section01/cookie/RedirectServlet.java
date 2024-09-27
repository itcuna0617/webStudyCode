package com.greedy.section01.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		System.out.println("====== redirect 후 param에서 값 꺼내보기 ======");
		System.out.println("이름: " + firstName);
		System.out.println("성: " + lastName);
		
		/*
		 * 다른 서블릿에서 redirect되어 오면 request객체는 서로 공유되지 않는다.
		 * 따라서 쿠키 또는 세션을 통해 값을 공유할 수 있는데 우선 이번 예제에서는 쿠키로 확인하자.
		 */
		
		/*
		 * 쿠키를 불러오는 방법
		 * 1. request에서 쿠키 목록을 쿠키 배열 형태로 꺼내온다.
		 * 2. 쿠키의 getName과 getValue를 이용해 쿠키에 담긴 값을 사용한다.
		 */
		Cookie[] cookies = request.getCookies();
		
		for(Cookie cookie : cookies) {
			System.out.println(cookie.getName() + ": " + cookie.getValue());
			
			if("firstName".equals(cookie.getName())) {
				firstName = cookie.getValue();
			} else if("lastName".equals(cookie.getName())) {
				lastName = cookie.getValue();
			}
		}
		
		StringBuilder responseText = new StringBuilder();
		responseText.append("<h3>your first name is ")
		            .append(firstName)
		            .append("<br>and last name is ")
		            .append(lastName)
		            .append("</h3>");
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print(responseText);
		out.flush();
		out.close();
	}

}




