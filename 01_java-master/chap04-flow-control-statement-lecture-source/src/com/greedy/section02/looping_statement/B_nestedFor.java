package com.greedy.section02.looping_statement;

import java.util.Scanner;

public class B_nestedFor {
	public void printGugudanFromTwoToNine() {
		
		/* for문 안에서 for문을 이용할 수 있다.(중첩 반복문) */
		/*
		 * 2 * 1 = 2
		 * 2 * 2 = 4
		 * 2 * 3 = 6
		 * 2 * 4 = 8
		 * 2 * 5 = 10
		 * 2 * 6 = 12
		 * 2 * 7 = 14
		 * 2 * 8 = 16
		 * 2 * 9 = 18
		 * 
		 * 3 * 1 = 3
		 * 3 * 2 = 6
		 * 3 * 3 = 9
		 * 3 * 4 = 12
		 * 3 * 5 = 15
		 * 3 * 6 = 18
		 * 3 * 7 = 21
		 * 3 * 8 = 24
		 * 3 * 9 = 27
		 * 
		 * ...
		 */
		
		for(int i = 2; i <= 9; i++) {	    // i는 단수
			System.out.println("=======" + i + "단 출력 =======");
			for(int j = 1; j <= 9; j++) {	// j는 곱하는 수
				System.out.println(i + " * " + j + " = " + (i * j));
			}
			System.out.println("=====================");
		}
		
//		for(int i = 1; i <= 8; i++) {
//			System.out.println("=======" + (i + 1) + "단 출력 =======");
//			for(int j = 1; j <= 9; j++) {	// j는 곱하는 수
//				System.out.println((i + 1) + " * " + j + " = " + ((i + 1) * j));
//			}
//			System.out.println("=====================");
//		}
	}

	public void printUpgradeGugudanFromTwoToNine() {
		for(int i = 2; i <= 9; i++) {
			System.out.println("=======" + i + "단 출력 =======");
			printGugudanOf(i);		// i(단)이 정해지면 1부터 9까지 곱하는 기능을 메소드로 만들자.
			System.out.println("=====================");
		}
	}

	private void printGugudanOf(int dan) {
		for(int su = 1; su <= 9; su++) {	
			System.out.println(dan + " * " + su + " = " + (dan * su));
		}
	}

	public void printStarInputRowTimes() {
		
		/*
		 * input: 5
		 * 
		 * *
		 * **
		 * ***
		 * ****
		 * *****
		 */
		
//		Scanner sc = new Scanner(System.in);
//		System.out.print("출력할 행(row) 수를 입력하세요: ");
//		int row = sc.nextInt();
//		
//		for(int i = 1; i <= row; i++) {
//			for(int j = 1; j <= i; j++) {	// i(줄, row)가 정해지면 i번 반복해서 별을 찍고
//				System.out.print("*");
//			}
//			System.out.println();			// 엔터효과
//		}
		
		/*
		 * input: 5
		 *     *
		 *    ***
		 *   *****
		 *  *******
		 * *********
		 */
		
		/* 규칙성 찾아보기 */
//		i = 1, 공백 = 4, 별 = 1 -> 공백 = (input - i), 별 = (i * 2 - 1)
//		i = 2, 공백 = 3, 별 = 3
//		i = 3, 공백 = 2, 별 = 5
//		i = 4, 공백 = 1, 별 = 7
//		i = 5, 공백 = 0, 별 = 9
		
		Scanner sc = new Scanner(System.in);
		System.out.print("input: ");
		int input = sc.nextInt();
		for(int i = 1; i <= input; i++) {
			
			/* 한줄씩 공백 찍기 */
			for(int j = 1; j <= input - i; j++) {
				System.out.print(" ");
			}
				
			/* 한줄씩 별 찍기 */
			for(int j = 1; j <= i * 2 - 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}





