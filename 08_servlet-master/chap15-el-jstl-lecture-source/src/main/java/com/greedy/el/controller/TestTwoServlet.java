package com.greedy.el.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greedy.el.model.dto.MemberDTO;

@WebServlet("/test2")
public class TestTwoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDTO member =
				new MemberDTO("홍길동", 20, "010-123-1234", "hong@greedy.ac.kr");
		
		request.setAttribute("member", member);
		
		request.getRequestDispatcher("/views/el/testEl2.jsp").forward(request, response);
	}
}








