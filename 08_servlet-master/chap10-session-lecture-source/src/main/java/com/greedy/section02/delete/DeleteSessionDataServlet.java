package com.greedy.section02.delete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/delete")
public class DeleteSessionDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		System.out.println("attribute 만료 전 firstName: " + session.getAttribute("firstName"));
		System.out.println("attribute 만료 전 lastName: " + session.getAttribute("lastName"));
		
		session.invalidate();		// HttpSession 객체의 Attribute 값들을 무효화
		System.out.println("attribute 만료 후 firstName: " + session.getAttribute("firstName"));
		System.out.println("attribute 만료 후 lastName: " + session.getAttribute("lastName"));
	}
}
