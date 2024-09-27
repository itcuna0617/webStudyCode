package com.greedy.level02.normal;

import java.util.Scanner;

public class PrintInput {

	/* 이름(String), 나이(int), 주소(String), 성별(char)을 입력받고 출력하는 메소드 */
	/*
	 * ex)
	 * ==== 입력하신 개인 정보 ====
	 * 이름 : 김용승
	 * 나이 : 20
	 * 주소 : 서울시 서초구
	 * 성별 : 남
	 */
	public void printInform() {
		System.out.println("==== 입력하신 개인 정보 ====");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("주소 : ");
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("성별 : ");
		char gender = sc.nextLine().charAt(0);
		
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("주소 : " + address);
		System.out.println("성별 : " + gender);	
		
		return;
	}

	/* 매개변수 두개를 입력받아 해당하는 범위의 난수를 생성하는 메소드 */
	/*
	 * 1. 반환형은 int
	 * 2. 매개변수 두개 중 어떤게 큰지를 판단해서 랜덤수를 생성
	 */
	public static int makeRandom(int first, int second) {
		int amount = 0;
		int random = 0;
		
		if(first < second) {
			amount = second - first + 1;
			random = (int)(Math.random() * amount) + first;
		}else {
			amount = first - second + 1;
			random = (int)(Math.random() * amount) + second;
		}
		
		return random;
	}

}
