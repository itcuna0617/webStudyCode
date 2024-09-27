package com.greedy.jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * 1. starter 프로젝트 만들 때 체크한 것들
 * JDBC API
 * Oracle Driver
 * Spring Boot DevTools
 * Spring Web
 * 
 * 2. JSP나 HTML 생성이 기본적으로 지원되지 않으므로 Marketplace에서 Web Developer 플러그인
 *    두번째 것 다운 받기(인코딩 설정 바꿀 것 -> UTF-8)
 *    
 * 3. application.properties 수정(DB 연결정보(log 적용 가능한) properties 추가)
 * 
 * 4. pom.xml 수정
 * 
 * 5. Controller 추가 및 핸들러 메소드 추가
 * 
 * 6. 아래 src에 webapp폴더부터 jsp까지 추가
 */
@SpringBootApplication
public class Chap01JspPracLectureSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chap01JspPracLectureSourceApplication.class, args);
		
		
	}

}
