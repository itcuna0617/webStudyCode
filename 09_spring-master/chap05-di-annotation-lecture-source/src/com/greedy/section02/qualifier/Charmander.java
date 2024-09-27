package com.greedy.section02.qualifier;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class Charmander implements Pokemon {

	@Override
	public void attack() {
		System.out.println("파이리가 공격을 합니다!~ 받아라~ 불~~ 퐈이아~~");
	}

}
