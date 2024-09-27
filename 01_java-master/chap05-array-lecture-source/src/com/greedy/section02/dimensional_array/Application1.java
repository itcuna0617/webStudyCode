package com.greedy.section02.dimensional_array;

import java.util.Arrays;

public class Application1 {
	public static void main(String[] args) {
		
		/*
		 * 다차원 배열
		 * 다차원 배열은 2차원 이상의 배열을 의미한다.
		 * 배열의 인덱스마다 또 다른 배열의 주소를 보관하는 배열을 의미한다.
		 * 즉, 2차원 배열은 1차원 배열 여러 개를 하나로 묶어서 관리하는 1차원 배열을 의미한다.
		 * 더 많은 차원의 배열을 사용할 수 있지만 일반적으로 2차원 배열보다 더 높은 차원의
		 * 배열은 사용 빈도가 현저히 적다. (인지 범위 초과)
		 */
		
		/*
		 * 2차원 배열을 사용하는 방법
		 * 1. 배열의 주소를 보관 할 레퍼런스 변수 선언(stack)
		 * 2. 여러 개의 1차원 배열의 주소를 관리하는 배열을 생성(heap)
		 * 3. 각 인덱스에서 관리하는 배열을 할당(heap)하여 주소를 보관하는 배열에 저장
		 * 4. 생성한 여러 개의 1차원 배열에 차례로 접근해서 사용
		 */
		
		/* 1. 배열의 주소를 보관 할 레퍼런스 변수 선언(stack) */
		int[][] iArr1;		// 권장하는 방식
		int iArr2[][];
		int[] iArr3[];
		
		/* 2. 여러 개의 1차원 배열의 주소를 관리하는 배열을 생성(heap) */
//		iArr1 = new int[][];		// 크기를 아예 지정하지 않으면 에러 발생
//		iArr1 = new int[][4];		// 1차원 배열들의 주소를 관리 할 배열의 크기를 지정하지 않으면 에러 발생
		iArr1 = new int[3][];		// 1차원 배열들의 주소를 관리 할 1차원 배열의 크기만 지정하는 것은 허용
		
		/* 3. 주소를 관리하는 배열의 인덱스마다 배열을 할당할 수 있다.(크기가 다르게 가능, 가변배열) */
		iArr1[0] = new int[4];
		iArr1[1] = new int[3];
		iArr1[2] = new int[5];
		
		/*
		 * 서로 같은 길이의 1차원 배열 여러 개를 하나로 묶어 관리하는 2차원 배열을 정변배열이라고 하며,
		 * 서로 길이가 같지 않은 1차원 배열 여러 개를 하나로 묶어 관리하는 2차원 배열을 가변배열이라고 한다.
		 */
		
		iArr2 = new int[3][5];
		
		/* 4. 각 배열의 인덱스에 차례로 접근해서 값 출력 */
		/* iArr1의 0번째 인덱스에 있는 배열 출력 */
		for(int i = 0; i < iArr1[0].length; i++) {
			System.out.print(iArr1[0][i] + " ");
		}
		System.out.println();
		
		/* iArr1의 1번째 인덱스에 있는 배열 출력 */
		for(int i = 0; i < iArr1[1].length; i++) {
			System.out.print(iArr1[1][i] + " ");
		}
		System.out.println();
		
		/* iArr1의 2번째 인덱스에 있는 배열 출력 */
		for(int i = 0; i < iArr1[2].length; i++) {
			System.out.print(iArr1[2][i] + " ");
		}
		System.out.println();
		System.out.println();
		
		/* 5. 중첩 for문(2중 for문)을 이용해서 배열의 값 출력(가변이든 정변이든 사용 가능) */
		for(int i = 0; i < iArr1.length; i++) {
			for(int j = 0; j < iArr1[i].length; j++) {
				System.out.print(iArr1[i][j] + " ");
			}
			System.out.println();
		}
		
		/* 추가로, 2차원 배열부터는 Arrays.toString(배열)이 적용 되지 않음 */
		System.out.println(Arrays.toString(iArr1));			// 1차원 배열의 주소값을 저장한 1차원 배열이 나온다.(원치 않는 모습)
		
		for(int i = 0; i < iArr1.length; i++) {
			System.out.println(Arrays.toString(iArr1[i]));
		}
	}
}







