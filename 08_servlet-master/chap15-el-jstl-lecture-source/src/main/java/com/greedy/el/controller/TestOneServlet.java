package com.greedy.el.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/test1")
public class TestOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/* 1. request에 attribute 4개 담기 */
		request.setAttribute("name",  "홍길동");
		request.setAttribute("age", 20);
		request.setAttribute("phone", "010-1234-5678");
		
		List<String> items = new ArrayList<>();
		items.add("apple");
		items.add("banana");
		items.add("melon");
		
		request.setAttribute("items", items);
		
		/* 2. session에 attribute 1개 담기 */
		HttpSession session = request.getSession();
		session.setAttribute("name", "유관순");
		
		/* 3. 다른 jsp로 포워딩 */
//		RequestDispatcher view = request.getRequestDispatcher("/views/el/testEl1.jsp");
//		view.forward(request, response);
		
		request.getRequestDispatcher("/views/el/testEl1.jsp").forward(request, response);
	
	}
}
