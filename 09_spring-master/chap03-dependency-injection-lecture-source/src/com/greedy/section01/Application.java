package com.greedy.section01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.greedy.section01.config.ContextConfiguration;

public class Application {

	public static void main(String[] args) {
		
		/* 한명의 회원이 가진 하나의 계좌와 관련된 예제 */
		
		ApplicationContext context =
				new AnnotationConfigApplicationContext(ContextConfiguration.class);
		
//		String[] beanNames = context.getBeanDefinitionNames();
//		for(String beanName: beanNames) {
//			System.out.println(beanName);
//		}
		
		Account account = context.getBean(Account.class);
		MemberDTO member = context.getBean(MemberDTO.class);
		System.out.println("계좌 bean객체: " + account);
		System.out.println("회원 bean객체: " + member);
		
		/* 스프링이 준비한 bean을 활용해서 핵심 비즈니스 로직에만 집중해서 활용할 수 있다.(객체 지향적으로) */
		System.out.println(member.getPersonalAccount().getBalance());
		System.out.println(member.getPersonalAccount().deposit(10));
		System.out.println(member.getPersonalAccount().getBalance());
		System.out.println(member.getPersonalAccount().withDraw(5));
		System.out.println(member.getPersonalAccount().getBalance());
	}

}






