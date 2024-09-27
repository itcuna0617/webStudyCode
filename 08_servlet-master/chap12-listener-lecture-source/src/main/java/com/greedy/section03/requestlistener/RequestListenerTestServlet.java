package com.greedy.section03.requestlistener;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/request")
public class RequestListenerTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("userName", "hoggildong");
		request.setAttribute("age", 20);
		request.setAttribute("gender", "M");
		
		request.setAttribute("userName", "hong");
		
		request.removeAttribute("gender");
	}
}
