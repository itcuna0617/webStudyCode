package com.greedy.section03.layertest.model.dao;

import static com.greedy.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.greedy.section03.layertest.model.dto.CategoryDTO;
import com.greedy.section03.layertest.model.dto.OrderDTO;

public class OrderDAO {
	private Properties prop = new Properties();
	
	public OrderDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/order-query.xml"));
//			System.out.println(prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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

	public int registOrder(OrderDTO order, Connection con) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String query = prop.getProperty("insertOrder");
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, order.getOrderDate());
			pstmt.setString(2, order.getOrderTime());
			pstmt.setInt(3, order.getTotalOrderPrice());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
