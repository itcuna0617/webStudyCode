package com.greedy.section01.forward;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/forward")
public class ReceiveInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");	 // 한글이 넘어온다면 doPost에서는 UTF-8인코딩 설정하고 시작하자.
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		System.out.println("아이디: " + userId);
		System.out.println("비밀번호: " + password);
		
		/*
		 * 이 부분에서는 id와 pwd에 해당하는 user의 정보를 select하는 비즈니스 로직(Business Logic - BL)이
		 * 수행되어야 한다.
		 * 우리는 제대로 조회가 되었다는 가정하에 xxx님 환영합니다라는 메시지를 출력해 주는 화면을 만들 것이다.
		 */
		
		request.setAttribute("userName", "홍길동");
		
		/* 동적으로 응답 시 제공할 화면을 만드는 서블릿에게 request와 response를 포워드한다.(다른 서블릿에게 상태값을 넘김) */
		RequestDispatcher rd = request.getRequestDispatcher("print");	// 포워딩 처리 할 서블릿의 요청 url을 넘김
		rd.forward(request, response);
	}
}






