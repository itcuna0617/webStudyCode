package com.greedy.section02.qualifier;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // Pokemon 타입의 bean이 하나만 주입되게 허용
public class Pikachu implements Pokemon {

	@Override
	public void attack() {
		System.out.println("피카츄가 공격을 합니다!~ 백만볼트~ 찌지지지직~");
	}

}
