package com.greedy.section01.statement;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application2 {
	public static void main(String[] args) {

		/* 1. Connection 인스턴스 생성 */
		Connection con = getConnection();
		
		/* 2. Statement 선언 */
		Statement stmt = null;
		
		/* 3. ResultSet 선언(SELECT일 경우) */
		ResultSet rset = null;
		
		try {
			/* 4. Connection의 createStatement()로 Statement 인스턴스 생성 */
			stmt = con.createStatement();
			
			/* 5. Query를 주고 executeQuery()를 통해 DB를 다녀와서 ResultSet을 반환 받음 */
			String empId = "207";
			
			/* 문자열로 쿼리를 만들 때 띄어쓰기 및 문자열은 '(홑따옴표)를 빼먹지 않도록 유의한다. */
			String query = "SELECT A.EMP_ID, A.EMP_NAME, A.SALARY FROM EMPLOYEE A "
					       + "WHERE EMP_ID = '" + empId + "'";
			
			rset = stmt.executeQuery(query);
			
			/* 6. ResultSet에 담긴 결과물을 확인하기(컬럼명, 컬럼순서, 별칭) */
			if(rset.next()) {
				System.out.println(rset.getString("EMP_ID") + ", " + rset.getString("EMP_NAME") 
				                   + ", " + rset.getInt("SALARY"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			/* 7. 사용한 자원 반납(ResultSet -> Statement -> Connection) */
			close(rset);
			close(stmt);
			close(con);
		}
	}
}
