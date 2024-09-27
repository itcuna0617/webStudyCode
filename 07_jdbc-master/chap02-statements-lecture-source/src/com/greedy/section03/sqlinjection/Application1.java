package com.greedy.section03.sqlinjection;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application1 {

	private static String empId = "201";			// id의 개념으로 보자.
	private static String empName = "송종기";			// pwd의 개념으로 보자.
	
	public static void main(String[] args) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '" + empId
				       + "' AND EMP_NAME = '" + empName + "'";
		
//		System.out.println(query);
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {	// 한 행이라도 조회가 되면
				System.out.println(rset.getString("EMP_NAME") + "님 환영합니다.");
			} else {			// 한 행도 조회가 안되면
				System.out.println("해당하는 사원이 없습니다.");
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








