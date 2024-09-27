package com.greedy.section04.constructor;

import com.greedy.section04.constructor.dto.UserDTO2;

public class Application2 {
	public static void main(String[] args) {
		
		/* setter(설정자)를 이용한 초기화와 매개변수 있는 생성자를 이용한 초기화는 각각의 장단점이 존재한다. */
		/*
		 * 1. 기본생성자와 설정자를 이용한 초기화
		 * 장점: 필드를 초기화 하는 각각의 값들이 어떤 필드를 초기화 하는지 명확하게 볼 수 있다. 
		 *      초기화 할 필드를 수정하기 용이하다.
		 * 단점: 하나의 인스턴스를 생성할 때 한번의 호출로 끝나지 않는다.
		 * 
		 * 2. 매개변수 있는 생성자를 이용한 초기화
		 * 장점: setter 메소드를 여러번 호출해서 사용하지 않고 단 한번의 호출로 인스턴스를
		 *      생성 및 필드 값 초기화를 할 수 있다.
		 * 단점: 필드를 초기화 할 때 매개변수의 갯수에 따라 경우의 수 별로 생성자를 만들어 두어야 한다.
		 *      호출 시 전달인자가 많아지는 경우 어떠한 값이 어떤 필드를 의미하는지 한 눈에 알아보기 힘들다.
		 */
		
		/* setter 사용 */
		UserDTO2 user1 = new UserDTO2();
		user1.setId("user01");
		user1.setPwd("pwd01");
		user1.setName("홍길동");
		user1.setEnrollDate(new java.util.Date());
		System.out.println(user1.toString());
		
		/* 매개변수 있는 생성자 사용 */
		UserDTO2 user2 = new UserDTO2("user02", "pwd02", "허균", new java.util.Date());
		System.out.println(user2.toString());
	}
}




