package com.greedy.section01.javaconfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.greedy.section01.javaconfig.MemberDAOImpl;

//@Configuration
//@ComponentScan(basePackages="com.greedy.section01.javaconfig")

/* 어노테이션들을 확인해야 할 범위(패키지)가 많다면 {"패키지1", "패키지2"} 형태로 추가해 주면 된다. */
//@ComponentScan(basePackages={"com.greedy.section01.javaconfig", "abc", "ddd"} )
public class ContextConfiguration1 {
	
	/*
	 * chap02에서는 chap01때처럼 우리가 new 연산자를 써서 객체를 생성하고 bean을 설정파일에서
	 * 등록하는 것이 아닌,
	 * @ComponentScan을 활용해서 @Component 계열의 어노테이션이 달린 클래스의 객체를
	 * bean으로 자동 등록한 것이다.
	 * (단, @Component 계열(@Controller, @Service, @Repository)의 어노테이션만
	 * 달아둔다고 bean으로 등록되는 것이 아니라 설정 파일과 같은 패키지에 있거나 다른 패키지라면
	 * basePackages를 통해 어노테이션이 달린 클래스를 포함한 패키지를 등록해 주어야 한다.)
	 */
	
//	@Bean
//	public MemberDAOImpl memberDAOImpl() {
//		return new MemberDAOImpl();
//	}
}
