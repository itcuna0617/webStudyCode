package com.greedy.el.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greedy.el.model.dto.MemberDTO;

@WebServlet("/test4")
public class TestFourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDTO requestMember = 
				new MemberDTO("홍길동", 20, "010-123-1111", "hong@greedy.com");
		
		MemberDTO sessionMember =
				new MemberDTO("유관순", 18, "010-321-3333", "yoo@greedy.com");
		
		request.setAttribute("member", requestMember);
		
		request.getSession().setAttribute("member", sessionMember);
		
		request.getRequestDispatcher("/views/el/testEl4.jsp").forward(request, response);
	}
}



