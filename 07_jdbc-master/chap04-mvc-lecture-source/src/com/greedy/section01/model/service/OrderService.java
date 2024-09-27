package com.greedy.section01.model.service;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.commit;
import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.greedy.section01.model.dao.OrderDAO;
import com.greedy.section01.model.dto.CategoryDTO;
import com.greedy.section01.model.dto.MenuDTO;
import com.greedy.section01.model.dto.OrderDTO;
import com.greedy.section01.model.dto.OrderMenuDTO;

/*
 * Service Layer(계층)의 역할
 * 1. Connection  생성
 * 2. DAO 계층의 메소드 호출
 * 3. 트랜젝션 제어(DML일 시, commit or rollback)
 * 4. Connection 닫기(리소스 반납)
 */
public class OrderService {
	private OrderDAO orderDAO = new OrderDAO();

//	public OrderService() {
//		System.out.println("service 탄생");
//	}

	public List<CategoryDTO> selectAllCategory() {
		Connection con = getConnection();
		
		List<CategoryDTO> categoryList = orderDAO.selectAllCategory(con);
		
		close(con);
		return categoryList;
	}

	public List<MenuDTO> selectMenuBy(int categoryCode) {
		Connection con = getConnection();
		
		List<MenuDTO> menuList = orderDAO.selectMenuBy(categoryCode, con);
		
		close(con);
		return menuList;
	}

	public int registOrder(OrderDTO order, List<OrderMenuDTO> orderMenuList) {
		
		/* 0. Controller에게 트랜잭션 성공 여부를 알려줄 변수 */
		int result = 0;		// 주문 트랜잭션이 모두 성공적으로 마무리 되면 1을 대입 받을 변수
		
		/* 1. Connection 생성 */
		Connection con = getConnection();
		
		/* 2. TBL_ORDER 테이블(부모)에 INSERT */
		int result1 = orderDAO.registOrder(order, con);
		System.out.println("order 테이블에 insert 성공 여부: " + result1);
		
		/* 3. 마지막 주문 시퀀스 SELECT */
		int orderCode = orderDAO.lastOrderCode(con);
		System.out.println("방금 추가한 주문 코드 번호: " + orderCode);
		
		/* 4. TBL_ORDER_MENU 테이블(자식)에 INSERT */
		int result2 = 0;
		
		/* 사용자가 고른 메뉴의 갯수만큼 메뉴별로 insert를 하고 결과를 누적 */
		for(int i = 0; i < orderMenuList.size(); i++) {			
			
			/* 각 메뉴 정보를 가진 orderMenuList.get(i)(== OrderMenuDTO)에 주문 코드번호를 넣어 주어야 한다. */
			orderMenuList.get(i).setOrderCode(orderCode);
			
			result2 += orderDAO.registOrderMenu(orderMenuList.get(i), con);
		}
		System.out.println("사용자가 고른 메뉴 갯수만큼 성공한 결과: " + result2);
		
		/* 5. 트랜잭션 처리 */
		if(result1 == 1 && result2 == orderMenuList.size()) { // SELECT을 제외한 DML작업들 모두 성공 시
			
			// 모두 commit
			commit(con);
			System.out.println("모든 DML 작업 성공!");
			result = 1;
		} else {
			
			// 모두 rollback
			rollback(con);
			System.out.println("DML 작업 중 하나라도 실패");
		}
		
		/* 6. Connection 반납 */
		close(con);
		
		/* 7. 결과 반환 */
		return result;
	}
}








