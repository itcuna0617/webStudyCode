package com.greedy.section02.otherservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("나 redirect야");
		String param = request.getParameter("test");
//		String reqAttr = (String)request.getAttribute("test");
		
		StringBuilder redirectText = new StringBuilder();
		redirectText.append("<!doctype html>\n")
		            .append("<html>\n")
		            .append("<head>\n")
		            .append("<body>\n")
		            .append("<h1>" + param + "이 넘어오며 redirect 성공!</h1>\n")
//		            .append("<h1>" + reqAttr + "이 넘어오며 redirect 성공!</h1>\n")
		            .append("</body>\n")
		            .append("</html>");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(redirectText);
		out.flush();
		out.close();
	}
}
