package com.greedy.section01.javaconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.greedy.section01.javaconfig.config.ContextConfiguration1;

public class Application1 {

	public static void main(String[] args) {
		ApplicationContext context =
				new AnnotationConfigApplicationContext(ContextConfiguration1.class);
		
		String[] beanNames = context.getBeanDefinitionNames();
		for(String beanName : beanNames) {
			System.out.println("beanName: " + beanName);
		}
		
		MemberDAO memberDAO = context.getBean(MemberDAO.class);	// MemberDAO타입의 bean이 있다면 꺼낸다.
		System.out.println(memberDAO.selecteMember(2));
		
		MemberDTO newMember = new MemberDTO();
		newMember.setId("user03");
		newMember.setPwd("pass03");
		newMember.setName("새로운 멤버");
		System.out.println("회원 추가 성공 여부: " + memberDAO.insertMember(newMember));
		
		MemberDAO memberDAO2 = context.getBean(MemberDAO.class);
		newMember = new MemberDTO();
		newMember.setId("user04");
		newMember.setPwd("pass04");
		System.out.println("한번 더!!: " + memberDAO2.insertMember(newMember));
		
		System.out.println(memberDAO2.selecteMember(3));
		System.out.println(memberDAO2.selecteMember(4));
	}

}






