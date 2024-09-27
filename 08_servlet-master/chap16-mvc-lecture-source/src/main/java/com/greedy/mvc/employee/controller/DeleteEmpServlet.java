package com.greedy.mvc.employee.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greedy.mvc.employee.model.service.EmployeeService;

@WebServlet("/employee/delete")
public class DeleteEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String empId = request.getParameter("empId");
		
		int result = new EmployeeService().deleteEmp(empId);
		
		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/successPage.jsp";
			request.setAttribute("successCode", "deleteEmp");
		} else {
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("message", "직원 정보 삭제 실패!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}
