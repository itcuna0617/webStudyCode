package com.greedy.session.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/*
 * 추가한 starter들
 * JDBC API
 * Oracle Driver
 * Spring Boot DevTools
 * Spring Web
 * Thymeleaf
 * Spring Security
 */
@SpringBootApplication
@ComponentScan("com.greedy.session")
public class Chap03SecuritySessionLectureSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chap03SecuritySessionLectureSourceApplication.class, args);
	}

}
