package com.greedy.section05.parameter;

import java.util.Arrays;

public class ParameterTest {

	public void testPrimitiveTypeParameter(int num) {
		System.out.println("매개변수로 전달 받은 값: " + num);
	}

	public void testPrimitiveTypeArrayParameter(int[] iArr) {
		System.out.println("매개변수로 전달 받은 값: " + Arrays.toString(iArr));
		
		for(int i = 0; i < iArr.length; i++) {
			System.out.print(iArr[i] + " ");
		}
		System.out.println();
		
		for(int i : iArr) {
			System.out.print(i + " ");
		}
	}

	public void testClassTypeParameter(Rectangle r1) {
		System.out.println("매개변수로 전달 받은 값: " + r1);
		
		r1.setWidth(100);
		r1.setHeight(200);
		
		r1.calcArea();
		r1.calcRound();
	}

	public void testVariableLengthArrayParameter(String name, String... hobby) {
		for(int i = 0; i < hobby.length; i++) {
			System.out.print(hobby[i] + " ");
		}
		System.out.println();
		
		System.out.println("이름: " + name + ", 취미의 갯수: " + hobby.length
				           + ", 취미: " + Arrays.toString(hobby));
	}
}
