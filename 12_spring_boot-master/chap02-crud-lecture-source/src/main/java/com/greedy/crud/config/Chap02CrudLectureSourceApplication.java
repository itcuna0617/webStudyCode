package com.greedy.crud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/*
 * 추가 한 starter들
 * JDBC API
 * Oracle Driver
 * Spring Boot DevTools
 * Spring Web
 * Thymeleaf
 */
@SpringBootApplication
@ComponentScan("com.greedy.crud")		// 설정 클래스 파일을 옮기면 ComponentScan을 신경쓰자!!
public class Chap02CrudLectureSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chap02CrudLectureSourceApplication.class, args);
	}

}
