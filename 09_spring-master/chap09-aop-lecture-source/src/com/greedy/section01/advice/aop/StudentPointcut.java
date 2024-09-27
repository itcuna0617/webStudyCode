package com.greedy.section01.advice.aop;

import org.aspectj.lang.annotation.Pointcut;

public class StudentPointcut {
	@Pointcut("execution(* com.greedy.section01.advice..*(..))")
	public void studentPointcut() {}
}
