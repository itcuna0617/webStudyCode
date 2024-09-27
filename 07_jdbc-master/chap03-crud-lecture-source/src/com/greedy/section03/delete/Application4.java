package com.greedy.section03.delete;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Application4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제 할 메뉴 번호를 입력하세요: ");
		int menuCode = sc.nextInt();
		
		/* ------------------------------------------------------ */
		
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
			String query = prop.getProperty("deleteMenu");
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, menuCode);
			
			result = pstmt.executeUpdate();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			if(result > 0) {
				System.out.println("메뉴 삭제 성공!");
			} else {
				System.out.println("메뉴 삭제 실패!");
			}
			close(pstmt);
			close(con);
		}
	}
}
