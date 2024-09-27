package com.greedy.section01.javaconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.greedy.section01.javaconfig.config.ContextConfiguration3;

public class Application3 {

	public static void main(String[] args) {
		ApplicationContext context =
				new AnnotationConfigApplicationContext(ContextConfiguration3.class);
		
		String[] beanNames = context.getBeanDefinitionNames();
		for(String beanName : beanNames) {
			System.out.println("beanName: " + beanName);
		}
		
		MemberDAO memberDAO = context.getBean(MemberDAO.class);
		System.out.println(memberDAO.selecteMember(2));
		System.out.println(memberDAO.insertMember(new MemberDTO(3, "user03", "pass03", "건물주")));
		System.out.println(memberDAO.selecteMember(3));

	}

}
