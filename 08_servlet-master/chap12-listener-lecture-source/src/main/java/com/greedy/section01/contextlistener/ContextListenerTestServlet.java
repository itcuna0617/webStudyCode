package com.greedy.section01.contextlistener;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/context")
public class ContextListenerTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("context listener 확인용 서블릿");
		
		ServletContext context = request.getServletContext();
		
		context.setAttribute("test", "value");
		
		context.setAttribute("test2", "value");
		
		context.setAttribute("test2", "value2");
		
		context.removeAttribute("test");
	}
}
