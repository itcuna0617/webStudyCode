package com.greedy.section02.template;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/* JDBC와 관련된 기능을 모듈화 한 클래스(따로 빼서 응집 시킨 것) */
public class JDBCTemplate {
	
	/* Connection 인스턴스를 반환하는 기능 */
	public static Connection getConnection() {
		Properties prop = new Properties();
		
		Connection con = null;
		try {
			prop.load(new FileReader("config/jdbc-config.properties"));
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			
			Class.forName(driver);
			
			/* DB설정 정보에서 사용자의 아이디와 패스워드에 해당하는 키 값을 user와 password라고 쓰면 각각 꺼내서 넘겨줄 필요가 없다. */
			con = DriverManager.getConnection(url, prop);	
			
			System.out.println("con: " + con);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}

	/* Connection 인스턴스를 닫는 기능 */
	public static void close(Connection con) {
		try {
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
