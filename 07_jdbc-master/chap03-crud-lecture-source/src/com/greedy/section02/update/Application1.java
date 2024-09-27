package com.greedy.section02.update;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.greedy.model.dto.MenuDTO;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

public class Application1 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("변경 할 메뉴 번호를 입력하세요: ");
		int menuCode = sc.nextInt();
		System.out.print("변경 할 메뉴 이름을 입력하세요: ");
		sc.nextLine();                  // 버퍼에 남은 엔터 제거용 코드
		String menuName = sc.nextLine();
		System.out.print("변경 할 메뉴의 가격을 입력하세요: ");
		int menuPrice = sc.nextInt();
		System.out.print("변경 할 메뉴의 카테고리를 입력하세요(4~12): ");
		int categoryCode = sc.nextInt();
		System.out.print("메뉴 판매 여부를 입력하세요(Y/N): ");
		sc.nextLine();					// 버퍼에 남은 엔터 제거용 코드
		String orderableStatus = sc.nextLine();
		
		MenuDTO changedMenu = new MenuDTO();
		changedMenu.setMenuCode(menuCode);
		changedMenu.setMenuName(menuName);
		changedMenu.setMenuPrice(menuPrice);
		changedMenu.setCategoryCode(categoryCode);
		changedMenu.setOrderableStatus(orderableStatus);
		
		/* -------------------------------------------------------- */
		
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
			String query = prop.getProperty("updateMenu");
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, changedMenu.getMenuName());
			pstmt.setInt(2, changedMenu.getMenuPrice());
			pstmt.setInt(3, changedMenu.getCategoryCode());
			pstmt.setString(4, changedMenu.getOrderableStatus());
			pstmt.setInt(5, changedMenu.getMenuCode());
			
			result = pstmt.executeUpdate();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(result > 0) {
				System.out.println("메뉴 수정 성공!");
			} else {
				System.out.println("메뉴 수정 실패!");
			}
			
			close(pstmt);
			close(con);
		}
	}
}





