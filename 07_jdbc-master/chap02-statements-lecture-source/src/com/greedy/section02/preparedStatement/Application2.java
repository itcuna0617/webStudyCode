package com.greedy.section02.preparedStatement;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Application2 {

	public static void main(String[] args) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String empId = "208";
//		int empId = 208;
		try {
			pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME, SALARY FROM EMPLOYEE WHERE EMP_ID = ?");
			pstmt.setString(1, empId);
//			pstmt.setInt(1, empId);
			
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
