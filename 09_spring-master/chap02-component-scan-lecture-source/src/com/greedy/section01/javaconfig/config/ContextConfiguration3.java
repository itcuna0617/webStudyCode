package com.greedy.section01.javaconfig.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.greedy.section01.javaconfig.MemberDAO;

@Configuration
@ComponentScan(basePackages="com.greedy.section01.javaconfig",
				useDefaultFilters=false,
				includeFilters= {
						@ComponentScan.Filter(
								type=FilterType.ASSIGNABLE_TYPE,
								classes= {MemberDAO.class}
						)
				})
public class ContextConfiguration3 {

}
