package com.greedy.section01.method;

public class Application8 {
	public static void main(String[] args) {

		/* non-static 메소드 호출 */
		int first = 20;
		int second = 10;
		
//		System.out.println(new Application8().plusTwoNumbers(first, second));
		
		Application8 app8 = new Application8();
		System.out.println(app8.plusTwoNumbers(first, second));
		System.out.println(app8.minusTwoNumbers(first, second));
		System.out.println(app8.multipleTwoNumbers(first, second));
		System.out.println(app8.divideTwoNumbers(first, second));
	}
	
	public int plusTwoNumbers(int first, int second) {
		return first + second;
	}
	
	public int minusTwoNumbers(int first, int second) {
		return first - second;
	}
	
	public int multipleTwoNumbers(int first, int second) {
		return first * second;
	}
	
	public int divideTwoNumbers(int first, int second) {
		return first / second;
	}
}
