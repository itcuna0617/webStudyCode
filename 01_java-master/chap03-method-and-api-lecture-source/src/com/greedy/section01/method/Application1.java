package com.greedy.section01.method;

public class Application1 {
	public static void main(String[] args) {		// main()메소드는 자바 프로그램을 실행 및 종료하는 기능을 지님
		
		/* 메소드란? */
		/*
		 * 메소드(method)는 어떤 특정 작업을 수행하기 위한 명령문의 집합이라고 할 수 있다.
		 * 자세한 메소드 사용법은 객체 부분에서 다루게 되니 지금은 호출 흐름에 대해 이해할 수 있도록 하자.
		 */
		
		/* 1. 먼저 main()메소드가 동작하는지 확인하기 위한 간략한 출력 구문을 작성하자. */
		System.out.println("main() 시작 됨...");
		
		/* 2. main() 메소드 밖(클래스 안이면서 main()메소드 밖)에 methodA()라는 메소드를 추가하자. */
		
		/* 5. 작성한 메소드를 호출하는 코드 작성 */
		Application1 app1 = new Application1();		// static이 아닌 메소드가 보이도록 클래스 전체를 인지시키자.
		app1.methodA();
		
		/* 6. 코드 동작 흐름을 더 이해하기 위해 main()메소드가 종료되기 직전에 출력하는 구문을 간단히 작성하자. */
		System.out.println("main() 종료...");
		
		/* 7. 메소드 실행흐름을 확인하고 methodB()를 추가한다. */
	}
	
	/* 3. 호출 할 메소드 생성 */
	public void methodA() {
		
		/* 4. 호출 확인을 위해 간단히 출력 구문을 작성하고 다시 main()메소드 내부에서 호출 코드를 작성하자. */
		System.out.println("methodA() 호출 됨...");
		
		/* 11. methodB() 호출 구문 작성 */
		methodB();					// static이 안 붙은 메소드들끼리는 쉽게 메소드명으로 호출할 수 있다.
		
		/* 12. methodA() 종료 직전 출력 구문 추가 */
		System.out.println("methodA() 종료 됨...");
		
		/* 13. 다시 한번 methodC()를 맨 아래에 추가해 보자. */
	}  
	
	/* 8. 호출 확인을 위한 메소드 생성 */
	public void methodB() {
		
		/* 9. 동작하는지 확인하는 용도의 출력 구문을 작성하자. */
		System.out.println("methodB() 호출 됨...");
		
		/* 10. methodA() 내부에서 methodB()를 호출하는 구문을 작성하자. */
		
		/* 17. methodC() 호출 */
		methodC();
		
		/* 18. methodB()가 종료되는 시점을 확인하기 위해 출력 구문 추가 */
		System.out.println("methodB() 종료 됨...");
	}
	
	/* 14. 호출 확인을 위한 메소드 생성 */
	public void methodC() {
		
		/* 15. 동작하는지 확인하는 용도의 출력 구문을 작성하자. */
		System.out.println("methodC() 호출 됨...");
		
		/* 16. methodB() 내부에서 methodC()를 호출하는 구문을 작성하자. */
		
		/* 19. methodC() 종료 구문 추가 */
		System.out.println("methodC() 종료 됨...");
	}
}




