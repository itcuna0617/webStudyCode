package com.greedy.mvc.employee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greedy.mvc.employee.model.dto.EmployeeDTO;
import com.greedy.mvc.employee.model.service.EmployeeService;

@WebServlet("/employee/list")
public class SelectAllEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EmployeeService empService = new EmployeeService();
		List<EmployeeDTO> empList = empService.selectAllEmpList();
		
		for(EmployeeDTO emp : empList) {
			System.out.println(emp);
		}
		
		String path="";
		if (!empList.isEmpty()) {
			path="/WEB-INF/views/employee/employeeList.jsp";
			request.setAttribute("empList",  empList);
		} else {
			path="/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("message", "직원 목록 조회 실패!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}






