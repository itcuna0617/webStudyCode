package com.greedy.section02.looping_and_branching.level04.advanced;

import java.util.Scanner;

public class Application2 {

	public static void main(String[] args) {
		
		/* 받은 금액을 입력 받고, 상품 가격을 입력 받아 거스름돈을 계산하는 프로그램을 작성하세요
		 * 
		 * 단, 거스름돈은 대한민국 화폐 단위 별 필요 수량이 출력되도록 작성하고,
		 * 지폐외 동전을 구분하여 단위를 표기하세요
		 * 
		 * -- 입력 예시 --
		 * 받으신 금액을 입력하세요 : 100000
		 * 상품 가격을 입력하세요 : 22340
		 * 
		 * -- 출력 예시 --
		 * ============================
		 * 50000원권 지폐 1장
		 * 10000원권 지폐 2장
		 * 5000원권 지폐 1장
		 * 1000원권 지폐 2장
		 * 500원권 동전 1개
		 * 100원권 동전 1개
		 * 50원권 동전 1개
		 * 10원권 동전 1개
		 * ============================
		 * 거스름돈 : 77660원
		 * */
		
		Scanner sc = new Scanner(System.in);

		System.out.print("받으신 금액을 입력하세요 : ");
		int money = sc.nextInt();

		System.out.print("상품 가격을 입력하세요 : ");
		int price = sc.nextInt();

		int change = money - price;
		int currency = 50000;			// 화폐 단위
		int mok = 0;					// 화폐 단위로 나눈 몫(화폐 단위 장수)
		int nmg = 0;					// 화폐 단위로 나눈 나머지(다음 화폐로 계산)
		int sw = 0;						// 5로 나눌 때와 2로 나눌 때
		int temp = change;				// 잔돈

		System.out.println("=========================");
		while(true) {
			mok = temp / currency;
			nmg = temp % currency;

			if(mok == 0 && nmg == 0) {
				break;
			}
			
			if(mok != 0) {
				if(currency > 500)
					System.out.println(currency + "원권 지폐 " + mok + "장");
				else
					System.out.println(currency + "원권 동전 " + mok + "개");
			}

			if(sw == 0) {
				sw = 1;
				currency /= 5;		// currency = currency / 5;
			} else {
				sw = 0;
				currency /= 2;
			}
			
			temp = nmg;
		}
		
		System.out.println("=========================");
		System.out.println("거스름돈 : " + change + "원");
		
		sc.close();

	}

}
