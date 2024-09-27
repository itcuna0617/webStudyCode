package com.greedy.section01.statement;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.greedy.model.dto.EmployeeDTO;

/* DB의 Employee 다중 행(사원 여러명)의 정보를 자바의 DTO 객체들 및 컬렉션(ArrayList)에 옮겨담기 */
public class Application5 {

	public static void main(String[] args) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rset = null;
		
		/* 한 행의 정보를 담을 DTO */
		EmployeeDTO row = null;
		
		/* DTO들을 담을 컬렉션 */
		List<EmployeeDTO> empList = new ArrayList<>();		// NPE 방지를 위해 컬렉션은 일반적으로 선언과 동시에 초기화를 한다.
		
		String query = "SELECT * FROM EMPLOYEE";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				row = new EmployeeDTO();  // 매번 새로운 행의 정보를 담을 인스턴스를 새로 생성
				
//				System.out.println(rset.getString("EMP_NAME"));
				
				row.setEmpId(rset.getString("EMP_ID"));
				row.setEmpName(rset.getString("EMP_NAME"));
				row.setEmpNo(rset.getString("EMP_NO"));
				row.setEmail(rset.getString("EMAIL"));
				row.setPhone(rset.getString("PHONE"));
				row.setDeptCode(rset.getString("DEPT_CODE"));
				row.setJobCode(rset.getString("JOB_CODE"));
				row.setSalLevel(rset.getString("SAL_LEVEL"));
				row.setSalary(rset.getInt("SALARY"));
				row.setBonus(rset.getDouble("BONUS"));
				row.setManagerId(rset.getString("MANAGER_ID"));
				row.setHireDate(rset.getDate("HIRE_DATE"));
				row.setEntDate(rset.getDate("ENT_DATE"));
				row.setEntYn(rset.getString("ENT_YN"));
				
//				System.out.println(row);
				
				empList.add(row);		  // 한 행의 정보를 담고 있는 인스턴스를 컬렉션에 추가(누적)
			}
			
//			System.out.println(empList);
			for(EmployeeDTO emp : empList) {
				System.out.println(emp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
			close(con);
		}
	}
}







