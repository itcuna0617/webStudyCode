package com.greedy.section03.branching_statement;

public class A_break {
	public void testSimpleBreakStatement1() {
		
		/*
		 * break문은 반복문 내에서 사용한다.
		 * 해당 반복문을 빠져 나올 때 사용하며, 반복문의 조건식 판단 결과와 상관 없이
		 * 반복문을 빠져나올 때 사용한다.
		 * 일반적으로 if(조건식) { break; } 처럼 사용된다.
		 * 단, switch문은 반복문이 아니지만 예외적으로 사용된다.(이 때는 switch만 빠져나온다는 의미를 가짐)
		 */
		
		/* break문을 활용하여 무한루프에서 1~100까지 합계 구하기 */
		int sum = 0;		// 합계
		int i = 1;			// 더할 수
		while(true) {
			sum += i;
				if(i == 100) {
					break;
				}
			i++;
		}
		System.out.println("1부터 100까지의 합은 " + sum + "입니다.");
	}

	public void testSimpleBreakStatement2() {
		
		/* 중첩 반복문 내에서 분기문의 흐름 */
		/* break는 모든 반복문을 종료하는 것이 아닌, 자신에게 가장 인접한 반복문 실행만 멈춘다. */
		
		/*
		 * 구구단 2 ~ 9단 출력
		 * 단, 각 단의 수(곱하는 값)가 5보다 큰 경우는 출력을 생략한다.
		 */
		for(int dan = 2; dan < 10; dan++) {
			for(int su = 1; su < 10; su++) {
				if(su > 5) {
					break;
				}
				System.out.println(dan + " * " + su + " = " + (dan * su));
			}
			System.out.println();
		}
	}
}






