package com.greedy.section01.statement;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Application3 {

	public static void main(String[] args) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			stmt = con.createStatement();
			
			Scanner sc = new Scanner(System.in);
			System.out.print("확인하고 싶은 사원 번호를 입력하시오: ");
			String empId = sc.next();
			
			String query = "SELECT A.EMP_ID, A.EMP_NAME, A.SALARY FROM EMPLOYEE A "
						   + "WHERE A.EMP_ID = '" + empId + "'";
		
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				
				System.out.println(rset.getString(1) + ", " + rset.getString(2) 
				                   + ", " + rset.getInt(3));
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
