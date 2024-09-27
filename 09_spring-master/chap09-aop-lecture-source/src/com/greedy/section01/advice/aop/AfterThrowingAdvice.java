package com.greedy.section01.advice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterThrowingAdvice {
	
	/*
	 * AfterThrowing Advice는 예외가 발생해야 동작한다.
	 * 
	 * Application에서 Passion 수치 수정으로 테스트
	 * 1. NormalStudent는 0보다 작은 값으로
	 * 2. GreedyStudent는 10보다 작은 값으로
	 * Passion 수치가 주어지면 예외 발생
	 */
	
	@AfterThrowing(pointcut="execution(* com.greedy.section01.advice..*(..))"
					, throwing="exception")
	public void afterThrowingAttending(JoinPoint joinPoint, Throwable exception) {
		System.out.println("============ after throwing attending ==========");
		System.out.println(exception.getMessage());
		System.out.println("================================================");
	}
}
