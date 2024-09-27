package com.greedy.crud.menu.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.greedy.crud.menu.model.dto.CategoryDTO;
import com.greedy.crud.menu.model.dto.MenuDTO;

@Repository
public class MenuDAO {
	private JdbcTemplate jdbcTemplate;
	private Properties prop;
	
	/* JdbcTemplate은 JDBC API에서 제공하는 bean으로 생성자 주입을 통해 주입 받는다. */
	@Autowired
	public MenuDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		
		/* MenuDAO Bean이 생성될 때 쿼리를 Prop으로 불러 온다. */
		prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("C:\\springbootWorkspace\\chap02-crud-lecture-source\\src\\main\\resources\\mappers\\menu-mapper.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<MenuDTO> findAllMenu(){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<MenuDTO> menuList = new ArrayList<>(); 	
		
		String query = prop.getProperty("findAllMenu");
		
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();	// jdbcTemplate 빈으로부터 Connection객체 전달받음
			pstmt = con.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MenuDTO menu = new MenuDTO();
				menu.setCode(rset.getInt("MENU_CODE"));
				menu.setName(rset.getString("MENU_NAME"));
				menu.setPrice(rset.getInt("MENU_PRICE"));
				menu.setCategoryCode(rset.getInt("CATEGORY_CODE"));
				menu.setOrderableStatus(rset.getString("ORDERABLE_STATUS"));
				
				menuList.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset!=null && pstmt!=null) {
					rset.close();
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return menuList;
	};

	public List<CategoryDTO> findAllCategory(){
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<CategoryDTO> categoryList = new ArrayList<>(); 	// NPE 방지 코드
		
		String query = prop.getProperty("findAllCategory");
		
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			pstmt = con.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				CategoryDTO category = new CategoryDTO();
				category.setCode(rset.getInt("CATEGORY_CODE"));
				category.setName(rset.getString("CATEGORY_NAME"));
				category.setRefCategoryCode(rset.getInt("REF_CATEGORY_CODE"));
				
				categoryList.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset!=null && pstmt!=null) {
					rset.close();
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return categoryList;
	};

	public int registNewMenu(MenuDTO newMenu) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("registNewMenu");
		
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, newMenu.getName());
			pstmt.setInt(2, newMenu.getPrice());
			pstmt.setInt(3, newMenu.getCategoryCode());
			pstmt.setString(4, newMenu.getOrderableStatus());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	};
}
