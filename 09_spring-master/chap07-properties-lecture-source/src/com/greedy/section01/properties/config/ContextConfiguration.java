package com.greedy.section01.properties.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.greedy.section01.properties.Beverage;
import com.greedy.section01.properties.Bread;
import com.greedy.section01.properties.Product;
import com.greedy.section01.properties.ShoppingCart;

@Configuration
@PropertySource("product-info.properties")
public class ContextConfiguration {
	
	/* 필드에서 외부 리소스 파일의 값 주입 받기 */
	@Value("${bread.carpbread.name}")
	private String carpBreadName;
	
	@Value("${bread.carpbread.price}")
	private int carpBreadPrice;
	
	@Value("${beverage.milk.name}")
	private String milkName;
	
	@Value("${beverage.milk.price}")
	private int milkPrice;
	
	@Value("${beverage.water.name}")
	private String waterName;
	
	@Value("${beverage.water.price}")
	private int waterPrice;
	
	@Value("${beverage.water.capacity}")
	private int waterCapacity;
	
	@Bean
	public Product carpBread() {
		return new Bread(carpBreadName, carpBreadPrice, new java.util.Date());
	}
	
	/* 빈을 만드는 메소드의 매개변수에서 외부 리소스 파일의 값 주입 받기 */
	@Bean
	public Product milk(@Value("${beverage.milk.capacity}") int milkCapacity) {
		return new Beverage(milkName, milkPrice, milkCapacity);
	}
	
	@Bean
	public Product water() {
		return new Beverage(waterName, waterPrice, waterCapacity);
	}
	
	@Bean
//	@Scope("prototype")		// import는 springframework 패키지로 할 것, 해당 빈이 사용될 때마다 새로운 객체
	@Scope("singleton")
	public ShoppingCart cart() {
		return new ShoppingCart();
	}
}









