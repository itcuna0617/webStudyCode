package com.greedy.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application1 {

	public static void main(String[] args) {
		
		/* DB 접속을 위한 Connection 인스턴스 생성을 위한 레퍼런스 변수 선언(finally에서도 써야 하므로 try문 밖에서 선언) */
		Connection con = null;
		try {
			
			/* 사용 할 DBMS의 종류에 맞는 드라이버 등록(DBMS 인지) */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			/* DriverManager의 getConnection이라는 메소드로 Connection 인스턴스 생성 */
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
														"C##EMPLOYEE", "EMPLOYEE");	// 아이디와 비번은 대소문자 정확하게 쓸 것
			
			/* 해당 DBMS의 해당 계정으로 통하는 Connection 인스턴스 생성 여부 확인(라이브러리 등록도 확인 됨) */
			System.out.println(con);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
