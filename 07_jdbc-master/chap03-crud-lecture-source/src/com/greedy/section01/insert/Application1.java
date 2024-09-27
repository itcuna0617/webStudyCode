package com.greedy.section01.insert;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class Application1 {

	public static void main(String[] args) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;		// DML(INSERT, UPDATE, DELETE)부터는 ResultSet이 아닌 int가 돌아온다.
		                    // (DML 작업이 이루어진 행의 갯수)
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
//			System.out.println(prop);
			
			String query = prop.getProperty("insertMenu");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "초콜릿샤브샤브");
			pstmt.setInt(2, 100000);
			pstmt.setInt(3, 7);
			
			result = pstmt.executeUpdate();
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);			// connection을 닫을 때 commit이 발생함
		}

		System.out.println("result 확인: " + result);
	}
}






