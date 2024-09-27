package com.greedy.section01.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration					// 이 클래스가 설정 메타 정보를 가지고 있다는 의미를 가진 어노테이션을 추가한다.
public class ContextConfiguration {
	@Bean(name="member")		// name 속성명을 달지 않고 이름을 지어줄 수 있으며, name 설정을 안하면 메소드 이름으로 빈이 생성된다.
	public MemberDTO getMember() {
		return new MemberDTO(1, "user01", "pass01", "홍길동");
	}
	
//	@Bean("member2")		// name 속성명을 달지 않고 이름을 지어줄 수 있으며, name 설정을 안하면 메소드 이름으로 빈이 생성된다.
//	public MemberDTO getMember2() {
//		return new MemberDTO(2, "user02", "pass02", "유관순");
//	}
}
