package com.greedy.section01.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session")
public class SessionHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		System.out.println("이름: " + firstName);
		System.out.println("성: " + lastName);
		
		/*
		 * request.getSession으로 HttpSession 객체를 생성하는 이유
		 * : 요청이 서블릿으로 들어올 때 HttpServletRequest에는 JSESSIONID라는 값이 Header에 같이 포함되어 넘어온다.
		 *   그럼 해당 JSESSIONID에 맞는 HttpSession 객체를 생성해서 해당 사용자를 위한 전용 저장 공간을 제공하게 
		 *   되는 것이다.
		 *   (요청을 보낸 사람 전용 Session 저장 공간을 생성했다고 보면 된다.)
		 *    
		 */
		HttpSession session = request.getSession();
		
		System.out.println("session 객체의 default 유지 시간(초): " + session.getMaxInactiveInterval());
		
		session.setMaxInactiveInterval(60 * 10); 		      // 세션 만료 시간을 10분으로 설정(초 단위)
		
		System.out.println("우리가 설정한 session 객체의 유지 시간(초): " + session.getMaxInactiveInterval());
		
		System.out.println("session id: " + session.getId()); // 요청한 클라이언트의 브라우저에 저장 된(쿠키값) JSESSIONID가 나옴 
		
		session.setAttribute("firstName", firstName);
		session.setAttribute("lastName", lastName);
		
		response.sendRedirect("redirect");
	}
}



















