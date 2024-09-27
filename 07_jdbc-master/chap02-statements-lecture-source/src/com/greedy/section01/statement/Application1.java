package com.greedy.section01.statement;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application1 {

	public static void main(String[] args) {
		Connection con = getConnection();
		
//		System.out.println("main에서 Connection 확인: " + con);
		Statement stmt = null;
		ResultSet rset = null;
		try {
			stmt = con.createStatement();
			
			rset = stmt.executeQuery("SELECT A.EMP_ID, A.EMP_NAME, A.EMP_NO \"주민번호\", A.BONUS FROM EMPLOYEE A"     
											+ " WHERE A.EMP_ID = '204'");
			
			/*
			 * Statement를 활용해서 쿼리로 조회한 결과인 ResultSet의 row별로 원하는 컬럼 값 추출해 보기
			 * 
			 * rset.next() 이후 rset은 한 행을 의미하며 한 행에서 원하는 값은 컬럼명, 컬럼순서, 또는 별칭을 통해
			 * 추출할 수 있다.
			 */
			if(rset.next()) {
				System.out.println(rset.getString("EMP_ID"));
				System.out.println(rset.getString(2));
				System.out.println(rset.getString("주민번호"));
				System.out.println(rset.getDouble("BONUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			/* 닫는 순서는 ResultSet -> Statement -> Connection 순이다. */
			close(rset);
			close(stmt);
			close(con);
		}
	}

}










