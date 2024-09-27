package com.greedy.section01.insert;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.greedy.model.dto.MenuDTO;

public class Application3 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.print("메뉴의 이름을 입력하세요: ");
		String menuName = sc.nextLine();
		System.out.print("메뉴의 가격을 입력하세요: ");
		int menuPrice = sc.nextInt();
		System.out.print("메뉴의 카테고리 코드를 입력하세요(4~12): ");
		int menuCategory = sc.nextInt();
		
		MenuDTO newMenu = new MenuDTO();
		newMenu.setMenuName(menuName);
		newMenu.setMenuPrice(menuPrice);
		newMenu.setCategoryCode(menuCategory);
		
		System.out.println(newMenu);
		
		/* -------------------------------------------- */
		
		Connection con = getConnection();
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
			String query = prop.getProperty("insertMenu");
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, newMenu.getMenuName());
			pstmt.setInt(2, newMenu.getMenuPrice());
			pstmt.setInt(3, newMenu.getCategoryCode());
			
			result = pstmt.executeUpdate();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(result > 0) {
				System.out.println("메뉴 등록 성공!");
			} else {
				System.out.println("메뉴 등록 실패!");
			}
			
			close(pstmt);
			close(con);
		}
	}
}






