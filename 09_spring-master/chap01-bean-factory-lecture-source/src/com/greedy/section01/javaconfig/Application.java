package com.greedy.section01.javaconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.context.ApplicationContext;

public class Application {

	/*
	 * 우선 IOC 컨테이너(혹은 Core 컨테이너)라 불리는 존재의 최상위 인터페이스인 Bean Factory를 살펴보자.
	 * Bean Factory란?
	 * 스프링 컨테이너의 최상위 컨테이너이며 ApplicationContext와 함께 스프링 컨테이너라고 한다.
	 * Bean의 생성과 설정, 관리 등의 역할을 맡고 있다.
	 */
	
	public static void main(String[] args) {
		
		/* 어노테이션 방식으로 설정된 컨테이너 생성 */
		ApplicationContext context =
				new AnnotationConfigApplicationContext(ContextConfiguration.class);
		
		String[] beanNames = context.getBeanDefinitionNames();	// 빈으로 정의되어 관리되는 객체들의 이름을 String[]로 반환
		for(String beanName : beanNames) {
			
			/*
			 * 어노테이션 방식으로 생성한 컨테이너는 기본적으로 4개의 bean과
			 * 우리가 설정값을 지정하기 위해 만든 클래스의 객체 및 설정 안에서 정의한 bean까지 관리되고 있다.
			 * (4개의 default bean + contextConfiguration + member + member2)
			 */
			System.out.println("beanName: " + beanName);
		}
		
		/* 컨테이너에서 관리되는 Bean을 확인하는 방법 3가지(외우지 말것) */
		/* 1. id(bean의 이름)로 bean 가져오기(Object형으로 반환되므로 다운캐스팅 해야한다.) */
//		MemberDTO member = (MemberDTO)context.getBean("member");
//		System.out.println(member);
//		System.out.println(System.identityHashCode(member));
//		
//		MemberDTO member2 = (MemberDTO)context.getBean("member");
//		System.out.println(System.identityHashCode(member2));
		
		/* 2. bean의 타입으로 가져오기(같은 타입으로 관리되는 bean이 하나일 때만 가능) */
//		MemberDTO member = context.getBean(MemberDTO.class);
//		System.out.println(member);
		
		/* 3. id 및 타입으로 가져오기(다운캐스팅 없이 정확히 하나의 bean을 가져오기 위함) */
//		MemberDTO member = context.getBean("member", MemberDTO.class);
//		System.out.println(member);
	}

}





