package com.greedy.mvc.employee.model.service;

import static com.greedy.mvc.common.jdbc.JDBCTemplate.close;
import static com.greedy.mvc.common.jdbc.JDBCTemplate.getConnection;
import static com.greedy.mvc.common.jdbc.JDBCTemplate.commit;
import static com.greedy.mvc.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.greedy.mvc.employee.model.dao.EmployeeDAO;
import com.greedy.mvc.employee.model.dto.EmployeeDTO;

public class EmployeeService {

	private EmployeeDAO empDAO;
	
	public EmployeeService() {
		empDAO = new EmployeeDAO();
	}
	
	public EmployeeDTO selectOneEmpById(String empId) {
		Connection con = getConnection();
		System.out.println(con);
		
		EmployeeDTO selectedEmp = empDAO.selectOneEmpById(empId, con);
		
		close(con);
		
		return selectedEmp;
	}
	
	public List<EmployeeDTO> selectAllEmpList() {
		Connection con = getConnection();
		
		List<EmployeeDTO> empList = empDAO.selectAllEmpList(con);
		
		close(con);
		
		return empList;
	}

	public int insertEmp(EmployeeDTO emp) {
		Connection con = getConnection();
		
		int result = empDAO.insertEmp(con, emp);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

	public int updateEmp(EmployeeDTO emp) {
		Connection con = getConnection();
		
		int result = empDAO.updateEmp(con, emp);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

	public int deleteEmp(String empId) {
		Connection con = getConnection();
		
		int result = empDAO.deleteEmp(con, empId);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

}
