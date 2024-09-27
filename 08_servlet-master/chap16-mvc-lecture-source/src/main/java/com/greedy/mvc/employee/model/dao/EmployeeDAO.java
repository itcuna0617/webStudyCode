package com.greedy.mvc.employee.model.dao;

import static com.greedy.mvc.common.jdbc.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.greedy.mvc.common.config.ConfigLocation;
import com.greedy.mvc.employee.model.dto.EmployeeDTO;

public class EmployeeDAO {
	
	private Properties prop;
	
	public EmployeeDAO() {
		prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "employee-mapper.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(prop);
	}

	public EmployeeDTO selectOneEmpById(String empId, Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		EmployeeDTO selectedEmp = null;
		
		String query = prop.getProperty("selectedEmpById");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, empId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				selectedEmp = new EmployeeDTO();
				selectedEmp.setEmpId(rset.getString("EMP_ID"));
				selectedEmp.setEmpName(rset.getString("EMP_NAME"));
				selectedEmp.setDeptCode(rset.getString("DEPT_CODE"));
				selectedEmp.setJobCode(rset.getString("JOB_CODE"));
				selectedEmp.setSalary(rset.getInt("SALARY"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return selectedEmp;
	}
	
	public List<EmployeeDTO> selectAllEmpList(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		List<EmployeeDTO> empList = null;
		
		String query = prop.getProperty("selectAllEmpList");
		
//		System.out.println("사원 전체 조회 쿼리: " + query);
		
		try {
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(query);
			
			empList = new ArrayList<>();
			
			while(rset.next()) {
				EmployeeDTO emp = new EmployeeDTO();
				emp.setEmpId(rset.getString("EMP_ID"));
				emp.setEmpName(rset.getString("EMP_NAME"));
				emp.setEmail(rset.getString("EMAIL"));
				emp.setEmpNo(rset.getString("EMP_NO"));
				emp.setPhone(rset.getString("PHONE"));
				emp.setDeptCode(rset.getString("DEPT_CODE"));
				emp.setJobCode(rset.getString("JOB_CODE"));
				emp.setSalLevel(rset.getString("SAL_LEVEL"));
				emp.setSalary(rset.getInt("SALARY"));
				emp.setBonus(rset.getDouble("BONUS"));
				emp.setManagerId(rset.getString("MANAGER_ID"));
				emp.setHireDate(rset.getDate("HIRE_DATE"));
				emp.setEntDate(rset.getDate("ENT_DATE"));
				emp.setEntYn(rset.getString("ENT_YN"));
				
				empList.add(emp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return empList;
	}

	public int insertEmp(Connection con, EmployeeDTO emp) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertEmp");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getEmpNo());
			pstmt.setString(3, emp.getEmail());
			pstmt.setString(4, emp.getPhone());
			pstmt.setString(5, emp.getDeptCode());
			pstmt.setString(6, emp.getJobCode());
			pstmt.setString(7, emp.getSalLevel());
			pstmt.setInt(8, emp.getSalary());
			pstmt.setDouble(9, emp.getBonus());
			pstmt.setString(10, emp.getManagerId());
			pstmt.setDate(11, emp.getHireDate());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateEmp(Connection con, EmployeeDTO emp) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateEmp");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setDate(1, emp.getEntDate());
			pstmt.setString(2, emp.getEmpId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteEmp(Connection con, String empId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteEmp");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, empId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}




