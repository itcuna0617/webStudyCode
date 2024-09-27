package com.greedy.section01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.greedy.section01.Account;
import com.greedy.section01.MemberDTO;
import com.greedy.section01.PersonalAccount;

@Configuration
public class ContextConfiguration {

	/* MemberDTO타입의 bean은 생성자 주입 방식을 통해 Account타입의 bean을 주입 받게 설정 */
	@Bean("account")
	public Account accountGenerator() {
		return new PersonalAccount(20, "011-123-123456", "1234", 0);
	}
	
	@Bean("member")
	public MemberDTO memberGenerator() {
		return new MemberDTO(1, "홍길동", "010-123-1234", "hong123@gmail.com"
				, accountGenerator());
	}
}
