package com.greedy.section01.statement;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.greedy.model.dto.EmployeeDTO;

/* DB의 Employee 한 행(사원한명)의 정보를 자바의 DTO 객체에 옮겨담기 */
public class Application4 {

	public static void main(String[] args) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rset = null;
		EmployeeDTO selectedEmp = null;
		
		/* 사번을 입력 받고 query를 준비하는 단계에서는 jdbc관련 예외가 발생하지 않으므로 try구문 밖으로 뺀다. */
		Scanner sc = new Scanner(System.in);
		System.out.print("조회 하려는 사번을 입력해 주세요: ");
		String empId = sc.next();
		
		String query = "SELECT A.* FROM EMPLOYEE A WHERE EMP_ID = '" + empId + "'";
//		System.out.println(query);
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {						// 한 행이 조회가 된다면
				selectedEmp = new EmployeeDTO();	// 조회 된 한 행을 담을 EmployeeDTO 객체 생성
				
//				System.out.println(rset.getString("EMP_NAME"));
				
				/* 각 컬럼에 담긴 값을 객체의 필드에 매핑 */
				selectedEmp.setEmpId(rset.getString("EMP_ID"));
				selectedEmp.setEmpName(rset.getString("EMP_NAME"));
				selectedEmp.setEmpNo(rset.getString("EMP_NO"));
				selectedEmp.setEmail(rset.getString("EMAIL"));
				selectedEmp.setPhone(rset.getString("PHONE"));
				selectedEmp.setDeptCode(rset.getString("DEPT_CODE"));
				selectedEmp.setJobCode(rset.getString("JOB_CODE"));
				selectedEmp.setSalLevel(rset.getString("SAL_LEVEL"));
				selectedEmp.setSalary(rset.getInt("SALARY"));
				selectedEmp.setBonus(rset.getDouble("BONUS"));
				selectedEmp.setManagerId(rset.getString("MANAGER_ID"));
				selectedEmp.setHireDate(rset.getDate("HIRE_DATE"));
				selectedEmp.setEntDate(rset.getDate("ENT_DATE"));
				selectedEmp.setEntYn(rset.getString("ENT_YN"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
			close(con);
		}
		
		System.out.println(selectedEmp);
	}
}






