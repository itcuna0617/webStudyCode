package com.greedy.section02.preparedStatement;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.greedy.model.dto.EmployeeDTO;

/* 다중행 조회 후 컬렉션에 저장하는 예제(Like문을 활용한 쿼리 적용해 보기) */
public class Application5 {

	public static void main(String[] args) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		EmployeeDTO row = null;
		List<EmployeeDTO> empList = new ArrayList<>();

		Scanner sc = new Scanner(System.in);
		System.out.print("조회 할 사원의 성씨를 입력하세요: ");
		String lastName = sc.next();
		
		/* 오라클에서는 LIKE문을 문자열을 이어붙이는 연산자를 활용하여 ?(placeholder)를 포함한 쿼리로 작성하게 된다. */
		String query = "SELECT * FROM EMPLOYEE WHERE EMP_NAME LIKE ? || '%'";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, lastName);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				row = new EmployeeDTO();  
				
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
				
				empList.add(row);		  
			}
			
			for(EmployeeDTO emp : empList) {
				System.out.println(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(con);
		}
	}
}















