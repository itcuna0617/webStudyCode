package com.greedy.section03.status;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/status")
public class StatusCodeTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.sendError(404, "없는 페이지입니다. 경로를 확인해 주세요");
		response.sendError(500, "서버 내부 오류입니다. 서버 오류는 개발자의 잘못이고, 개발자는 여러분입니다.");
	
	}
}
