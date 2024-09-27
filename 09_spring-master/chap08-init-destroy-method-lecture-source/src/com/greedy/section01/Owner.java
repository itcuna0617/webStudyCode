package com.greedy.section01;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class Owner {

	@PostConstruct	// Owner 빈 객체 생성 이후
	public void openShop() {
		System.out.println("사장님이 가게 문을 오픈하셨습니다. 이제 쇼핑을 하실 수 있습니다.");
	}
	
	@PreDestroy		// Owner 빈 객체 소멸 이전
	public void closeShop() {
		System.out.println("사장님이 가게 문을 닫으셨습니다. 이제 쇼핑을 하실 수 없습니다.");
	}
}
