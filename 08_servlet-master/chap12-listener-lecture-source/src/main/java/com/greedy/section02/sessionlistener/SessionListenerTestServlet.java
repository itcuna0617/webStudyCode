package com.greedy.section02.sessionlistener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session")
public class SessionListenerTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("userName", "honggildong");
		session.setAttribute("age", 20);
		session.setAttribute("gender", "M");
		
		session.setAttribute("userName", "hong");
		
		session.setAttribute("user", new UserDTO("honggildong", 20));
		
//		System.out.println(session.getAttribute("user"));
		
		session.removeAttribute("user");
		
		session.invalidate();		        // 해당 HttpSession의 모든 attribute 무효화
		
	}
}










