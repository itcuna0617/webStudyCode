package com.greedy.section01.insert;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Scanner;

public class Application2 {

	public static void main(String[] args) {
		Connection con = getConnection();
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
			
//			System.out.println(prop.getProperty("insertMenu"));
			String query = prop.getProperty("insertMenu");
			
			Scanner sc = new Scanner(System.in);
			System.out.print("메뉴의 이름을 입력하세요: ");
			String menuName = sc.nextLine();
			System.out.print("메뉴의 가격을 입력하세요: ");
			int menuPrice = sc.nextInt();
			System.out.print("메뉴의 카테고리 코드를 입력하세요(4~12): ");
			int menuCategory = sc.nextInt();
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, menuName);
			pstmt.setInt(2, menuPrice);
			pstmt.setInt(3, menuCategory);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				
				// 커밋 처리
				System.out.println("메뉴 등록 성공!");
			} else {
				
				// 롤백 처리
				System.out.println("메뉴 등록 실패!");
			}
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
			close(con);
		}
	}
}
