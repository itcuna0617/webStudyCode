package com.greedy.section01.run;

import com.greedy.section01.view.OrderMenu;

public class Application {

	public static void main(String[] args) {
		OrderMenu orderMenu = new OrderMenu();	// OrderMenu부터 OrderDAO까지 MVC관련 계층 클래스 인스턴스가 모두 생성 됨
		orderMenu.displayMainMenu();
	}
}
