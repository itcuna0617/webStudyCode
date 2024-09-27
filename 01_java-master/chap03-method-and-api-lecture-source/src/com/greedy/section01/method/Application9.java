package com.greedy.section01.method;

public class Application9 {
	public static void main(String[] args) {
		
		int first = 5;
		int second = 20;
		
		/* 최소값 출력(non-static 메소드) */
		Calculator calc = new Calculator();
		int min = calc.minNumberOf(first, second);
		
		System.out.println("두 수 중 최소값은: " + min);
		
//		System.out.println("두 수 중 최소값은: " + new Calculator().minNumberOf(first, second));
		
		/* 최대값 출력(static 메소드) */
		int max = Calculator.maxNumberOf(first, second);
		
		System.out.println("두 수 중 최대값은: " + max);
		
//		System.out.println("두 수 중 최대값은: " + Calculator.maxNumberOf(first, second)); 
	}
}
