package com.greedy.section04.constructor;

import com.greedy.section04.constructor.dto.User;
import com.greedy.section04.constructor.dto.UserDTO2;

public class Application {
	public static void main(String[] args) {
		
		/*
		 * 지금까지 우리는 인스턴스를 생성할 때
		 * 클래스명 레퍼런스변수 = new 클래스명(); 이렇게 인스턴스를 생성했다.
		 * 하지만 new 뒤에 클래스명과 소괄호 부분은 사실 생성자(constructor)라 불리는
		 * 일종의 메소드를 호출하는 구문이다.
		 * 정확히 말하자면 클래스명 레퍼런스변수 = new 생성자;라고 표현한다.
		 */
		
		/*
		 * 생성자란?
		 * 인스턴스를 생성할 때 초기 수행할 명령이 있는 경우 미리 작성해 두고, 인스턴스를 생성할 때 호출된다.
		 * 생성자에 매개변수가 없는 것을 기본 생성자라고 하며,
		 * 기본 생성자는 compiler에 의해 자동으로 추가되기 때문에 지금까지 명시적으로 작성하지 않고
		 * 사용할 수 있었다.
		 * (즉, 인스턴스 생성 시 별도로 수행 할 명령이 없었기 때문에 아무것도 작성하지 않은 것이다.)
		 * 
		 * 기본 생성자는 매개변수 있는 생성자가 있을 때에는 자동으로 추가되지 않기 때문에
		 * 그냥 기본 생성자는 명시적으로 클래스 작성 시 만들어 주자.
		 * (이후 상속이나 spring framework를 사용함에 있어서 자동으로 추가되지 않는 경우 내부적으로 사용할 때
		 * 에러가 발생할 수 있으므로 그냥 만드는 걸 기본으로 하자.)
		 */
		User user1 = new User();
		System.out.println(user1.getInformation());
		User user2 = new User("user02", "pass02", "신사임당");
		System.out.println(user2.getInformation());
		User user3 = new User("user03", "pass03", "이순신", new java.util.Date());
		System.out.println(user3.getInformation());
		
		UserDTO2 userDTO2 = new UserDTO2("userDTO01", "passDTO01", "장보고", new java.util.Date());
		System.out.println(userDTO2);	// println은 해당 객체의 toString()을 자동 실행한다.
	}
}





