package com.greedy.section01.view;

public class ResultView {

	public void success(int orderAmount) {
		System.out.println(orderAmount + "개의 메뉴 주문에 성공하셨습니다!");
	}
	
	public void failed() {
		System.out.println("메뉴 주문에 실패하셨습니다!");
	}
}
