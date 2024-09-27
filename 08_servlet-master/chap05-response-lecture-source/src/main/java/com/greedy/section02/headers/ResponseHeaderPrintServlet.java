package com.greedy.section02.headers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/header")
public class ResponseHeaderPrintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		long currentTime = System.currentTimeMillis();	// 서블릿 인스턴스가 돌아가는 서버측 현재 시간
		
		out.print("<h1>" + currentTime + "</h1>");
		out.print("<h1>" + new java.util.Date(currentTime) + "</h1>");
		out.flush();
		out.close();
		
		Collection<String> responseHeaders = response.getHeaderNames();
		Iterator<String> iter = responseHeaders.iterator();
		while(iter.hasNext()) {
			String headerName = iter.next();
			System.out.println(headerName + ": " + response.getHeader(headerName));
		}
	}
}









