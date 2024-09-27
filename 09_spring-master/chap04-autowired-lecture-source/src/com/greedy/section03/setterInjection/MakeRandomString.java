package com.greedy.section03.setterInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MakeRandomString {
	
	private RandomGenerator random;
	
	public MakeRandomString() {}
	
	public MakeRandomString(RandomGenerator random) {
		this.random = random;
	}
	
	@Autowired
	public void setRandom(RandomGenerator random) {
		System.out.println("이거쓰냐?");
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
