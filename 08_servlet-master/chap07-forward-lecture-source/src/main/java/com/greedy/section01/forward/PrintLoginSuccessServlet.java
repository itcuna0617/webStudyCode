package com.greedy.section01.forward;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/print")
public class PrintLoginSuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("=== 두번째 서블릿이 넘겨받은 request에 담긴 값들 ===");
		System.out.println(request.getAttribute("userName"));
		System.out.println(request.getParameter("userId"));
		System.out.println(request.getParameter("password"));
		
		/* getAttribute는 Object를 반환하므로 다운 캐스팅 할 것 */
		String userName = (String)request.getAttribute("userName");	
		
		StringBuilder responseText = new StringBuilder();
		responseText.append("<h3 align=\"center\">\n")
		            .append(userName)
		            .append("님 환영합니다.</h3>");
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print(responseText);
		out.flush();
		out.close();
	}
}
