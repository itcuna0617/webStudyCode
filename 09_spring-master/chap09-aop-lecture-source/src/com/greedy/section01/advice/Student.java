package com.greedy.section01.advice;

public interface Student {
	
	/* 열정을 전달하여 공부한 뒤 성취도 결과를 반환하는 추상 메소드 */
	AchievementResult study(Passion passion) throws Exception;
}
