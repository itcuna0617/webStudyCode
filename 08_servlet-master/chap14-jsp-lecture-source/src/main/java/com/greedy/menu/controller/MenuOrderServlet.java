package com.greedy.menu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/menu/order")
public class MenuOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("menu 주문용 서블릿으로 도착");
		
		request.setCharacterEncoding("UTF-8");	// post 요청으로 넘어오는 한글 parameter는 인코딩 처리 할 것
		
		/*
		 * 서블릿은 크게 3가지 일을 한다고 했다.
		 * 1. 요청받은 값 확인 및 검증
		 * 2. 비즈니스 로직 처리
		 * 3. 응답 페이지 생성 후 응답(JSP)
		 */
		
		/* 1. 요청받은 값 확인 및 검증 */
		String menuName = request.getParameter("menuName");
		int amount = Integer.valueOf(request.getParameter("amount"));
		
		System.out.println("주문한 메뉴는 " + menuName + "이고 수량은 " + amount + "개");
		
		/* 2. 비즈니스 로직 처리 */
		/*
		 * 비즈니스 로직은 대부분 DB에 CRUD 연산 등을 이용해 이뤄지게 된다.
		 * 여기서는 DB연결은 안 할 것이기 때문에 간단한 로직 처리만 해보자.
		 */
		int orderPrice = 0;
		switch(menuName) {
			case "햄버거": orderPrice = 6000 * amount; break;
			case "짜장면": orderPrice = 7000 * amount; break;
			case "짬뽕": orderPrice = 8000 * amount; break;
			case "순대국": orderPrice = 5000 * amount; break;
		}
		
		System.out.println("주문한 메뉴의 합계는 " + orderPrice + "원");
		
		/* Service, DAO 계층을 거쳐 DB에 Insert를 다녀왔다면... 그리고 성공했다면... */
		
		/* 3. 응답 페이지 생성 후 응답은 JSP에게 위임한다.(결과를 응답해 줄 JSP로 포워딩) */
		request.setAttribute("menuName", menuName);
		request.setAttribute("amount", amount);
		request.setAttribute("orderPrice", orderPrice);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/5_response.jsp");
		rd.forward(request, response);
	}
}




