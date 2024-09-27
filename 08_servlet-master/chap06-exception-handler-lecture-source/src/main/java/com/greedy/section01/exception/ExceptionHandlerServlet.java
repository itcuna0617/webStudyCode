package com.greedy.section01.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.core.ApplicationMappingImpl;

@WebServlet("/showErrorPage")
public class ExceptionHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("에러 발생하면 여기로 오나");
		
		Enumeration<String> attrName = request.getAttributeNames();
		while(attrName.hasMoreElements()) {
			System.out.println(attrName.nextElement());
		}
		
//		System.out.println(request.getAttribute("javax.servlet.error.status_code"));
//		System.out.println(request.getAttribute("javax.servlet.error.message"));
//		System.out.println(request.getAttribute("javax.servlet.error.servlet_name"));
		
		int statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
		String message = (String)request.getAttribute("javax.servlet.error.message");
		String servletName = (String)request.getAttribute("javax.servlet.error.servlet_name");
		
		StringBuilder errorPage = new StringBuilder();
		errorPage.append("<!doctype html>\n")
		         .append("<html>\n")
		         .append("<head>\n")
		         .append("</head>\n")
		         .append("<body>")
		         .append("<h1 align=\"center\">")
		         .append(statusCode)
		         .append("-")
		         .append(message)
		         .append("<br>")
		         .append("<p>에러 발생한 서블릿명: ")
		         .append(servletName)
		         .append("</p>")
		         .append("</h1>\n")
		         .append("</body>\n")
		         .append("</html>\n");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print(errorPage);
		out.flush();
		out.close();
	}
}
