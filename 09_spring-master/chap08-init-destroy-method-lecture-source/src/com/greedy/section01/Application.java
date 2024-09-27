package com.greedy.section01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.greedy.section01.config.ContextConfiguration;

public class Application {

	public static void main(String[] args) {
		ApplicationContext context =
				new AnnotationConfigApplicationContext(ContextConfiguration.class);
		
//		String[] beanNames = context.getBeanDefinitionNames();
//		for(String beanName : beanNames) {
//			System.out.println("beanName: " + beanName);
//		}
		
		Owner owner = context.getBean("owner", Owner.class);
		
		/* 가게 문 오픈 */
//		owner.openShop();
		
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
		
		/* 가게 문 닫기 */
//		owner.closeShop();
		
		/* IOC 컨테이너 종료(컨테이너가 관리하는 빈들도 소멸) */
		((AnnotationConfigApplicationContext)context).close();
	}

}
