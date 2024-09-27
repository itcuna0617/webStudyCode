package com.greedy.section01.primary;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	public static void main(String[] args) {
		ApplicationContext context =
				new AnnotationConfigApplicationContext("com.greedy.section01.primary");
		
		PokemonService ps = context.getBean(PokemonService.class);
		ps.attack();
		
	}

}
