package com.ohgiraffers.bootrestapi.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/*
 * 추가한 starter들
 * JDBC API
 * Oracle Driver
 * Spring Boot DevTools
 * Spring Web
 * Spring Security
 * Spring Data Jpa
 * 
 * pom.xml에 jwt 관련 라이브러리, modelmapper 및 파일 업로드 dependency 추가
 */
@SpringBootApplication
@ComponentScan("com.ohgiraffers.bootrestapi")
public class BootRestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootRestapiApplication.class, args);
	}

}
