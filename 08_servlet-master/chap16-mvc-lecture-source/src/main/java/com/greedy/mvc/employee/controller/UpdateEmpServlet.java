package com.greedy.mvc.employee.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greedy.mvc.employee.model.dto.EmployeeDTO;
import com.greedy.mvc.employee.model.service.EmployeeService;

@WebServlet("/employee/update")
public class UpdateEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String empId = request.getParameter("empId");
		java.sql.Date entDate = java.sql.Date.valueOf(request.getParameter("entDate"));
//		System.out.println("사번 : " + empId);
//		System.out.println("퇴사일 : " + entDate);
		
		EmployeeDTO emp = new EmployeeDTO();
		emp.setEmpId(empId);
		emp.setEntDate(entDate);
		
		int result = new EmployeeService().updateEmp(emp);
		
		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/successPage.jsp";
			request.setAttribute("successCode", "updateEmp");
		} else {
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("message", "직원 정보 수정 실패!");
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
}




