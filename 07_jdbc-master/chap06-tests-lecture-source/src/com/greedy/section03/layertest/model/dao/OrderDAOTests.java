package com.greedy.section03.layertest.model.dao;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.greedy.section03.layertest.model.dto.CategoryDTO;
import com.greedy.section03.layertest.model.dto.OrderDTO;

public class OrderDAOTests {

	private Connection con;
	private OrderDAO orderDAO;	
	private OrderDTO orderDTO;
	
	@Before
	public void setup() {
		con = getConnection();			// DAO 계층의 메소드를 단위 테스트 하기 위해 Connection 객체를 생성
		orderDAO = new OrderDAO();
		orderDTO = new OrderDTO();
		orderDTO.setOrderDate("22/11/07");
		orderDTO.setOrderTime("11:40:03");
		orderDTO.setTotalOrderPrice(30000);
		
//		assertNotNull(orderDAO);
	}
	
	/* 1. 카테고리 조회 시 카테고리가 존재하는지 테스트 */
//	@Test
	@Ignore
	public void selectAllCategory_조회_시_카테고리가_존재하는지_확인() {
		List<CategoryDTO> categoryList = orderDAO.selectAllCategory(con);
		
		assertTrue(!categoryList.isEmpty());
//		assertEquals(12, categoryList.size());			// 카테고리가 정확히 몇개 조회 되는지도 테스트는 가능하다.
	}
	
	/* 2. TBL_ORDER 테이블에 INSERT 하는 기능 동작하는지 테스트 */
	@Test
//	@Ignore
	public void registOrder_TBL_ORDER_테이블에_insert_하는_기능_동작_확인() {
		int result = orderDAO.registOrder(orderDTO, con);
		
		assertEquals(1, result);
	}
	
	/* 3. 테스트 이후 후처리 */
	@After
	public void afterTest() {
		close(con);
	}
}






