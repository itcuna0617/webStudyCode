package com.greedy.section04.overflow;

public class Application1 {
	public static void main(String[] args) {
		
		/* 데이터 오버플로우 */
		/*
		 * 자료형 별 값의 최대 범위를 벗어나는 경우
		 * 발생한 carry를 버림 처리하고 sign bit(MSB)를 반전시켜 최소값으로 순환시킴
		 */
		byte num1 = 126;
		
		num1++;			
		System.out.println("num1: " + num1);	// 127
		
		num1++;
		System.out.println("num1: " + num1);	// -128
		
		num1++;
		System.out.println("num1: " + num1);	// -127
		
		num1++;
		System.out.println("num1: " + num1);	// -126
		
		/* 데이터 언더플로우 */
		/* 오버플로우와 반대 개념으로 최소 범위보다 작은 수를 발생 시키는 경우 발생하는 현상이다. */
		byte num2 = -127;
		
		num2--;
		System.out.println("num2: " + num2);	// -128
		
		num2--;
		System.out.println("num2: " + num2);	// 127
		
		num2--;
		System.out.println("num2: " + num2);	// 126
		
		num2--;
		System.out.println("num2: " + num2);	// 125
		
		/* 추가 설명(++ 및 -- 사용 이유) */
		byte num3 = 127;
		num3 = (byte)(num3 + 1);		// num3 + 1의 결과를 자바는 int로 받아들이고 int는 byte형 변수에 대입될 수 없다.(그래서 강제 형변환 함)
		System.out.println("num3: " + num3);
	}
}







