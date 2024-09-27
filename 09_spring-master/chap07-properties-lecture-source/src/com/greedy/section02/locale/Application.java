package com.greedy.section02.locale;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.greedy.section02.locale.config.ContextConfiguration;

public class Application {

	public static void main(String[] args) {
		
		/*
		 * I18N 소프트웨어의 국제화(Internationalization)
		 * 국제화 - 그 나라 사람들이 해당 사이트를 이용할 수 있도록, 사용할 수 있도록
		 * 
		 * 1. 언어, 지역별 번역
		 * 2. OS/플랫폼 인코딩 - 나라별로 쓰는 OS
		 * 3. 문자열 치환 방법 : 서버쪽, 클라이언트쪽 치환 중 서버 쪽에서 하는게 좋다.(경우에 따라 다름)
		 * 4. 국제화 UI(문자열 크기 변화, 폰트, 아이콘 등)
		 * 5. 쓰기 방향의 차이
		 * 6. 숫자, 공백, 화폐, 날짜, 주소, 측정단위 등
		 * 7. 타임존, 썸머타임 등 시각
		 * 8. 문자열 정렬 방법
		 * 
		 * L10N(Localization)
		 * 현지화 - 그 나라의 문화에 맞도록(그 나라의 정서, 뉘양스에 맞도록, 그 나라에 사이트가 젖어들 수 있도록)
		 */
		
		ApplicationContext context =
				new AnnotationConfigApplicationContext(ContextConfiguration.class);
		
		String error404Message = 
				context.getMessage("error.404", null, Locale.KOREA);
		String error500Message = 
				context.getMessage("error.500", new Object[] {"pikachu", new java.util.Date()}, Locale.US);
		
		System.out.println("The I18N message for error.404: " + error404Message);
		System.out.println("The I18N message for error.500: " + error500Message);
		
	}

}
