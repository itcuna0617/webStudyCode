package com.greedy.section01.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie")
public class CookieHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	// 한글 깨짐 방지
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		System.out.println("이름: " + firstName);
		System.out.println("성: " + lastName);
		
		/*
		 * 쿠키(클라이언트의 브라우저에 저장)를 사용하는 방법
		 * 1. 쿠키 인스턴스를 생성한다.
		 * 2. 해당 쿠키의 만료시간을 설정한다.
		 * 3. 응답 헤더에 쿠키를 담는다.
		 * 4. 응답한다.
		 */
		Cookie firstNameCookie = new Cookie("firstName", firstName);
		Cookie lastNameCookie = new Cookie("lastName", lastName);
		
		firstNameCookie.setMaxAge(60 * 60 * 24);	// 초 단위로 설정. 하루를 만료시간으로 두는 예시
		lastNameCookie.setMaxAge(60 * 60 * 24);
		
		response.addCookie(firstNameCookie);
		response.addCookie(lastNameCookie);
		
		response.sendRedirect("redirect");
	}
}










