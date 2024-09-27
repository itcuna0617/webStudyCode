package com.greedy.section01.fieldInjection;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	public static void main(String[] args) {
		
		/* @Configuration이 달린 설정 파일을 따로 만들지 않고 @ComponentScan의 범위 개념만 문자열로 넘겨줄 수도 있다. */
		ApplicationContext context =
				new AnnotationConfigApplicationContext("com.greedy.section01.fieldInjection");
		
//		String[] beanNames = context.getBeanDefinitionNames();
//		for(String beanName: beanNames) {
//			System.out.println(beanName);
//		}
		
		BookService bookService = context.getBean("bookService", BookService.class);
		
		/* 도서 전체 조회 */
		List<BookDTO> bookList = bookService.selectBookList();
//		for(BookDTO book : bookList) {
//			System.out.println(book);
//		}
		
		/* 2번 도서 조회 */
		BookDTO book = bookService.selectOneBook(2);
		System.out.println(book);
	}
}






