package com.greedy.section01.javaconfig.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.greedy.section01.javaconfig.MemberDAO;

//@Configuration
//@ComponentScan(basePackages="com.greedy.section01.javaconfig",
//				excludeFilters= {
//						
//						/* 어노테이션을 인지하지 않게 하기 위한 필터링 조건을 작성하는 부분(Component계열의 어노테이션이 달리면 인지하지 않게 함) */
//						@ComponentScan.Filter(
//								type=FilterType.ASSIGNABLE_TYPE,
//								classes= {MemberDAO.class}
//						)
//				})
public class ContextConfiguration2 {

}
