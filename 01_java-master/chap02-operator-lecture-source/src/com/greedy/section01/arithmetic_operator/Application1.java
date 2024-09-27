package com.greedy.section01.arithmetic_operator;

public class Application1 {
	public static void main(String[] args) {
		
		/* 산술 연산자 */
		/*
		 * 산술 연산자는 주로 사칙연산과 관련된 연산자로 가장 기본적이면서도 많이 사용되는 연산자이다.
		 * 연산의 실행이 가능하기 위해 필요한 값이나 변수가 두 개인 이항 연산자로 분류되며
		 * 피연산자들의 연산 방향은 왼쪽에서 오른쪽이다.
		 */
		
		int num1 = 20;
		int num2 = 7;
		
		System.out.println("num1 + num2 = " + (num1 + num2));			// 27
		System.out.println("num1 - num2 = " + (num1 - num2));			// 13
		System.out.println("num1 * num2 = " + (num1 * num2));			// 140
		System.out.println("num1 / num2 = " + (num1 / (double)num2));	// 2.857142857142857
		System.out.println("num1 % num2 = " + (num1 % num2));			// 6
		
		System.out.println(((num1 * 100) / num2) / 100.0);				// 2.85, 소수점 셋째자리부터 버림 효과 내기
	}
}




