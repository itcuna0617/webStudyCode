package com.greedy.section02.constInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MakeRandomString {

	private RandomGenerator random;
	
	/*
	 * 생성자 주입의 장점
	 * 1. 필드에 final 키워드 사용이 가능해진다.(해당 속성이 변경 불가, 필드 주입시에는 굳이 매개변수 있는 생성자를 안 만들 수 있기 때문)
	 * 2. 순환참조 방지(필드 주입이나 세터 주입의 경우 메소드 실행 시점에만 발생할 수 있지만, 생성자 주입을 하면
	 *    애플리케이션 실행 시점에 확인 가능하다.)
	 * 3. DI 컨테이너와 결합도가 낮기 때문에 테스트 하기 좋다.(스프링 컨테이너 없이 테스트를 할 수 있다.)
	 */
	
	public MakeRandomString() {}
	
	@Autowired
	public MakeRandomString(RandomGenerator random) {
		this.random = random;
	}





	/* 난수 발생기를 통해 발생한 난수만큼 별이 있는 문자열 반환 */
	public String getRandomLengthString() {
		StringBuilder sb = new StringBuilder();
		
		int randomNumber = random.getRandomNumber();
		for(int i = random.getStartNum(); i <= randomNumber; i++) {
			sb.append("*");
		}
		
		return sb.toString();
	}
	

}
