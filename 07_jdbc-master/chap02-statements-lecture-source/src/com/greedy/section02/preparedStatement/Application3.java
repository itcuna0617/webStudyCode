package com.greedy.section02.preparedStatement;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Application3 {

	public static void main(String[] args) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("조회 하려는 사원을 입력하시오: ");
		int empId = sc.nextInt();
		
		String query = "SELECT EMP_ID, EMP_NAME, SALARY FROM EMPLOYEE WHERE EMP_ID = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, empId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				System.out.println(rset.getString("EMP_ID") + ", " + rset.getString("EMP_NAME")
				                  + ", " + rset.getInt("SALARY"));
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
