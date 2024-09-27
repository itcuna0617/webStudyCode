package com.greedy.section01.advice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.greedy.section01.advice.Passion;

/* @Aspect를 사용하기 위해서는 라이브러리가 필요하다.(aspectjrt 라이브러리, aspectjweaver 라이브러리) */
/* 설정파일에도 어노테이션을 추가해야 동작한다.(@EnableAspectJAutoProxy(proxyTargetClass = true)) */
@Aspect			// advice + pointcut의 개념
@Component		// 빈으로 관리 되어야 한다.
public class BeforeAttendingAdvice {

	/*
	 * 타켓 클래스의 메소드에서 어드바이스를 적용할 수 있는 지점을 조인포인트(joinpoint)라고 한다.
	 * 포인트컷(pointcut)은 여러 조인포인트들에 어드바이스(advice)를 적용할 곳을 지정한 것이고
	 * 해당 조인포인트에서는 어드바이스가 동작한다.
	 * 매개변수로 전달한 JoinPoint 객체를 통해 타겟 메소드의 메소드명, 매개변수 값 등의 자세한 정보를
	 * 접근할 수 있다.
	 * 
	 * <포인트컷 표현식>
	 * 수식어: public, private 등 수식어를 명시(생략 가능)
	 * 리턴 타입: 리턴 타입을 명시
	 * 클래스 이름(패키지명 포함) 및 메소드 이름: 클래스 이름과 메소드 이름을 명시
	 * 파라미터(매개변수): 메소드의 파라미터를 명시
	 * " * ": 1개이면서 모든 값이 올 수 있음
	 * " .. ": 0개 이상의 모든 값이 올 수 있음
	 * 
	 * ex)
	 * execution(public Integer com.greedy.section01.advice.*.*(*))
	 * => com.greedy.section01.advice 패키지에 속해 있는 바로 다음 하위 클래스인 파라미터가 1개인 모든 메소드이며
	 *    접근제한자가 public이고 반환형이 Integer인 경우
	 *    
	 * execution(* com.greedy.section01.advice.annotation..*(..))
	 * => com.greedy.section01.advice.annotation패키지 하위 패키지에 속해 있고, 이름이 stu로 시작하는
	 *    파라미터가 0개 이상인 모든 메소드이며 접근제한자와 반환형은 상관 없음
	 */
//	@Before("execution(* com.greedy.section01.advice..*(..))")		  // 포인트 컷
//	@Before("studentBeforePointcut()")
	@Before("StudentPointcut.studentPointcut()")
	public void beforeAttending(JoinPoint joinPoint) {
		System.out.println("========== before attending ==========");
		System.out.println("오늘도 신나게 등원해서 입실 카드를 찍는다.");
		System.out.println("수강생 타입: " + joinPoint.getTarget().getClass());					// 타겟 메소드가 소속된 타겟 클래스의 정보					
		System.out.println("수강생의 행위: " + joinPoint.getSignature());							// 리턴 타입과 매개변수를 포함한 메소드 헤드부
		System.out.println("행위 요약: " + joinPoint.getSignature().getName());					// 메소드의 이름만 반환
		System.out.println("수강생의 열정: " + ((Passion)joinPoint.getArgs()[0]).getScore());		// 메소드 매개변수들을 Object배열 형태로 추출
		System.out.println("======================================");
	}
	
	/* 자주 쓰이는 포인트 컷은 메소드로 따로 작성할 수 있다. */
	@Pointcut("execution(* com.greedy.section01.advice..*(..))")
	private void studentBeforePointcut() {}
}








