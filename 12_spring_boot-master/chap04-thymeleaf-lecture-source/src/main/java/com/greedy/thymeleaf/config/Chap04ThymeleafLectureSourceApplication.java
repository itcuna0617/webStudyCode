package com.greedy.thymeleaf.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/*
 * 추가한 starter들
 * Spring Boot DevTools
 * Spring Web
 * Thymeleaf
 */
@SpringBootApplication
@ComponentScan("com.greedy.thymeleaf")
public class Chap04ThymeleafLectureSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chap04ThymeleafLectureSourceApplication.class, args);
	}

}
