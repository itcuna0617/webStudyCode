package com.greedy.section01.advice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.greedy.section01.advice.GreedyStudent;

@Aspect
@Component
public class AroundAttendingAdvice {
	
	@Around("execution(* com.greedy.section01.advice..*(..))")
	public Object aroundAttending(ProceedingJoinPoint joinPoint) throws Throwable {
		
		/*
		 * Around Advice는 가장 강력한 어드바이스이다.
		 * 이 어드바이스는 조인포인트를 완전히 장악하기 때문에
		 * 앞에서 살펴 본 어드바이스(Before와 After)를 모두 Around 어드바이스로 조합할 수 있다.
		 * 심지어 원본 조인포인트를 언제 실행할 지, 실행 자체를 안 할지, 계속 실행할 지 여부까지도 제어한다.
		 * AroundAdvice의 조인포인트 매개변수는 ProceedingJoinPoint로 고정되어 있다.(JoinPoint 아님)
		 * JoinPoint의 하위 인터페이스로 원본 조인포인트의 진행 시점을 제어할 수 있다.(proceed())
		 * 조인포인트를 진행하는 호출을 잊는 경우가 자주 발생하기 때문에 주의해야 하며
		 * 최소한의 요건을 충족하면서도 가장 기능이 약한 어드바이스(Before나 After같은 단일 시점 기능들)
		 * 를 쓰는게 바람직하다.
		 */
		
		System.out.println("========== around attending before ==========");
		System.out.println("오늘도 학원에 가서 열심히 공부해야지!!라는 마음으로 아침 일찍 일어납니다.");
		System.out.println("=============================================");

		/* 공부라는 기능이 수행되는 시간을 체크하기 위해 스톱워치를 켜보자. */
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		/* 타겟 메소드를 실행한다. */
		Object result = joinPoint.proceed();  // 예외 처리할 것
		
		stopWatch.stop();
		
		/* 타겟 메소드 수행 시간을 확인 */
		System.out.println("공부 기능이 수행되는 소요 시간: " + stopWatch.getTotalTimeMillis() + "(ms)");
		
		System.out.println("========== around attending after ===========");
		
		if(joinPoint.getTarget() instanceof GreedyStudent) {
			System.out.println("수업이 끝나도 학원이 문을 닫을 때까지는 끝난게 아닙니다."
										+ "조원들과 자율적 스터디를 진행합니다!!");
		}
		
		System.out.println("열심히 공부했으니 퇴실 카드를 찍는다.");
		System.out.println("=============================================");
		
		return result;			// 타겟메소드가 반환한 값을 around advice에서 반환해 줘야 메소드를 호출한 곳에 제대로 전달 된다.
	}
}









