package com.greedy.section02.qualifier;

import org.springframework.stereotype.Component;

@Component
public class Squirtle implements Pokemon {

	@Override
	public void attack() {
		System.out.println("꼬부기가 공격을 합니다!~ 받아라!! 물대포~~~");
	}

}
