package com.greedy.level01.basic;

public class Calculator {
	/* "메소드 호출 확인"을 출력하는 메소드 */
	public void checkMethod(){
		System.out.println("메소드 호출 확인");
		
		return;
	}
	
	/* 1부터 10까지의 총 합을 반환하는 메소드 */
	public int sum1to10(){
		int sum = 0;
		
		for(int i = 0; i <= 10; i++){
			sum += i;
		}
		
		return sum;
	}
	
	/* 2개의 매개변수 중 더 큰수를 출력하는 메소드 */
	public void checkMaxNumber(int a, int b){
		int max = a;
		
		if(a > b){
			max = a;
		}
		if(b > a){
			max = b;
		}
		
		System.out.println("두 수 중 큰 수는 " + max + "이다.");
		
		return;
	}
	
	/* 2개의 매개변수의 합을 반환하는 메소드 */
	public int sumTwoNumber(int first, int second){
		int sum = first + second;
		
		return sum;
	}
	
	/* 첫번째 매개변수에서 두번째 매개변수를 뺀 값을 반환하는 메소드 */
	public int minusTwoNumber(int first, int second){
		
		return first - second;
	}
	
}
