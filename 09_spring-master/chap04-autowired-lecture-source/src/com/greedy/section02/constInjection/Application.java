package com.greedy.section02.constInjection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.greedy.section02.constInjection.config.ContextConfiguration;

public class Application {

	public static void main(String[] args) {
		ApplicationContext context =
				new AnnotationConfigApplicationContext(ContextConfiguration.class);
		
		String[] beanNames = context.getBeanDefinitionNames();
		for(String beanName: beanNames) {
			System.out.println(beanName);
		}
		
		MakeRandomString randomString = context.getBean(MakeRandomString.class);
		System.out.println(randomString.getRandomLengthString());
		System.out.println(randomString.getRandomLengthString());
		System.out.println(randomString.getRandomLengthString());
		System.out.println(randomString.getRandomLengthString());
		System.out.println(randomString.getRandomLengthString());
		System.out.println(randomString.getRandomLengthString());
		
	}

}







