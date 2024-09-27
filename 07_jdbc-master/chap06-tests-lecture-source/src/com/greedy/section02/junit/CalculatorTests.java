package com.greedy.section02.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/* 단위 테스트용 클래스 */
public class CalculatorTests {
	
	private Calculator calc = null;
	
	/*
	 * 단위 테스트를 위한 어노테이션들
	 * @Test: 단정문이 들어 있고 단위 테스트를 위해 실행 할 메소드에 작성한다.
	 *        (main 메소드가 없이도 메소드를 실행할 수 있다.)
	 *        
	 * @Before: @Test가 작성 된 메소드 호출 이전에 해야하는 준비 작업을 위한 메소드에
	 *          작성한다. 테스트 메소드를 실행하기 전에 먼저 자동으로 실행된다.
	 *          
	 * @After: @Test가 작성된 메소드 호출 이후마다 자동 실행된다.
	 * @Ignore: 해당 메소드는 테스트를 무시한다.
	 */
	
	/*
	 * 대표적인 단정문들
	 * assertArrayEquals(a, b): 배열 a와 b가 일치함을 확인
	 * assertEquals(a, b): 객체 a와 b의 값이 같은지 확인(동등)
	 * assertSame(a, b): 객체 a와 b가 같은 객체임을 확인(동일)
	 * assertTrue(a): a가 참인지 확인
	 * assertNotNull(a): a 객체가 null이 아님을 확인
	 */
	
	/* 1. Calculator 인스턴스가 잘 생성이 되는지 테스트 */
//	@Test
	@Before
	public void setup() {
//		Calculator calc = null;
		calc = new Calculator();
		
//		assertNotNull(calc);		// @Test가 아니면 단정문이 의미가 없다.(결과로 안나옴)
	}
	
	/* 2. sumTwoNumbers 메소드가 정상 기능을 하는지 테스트 */
	/* 2-1. 4와 5를 전달하면 합계 9가 계산되어 반환되는지 확인 */
//	@Test
	@Ignore
	public void testSumTwoNumber_4와_5를_전달하면_합계_9를_반환하는지_확인() {
		int result = calc.sumTwoNumbers(4, 5);
		
//		assertEquals(9, result);		// 결과가 9와 일치하는지 판단
		assertEquals(8, result, 1);		// 결과가 9와 1만큼 차이나는 값(8 또는 10)들도 허용
	}
	
	/* 2-2. 6과 7을 전달하면 합계 13으로 계산되어 반환되는지 확인 */
	@Test
	public void testSumTwoNumber_6과_7을_전달하면_합계_13을_반환하는지_확인() {
		int result = calc.sumTwoNumbers(6, 7);
		
		assertEquals(13, result);
	}
	
	/* 3. 위의 테스트 결과가 모두 통과되면 해당 클래스의 메소드는 신뢰성 있는 메소드임을 확인(증명) */
	@After
	public void afterTest() {
		System.out.println("단위 테스트 완료!");
	}
}






