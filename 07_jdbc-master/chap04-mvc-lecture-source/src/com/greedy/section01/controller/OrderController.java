package com.greedy.section01.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.greedy.section01.model.dto.CategoryDTO;
import com.greedy.section01.model.dto.MenuDTO;
import com.greedy.section01.model.dto.OrderDTO;
import com.greedy.section01.model.dto.OrderMenuDTO;
import com.greedy.section01.model.service.OrderService;
import com.greedy.section01.view.ResultView;

/*
 * Controller Layer(계층)의 역할
 * 1. 사용자가 입력한 정보를 파라미터 형태로 전달받아 전달받은 값을 검증하거나 가공처리
 * 2. Service 계층의 메소드 호출
 * 3. 수행 결과를 반환 받아 사용자에게 보여줄 뷰 결정
 * 4. 뷰에 필요한 데이터가 있다면 데이터를 전달
 */
public class OrderController {
	private OrderService orderService = new OrderService();
	private ResultView resultView = new ResultView();
	
//	public OrderController() {
//		System.out.println("controller 탄생");
//	}

	public List<CategoryDTO> selectAllCategory() {
		
		List<CategoryDTO> categoryList = orderService.selectAllCategory();
		
//		if(!categoryList.isEmpty()) {
//			System.out.println("조회 된게 있음");
//		} else {
//			System.out.println("텅!!");
//		}
		
		return categoryList;
	}

	public List<MenuDTO> selectMenuBy(String inputCategory, List<CategoryDTO> categoryList) {	// 입력받은 카테고리 이름, 사용자가 본 카테고리들 
		
		/* 사용자가 입력한 이름에 맞는 카테고리 코드가 담길 변수 */
		int categoryCode = 0;
		
		for(CategoryDTO category : categoryList) {		            // DB에서 조회 된 카테고리를 모두 확인
			if(category.getCategoryName().equals(inputCategory)) {	// 사용자가 입력한 카테고리 이름과 일치하면
				categoryCode = category.getCategoryCode();			// 해당 카테고리의 코드 번호를 변수에 대입   
			}
		}
		
		return orderService.selectMenuBy(categoryCode);
	}

	public void registOrder(Map<String, Object> requestMap) {
		
		/* 1. 뷰에서 전달 받은 파라미터를 꺼내서 각각의 변수에 담기 */
		int totalOrderPrice = (Integer)requestMap.get("totalOrderPrice");
		List<OrderMenuDTO> orderMenuList = (List<OrderMenuDTO>)requestMap.get("orderMenuList");
		
		/* 2. 화면단에서 미처 제공하지 못했거나 가공처리해야 할 것들을 가공처리하자.(query에 사용하기 위한 값으로) */
		/* 2-1. 주문 날짜와 시간을 Controllder(서버의 시간)에서 구하기 */
		java.util.Date orderTime = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String date = dateFormat.format(orderTime);
		String time = timeFormat.format(orderTime);
		
//		System.out.println(date);
//		System.out.println(time);
		
		/* 2-2. 서비스 쩍으로 전달하기 위해 DTO 인스턴스로 만들기(묶기) */
		OrderDTO order = new OrderDTO();
		order.setOrderDate(date);
		order.setOrderTime(time);
		order.setTotalOrderPrice(totalOrderPrice);

		/* 3. Service 메소드를 호출하고 결과를 리턴받음 */
		int result = orderService.registOrder(order, orderMenuList);
		
		if(result > 0) {
			resultView.success(orderMenuList.size());
		} else {
			resultView.failed();
		}
	}
}




