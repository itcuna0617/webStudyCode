package com.greedy.section01.singleton.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.greedy.section01.singleton.Beverage;
import com.greedy.section01.singleton.Bread;
import com.greedy.section01.singleton.Product;
import com.greedy.section01.singleton.ShoppingCart;

@Configuration
public class ContextConfiguration {
	@Bean
	public Product carpBread() {
		return new Bread("붕어빵", 1000, new java.util.Date());
	}
	
	@Bean
	public Product milk() {
		return new Beverage("딸기우유", 1500, 500);
	}
	
	@Bean
	public Product water() {
		return new Beverage("지리산암반수", 3000, 1000);
	}
	
	@Bean
//	@Scope("prototype")		// import는 springframework 패키지로 할 것, 해당 빈이 사용될 때마다 새로운 객체
	@Scope("singleton")
	public ShoppingCart cart() {
		return new ShoppingCart();
	}
}









