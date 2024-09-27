package com.greedy.section01.advice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.greedy.section01.advice.AchievementResult;

@Aspect
@Component
public class AfterReturningAtteningAdvice {
	
	/*
	 * 타겟 메소드가 실행되고 나서 반환값이 나오면 그 반환값을 추가적으로 처리할 수 있게 하는 어드바이스
	 * (returning 속성은 어드바이스의 Object 타입인 매개변수 이름과 동일해야 한다.)
	 */
	@AfterReturning(pointcut="execution(* com.greedy.section01.advice..*(..))", 
					returning="result")
	public void afterReturningAttending(JoinPoint joinPoint, Object result) {
		System.out.println("========== after returning attending ===========");
		System.out.println("보람찬! 하루일을! 끝마치고서~~ 두 다리 쭉 펴면 고향의 안방~~~!!");
		System.out.println("오늘의 이해도: " + ((AchievementResult)result).getUnderstandingScore());
		System.out.println("오늘의 만족도: " + ((AchievementResult)result).getSatisfactionScore());
		
		/* 취업율 수치를 백분율로 환산해서 퍼센트로 변환해 보자. 1000점 만점이다. */
		double employeementRate = ((AchievementResult)result).getEmployeementRate();
		double percent = employeementRate / 1000 * 100;
		System.out.println("증가 된 취업율: " + percent + "%");
		
		/* 리턴 할 결과값을 변경해 줄 수도 있다. */
		((AchievementResult)result).setEmployeementRate(percent);
		
		System.out.println("================================================");
	}
}










