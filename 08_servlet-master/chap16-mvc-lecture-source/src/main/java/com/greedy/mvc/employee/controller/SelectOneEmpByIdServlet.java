package com.greedy.mvc.employee.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greedy.mvc.employee.model.dto.EmployeeDTO;
import com.greedy.mvc.employee.model.service.EmployeeService;

@WebServlet("/employee/select")
public class SelectOneEmpByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 전달받은 파라미터 꺼내기 */
		String empId = request.getParameter("empId");
		System.out.println("넘어온 사번: " + empId);
		
		/* 입력받은 사번을 이요해 사원 정보를 조회하는 비즈니스 로직 호출 */
		EmployeeService empService = new EmployeeService();
		EmployeeDTO selectedEmp = empService.selectOneEmpById(empId);
		
		System.out.println("조회된 사원: " + selectedEmp);
		
		/* 비즈니스 로직 실행 결과에 따른 뷰 연결 */
		String path = "";
		if(selectedEmp != null) {		// 해당 사원이 조회 된 경우
			path = "/WEB-INF/views/employee/showEmpInfo.jsp";
			request.setAttribute("selectedEmp", selectedEmp);
		} else {						// 해당 사원이 조회되지 않은 경우
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("message", "사원의 정보를 조회할 수 없습니다.");
		}
				
		request.getRequestDispatcher(path).forward(request, response);
		
	}

}








