package com.greedy.section01.singleton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.greedy.section01.singleton.config.ContextConfiguration;

public class Application {

	/*
	 * 스프링 컨테이너가 관리하는 빈은 기본적으로 singleton이다.
	 * singleton은 IOC컨테이너 당 하나의 인스턴스만 bean으로 제공한다.
	 * 
	 * 스코프의 종류
	 * singleton			IOC 컨테이너당 빈 인스턴스를 하나만 생성한다.
	 * prototype			요청할 때마다 빈 인스턴스를 새로 만든다.
	 * request				HTTP 요청당 하나의 빈 인스턴스를 생성한다. 웹 애플리케이션 컨텍스트에만 해당된다.
	 * session				HTTP 세션당 빈 인스턴스를 생성한다. 웹 애플리케이션 컨텍스트에만 해당된다.
	 * globalSession	    전역 HTTP 세션당 빈 인스턴스 하나를 생성한다. 포털 애플리케이션 컨텍스트에만 해당된다.
	 */
	
	public static void main(String[] args) {
		ApplicationContext context =
				new AnnotationConfigApplicationContext(ContextConfiguration.class);
		
		String[] beanNames = context.getBeanDefinitionNames();
		for(String beanName : beanNames) {
			System.out.println("beanName: " + beanName);
		}
		
		/* 세 개의 상품 준비 */
		Product carpBread = context.getBean("carpBread", Bread.class);
		Product milk = context.getBean("milk", Beverage.class);
		Product water = context.getBean("water", Beverage.class);
		
		/* 첫 번째 손님의 쇼핑카트에 담기 */
		ShoppingCart cart1 = context.getBean("cart", ShoppingCart.class);
		cart1.addItem(carpBread);
		cart1.addItem(milk);
		
		System.out.println("cart1에 담긴 내용: " + cart1.getItem());
		
		/* 두 번째 손님의 쇼핑카트에 담기 */
		ShoppingCart cart2 = context.getBean("cart", ShoppingCart.class);
		cart2.addItem(water);
		
		System.out.println("cart2에 담긴 내용: " + cart2.getItem());
	}

}








