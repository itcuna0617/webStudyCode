package com.greedy.section01.tests;

public class Application {

	/* 테스트 시나리오 */
	/* 애플리케이션에서 테스트 되어야 할 기능이나 특징을 한 문장으로 서술한 것 */
	
	public static void main(String[] args) {

		/* 1. Calculator 인스턴스가 잘 생성이 되는지 테스트 */
		Calculator calc = null;
		calc = new Calculator();
		
		if(calc != null) {
			System.out.println("인스턴스 생성 성공!");
		} else {
			System.out.println("인스턴스 생성 실패!");
		}
		
		/* 2. sumTwoNumbers 메소드가 정상 기능을 하는지 테스트 */
		/* 2-1. 4와 5를 전달하면 합계 9가 계산되어 반환되는지 확인 */
		int result1 = calc.sumTwoNumbers(4, 5);
		
		if(result1 == 9) {
			System.out.println("4와 5를 전달하여 합계가 9인 것을 확인!");
		} else {
			System.out.println("4와 5를 전달하여 합계가 9가 아닌 것을 확인!");
		}
		
		/* 2-2. 6와 7를 전달하면 합계 13으로 계산되어 반환되는지 확인 */
		int result2 = calc.sumTwoNumbers(6, 7);
		
		if(result2 == 13) {
			System.out.println("6과 7을 전달하여 합계가 13인 것을 확인!");
		} else {
			System.out.println("6와 7을 전달하여 합계가 13이 아닌 것을 확인!");
		}
		
		/* 3. 위의 테스트 결과가 모두 통과되면 해당 클래스의 메소드는 신뢰성 있는 메소드임을 확인(증명) */
		if(result1 == 9 && result2 == 13) {
			System.out.println("테스트 성공! 내가 제대로 된 메소드를 만들었구나!");
		} else {
			System.out.println("테스트 실패! 다시 메소드를 확인하자!");
		}
	}
}
