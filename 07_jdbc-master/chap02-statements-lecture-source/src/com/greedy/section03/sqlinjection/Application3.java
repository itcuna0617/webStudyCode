package com.greedy.section03.sqlinjection;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application3 {

	private static String empId = "201";			                        // id의 개념으로 보자.
	private static String empName = "' OR 1=1 AND EMP_ID = '204";			// pwd의 개념으로 보자.
	
	public static void main(String[] args) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ? AND EMP_NAME = ?";

		/* Prepared Statement가 내부적으로 처리해 주는 모습(싱글쿼테이션이 있으면 싱글 쿼테이션을 더 달고 전체에 싱글쿼테이션을 달아준다. */
//		String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '201' AND EMP_NAME = ''' OR 1=1 AND EMP_ID = ''204'";
				
		System.out.println(query);
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, empId);
			pstmt.setString(2, empName);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {	// 한 행이라도 조회가 되면
				System.out.println(rset.getString("EMP_NAME") + "님 환영합니다.");
			} else {			// 한 행도 조회가 안되면
				System.out.println("해당하는 사원이 없습니다.");
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








