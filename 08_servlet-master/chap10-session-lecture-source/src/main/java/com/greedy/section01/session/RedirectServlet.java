package com.greedy.section01.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String firstName = (String)session.getAttribute("firstName");
		String lastName = (String)session.getAttribute("lastName");
		
		System.out.println("firstName: " + firstName);
		System.out.println("lastName: " + lastName);
	
		StringBuilder responseText = new StringBuilder();
		responseText.append("<h3>your firstName is ")
		            .append(firstName)
		            .append("\n and lastName is ")
		            .append(lastName)
		            .append("</h3>");
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print(responseText);
		out.flush();
		out.close();
	}
}






