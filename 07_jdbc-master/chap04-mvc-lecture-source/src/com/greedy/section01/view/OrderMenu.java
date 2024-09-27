package com.greedy.section01.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.greedy.section01.controller.OrderController;
import com.greedy.section01.model.dto.CategoryDTO;
import com.greedy.section01.model.dto.MenuDTO;
import com.greedy.section01.model.dto.OrderMenuDTO;

public class OrderMenu {

	private OrderController orderController = new OrderController();
	
//	public OrderMenu() {
//		System.out.println("view 탄생");
//	}

	public void displayMainMenu() {
		
		Scanner sc = new Scanner(System.in);
		
		/* 메뉴를 반복 입력 받으며 누적 시킬 변수 */
		int totalOrderPrice = 0;
		List<OrderMenuDTO> orderMenuList = new ArrayList<>(); 
		
		boolean flag = true;
		do {
			System.out.println("========= 음식 주문 프로그램 ==========");
			
			List<CategoryDTO> categoryList = orderController.selectAllCategory();
			
//			System.out.println(categoryList);
			for(CategoryDTO category : categoryList) {
				System.out.println(category);
			}
			
			System.out.println("==================================");
			System.out.print("주문하실 카테고리 종류의 이름을 입력해 주세요: ");
			String inputCategory = sc.nextLine();	// 커피
			
			System.out.println("========= 주문 가능 해당 카테고리의 메뉴 ==========");
			
			/* 입력받은 값 가공 처리는 Controller에게 맡기고 가공 처리에 필요한 값을 넘긴다. */
			List<MenuDTO> menuList = orderController.selectMenuBy(inputCategory, categoryList);
			
			/* for-each */
			for(MenuDTO menu : menuList) {
				System.out.println(menu);
			}
			
			System.out.print("주문하실 메뉴를 선택해 주세요: ");
			String inputMenu = sc.nextLine();	    // 메뉴 이름 
			
			/* for */
			/* 메뉴 이름이 정해지면 주문관련에 쓰일 값들을 화면단에서는 이미 알고 있으므로 미리 추출해 둔다. */
			int menuCode = 0;
			int menuPrice = 0;
			for(int i = 0; i < menuList.size(); i++) {
				MenuDTO menu = menuList.get(i);
				if(menu.getMenuName().equals(inputMenu)) {	// 사용자가 고른 메뉴
					menuCode = menu.getMenuCode();			// 해당 메뉴의 코드 번호
					menuPrice = menu.getMenuPrice();		// 해당 메뉴의 단가
				}
			}
			
			/* 메뉴를 오타 냈을 때 이후 과정이 진행되지 않고 다시 메뉴를 입력하게 함 */
			if(menuPrice == 0) {
				System.out.println("해당 메뉴가 없어요");
				continue;
			}
			
			System.out.print("주문하실 수량을 입력하세요: ");
			int orderAmount = sc.nextInt();
			sc.nextLine();
			
			totalOrderPrice += menuPrice * orderAmount;		// 메뉴 한건 주문에 대한 합계를 전체 합계에 누적
			
			/* 아직 주문번호가 나오지는 않았지만 해당 주문 메뉴에 대한 메뉴 코드와 수량은 알 수 있다. */
			OrderMenuDTO orderMenu = new OrderMenuDTO();
			orderMenu.setMenuCode(menuCode);
			orderMenu.setOrderAmount(orderAmount);
			
			orderMenuList.add(orderMenu);					// 주문 메뉴를 List에 누적
			
			/* 메뉴를 더 선택할지 말지 결정하기 위한 코드 */
			String isContinue = "";
			while(true) {	// '예' 또는 '아니오'를 입력하지 않았을 경우 계속 주문할껀지 반복해서 또 물어보게 하는 반복문 
				System.out.print("계속 주문하시겠습니까?(예/아니오): ");
				isContinue = sc.nextLine();
				
	//			if(isContinue.equals("예")) {	// NPE(NullPointer Exception)가 발생할 수 있다.
				if("예".equals(isContinue)) {
					break;
				} else if("아니오".equals(isContinue)) {
					flag = false;
					break;
				}
			}
		} while(flag);
		
		/* 
		 * 주문 한건에 대한 전체 관련 내용(사용자가 고른 메뉴들에 대한 처리는 끝난 상태)을 Map(DTO말고)에 담아
		 * Controller로 전달
		 * 
		 * 아래 코드가 의미 하는 바
		 * 1. 현재시간은 서버의 시간 개념으로 DB에 저장하기 위해 Controller에서 현재시간을 추출해서 추가할 예정
		 * 2. 쿼리스트링의 개념을 자바의 Map으로 표현
		 */
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("totalOrderPrice", totalOrderPrice);
		requestMap.put("orderMenuList", orderMenuList);
		
		orderController.registOrder(requestMap);
	}


}
