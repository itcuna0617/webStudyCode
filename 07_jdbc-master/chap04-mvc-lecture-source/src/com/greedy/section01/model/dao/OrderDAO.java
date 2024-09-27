package com.greedy.section01.model.dao;

import static com.greedy.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import com.greedy.section01.model.dto.CategoryDTO;
import com.greedy.section01.model.dto.MenuDTO;
import com.greedy.section01.model.dto.OrderDTO;
import com.greedy.section01.model.dto.OrderMenuDTO;

/*
 * DAO(Database Access Object): 데이터베이스 접근용 객체
 * => CRUD를 담당하는 메소드들의 집합으로 이루어진 클래스이다.
 * 
 * DAO Layer(계층)의 역할
 * 1. Statement 및 PreparedStatement 생성
 * 2. Query와 Statement계열의 인스턴스를 통한 CRUD
 * 3. ResultSet 또는 int 값을 통한 DBMS로부터의 결과 도출(ResultSet일 시에는 DB의 한 행을 자바의 인스턴스로 변환)
 * 4. Statement계열 및 ResultSet 닫기
 */
public class OrderDAO {
	
//	public OrderDAO() {
//		System.out.println("DAO 탄생");
//	}

	public List<CategoryDTO> selectAllCategory(Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		CategoryDTO category = null;
		List<CategoryDTO> categoryList = new ArrayList<>();
		
		String query = "SELECT \r\n"
				+ "       A.CATEGORY_CODE\r\n"
				+ "     , A.CATEGORY_NAME\r\n"
				+ "     , A.REF_CATEGORY_CODE\r\n"
				+ "FROM TBL_CATEGORY A";
		
		try {
			pstmt = con.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
//				System.out.println(rset.getString("CATEGORY_NAME"));
				category = new CategoryDTO();
				category.setCategoryCode(rset.getInt("CATEGORY_CODE"));
				category.setCategoryName(rset.getString("CATEGORY_NAME"));
				category.setRefCategoryCode(rset.getInt("REF_CATEGORY_CODE"));
				
				categoryList.add(category);
			};
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return categoryList;
	}

	public List<MenuDTO> selectMenuBy(int categoryCode, Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		MenuDTO menu = null;
		List<MenuDTO> menuList = new ArrayList<>();
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/order-query.xml"));
			
			String query = prop.getProperty("selectMenuByCategory");
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, categoryCode);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				menu = new MenuDTO();
				menu.setMenuCode(rset.getInt("MENU_CODE"));
				menu.setMenuName(rset.getString("MENU_NAME"));
				menu.setMenuPrice(rset.getInt("MENU_PRICE"));
				menu.setCategoryCode(rset.getInt("CATEGORY_CODE"));
				menu.setOrderableStatus(rset.getString("ORDERABLE_STATUS"));
				
				menuList.add(menu);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return menuList;
	}

	public int registOrder(OrderDTO order, Connection con) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		try {
			prop.loadFromXML(new FileInputStream("mapper/order-query.xml"));
			String query = prop.getProperty("insertOrder");
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, order.getOrderDate());
			pstmt.setString(2, order.getOrderTime());
			pstmt.setInt(3, order.getTotalOrderPrice());
			
			result = pstmt.executeUpdate();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int lastOrderCode(Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int lastOrderCode = 0;
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/order-query.xml"));
			String query = prop.getProperty("selectLastOrderCode");
			
			pstmt = con.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				lastOrderCode = rset.getInt("CURRVAL");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return lastOrderCode;
	}

	public int registOrderMenu(OrderMenuDTO orderMenuDTO, Connection con) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		try {
			prop.loadFromXML(new FileInputStream("mapper/order-query.xml"));
			String query = prop.getProperty("insertOrderMenu");
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, orderMenuDTO.getOrderCode());
			pstmt.setInt(2, orderMenuDTO.getMenuCode());
			pstmt.setInt(3, orderMenuDTO.getOrderAmount());
			
			result = pstmt.executeUpdate();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}




