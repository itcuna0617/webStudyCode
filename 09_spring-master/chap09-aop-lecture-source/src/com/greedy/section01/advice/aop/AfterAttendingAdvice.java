package com.greedy.section01.advice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.greedy.section01.advice.Passion;

@Aspect
@Component
public class AfterAttendingAdvice {

	@After("execution(* com.greedy.section01.advice..*(..))")
	public void afterAttending(JoinPoint joinPoint) {
		System.out.println("=========== after attending ===========");
		System.out.println("내일도 학원에 가서 열심히 공부해야지!!라는 마음으로 잠자리에 듭니다.");
		System.out.println("수강생 타입: " + joinPoint.getTarget().getClass());
		System.out.println("수강생의 행위: " + joinPoint.getSignature());
		System.out.println("행위 요약: " + joinPoint.getSignature().getName());
		System.out.println("수강생의 열정: " + ((Passion)joinPoint.getArgs()[0]).getScore());
		System.out.println("=======================================");
	}
}
