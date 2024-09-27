package com.greedy.section02.locale.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ContextConfiguration {
	
	/* 스프링에서 제공하는 각 나라에 맞게 메세지를 자동화해서 구현할 때 사용되는 메소드 */
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		
		/* 접속하는 locale에 따라 자동으로 재로딩하는 용도의 MessageSource 구현체 */
		ReloadableResourceBundleMessageSource messageSource =
								new ReloadableResourceBundleMessageSource();
		
		/* 메세지 소스에 대한 설정 */
		/* 1. 다국어 메세지를 읽어올 properties 파일의 기본 파일명을 설정한다. */
		messageSource.setBasename("message");
		
		/* 2. 불러온 메세지를 해당 시간동안 캐싱한다. */
		messageSource.setCacheSeconds(10);
		
		/* 3. 기본 인코딩 셋을 설정한다. */
		messageSource.setDefaultEncoding("UTF-8");
		
		return messageSource;
	}
}




