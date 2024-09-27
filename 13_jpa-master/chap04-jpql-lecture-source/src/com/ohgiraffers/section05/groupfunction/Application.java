package com.ohgiraffers.section05.groupfunction;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/* 
 * 함수 자체는 간단하므로 기존에 배운 내용(시퀀스, 연관관계, 복합키설정)을 활용하여
 * JPQL의 그룹함수(집합함수)에 대해 알아보자.
 */
public class Application {

	public static void main(String[] args) {
		
		/* 
		 * JPQL의 그룹함수는 COUNT, MAX, MIN, SUM, AVG이며 SQL의 그룹함수와 별반 차이가 없다.
		 * 하지만, 주의해야 할 사항들이 있다.
		 * 1. NULL값은 무시된다.
		 * 2. DISTINCT를 집합 함수 안에 사용해서 중복된 값을 제거하고 구할 수 있다.
		 * 3. DISTINCT를 COUNT에서 사용할 때는 임베디드 타입은 지원하지 않는다.
		 * 4. 값이 없는 상태에서 COUNT를 제외한 그룹함수를 사용하면 NULL이 되고 COUNT만 0이 된다.
		 */
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		/* 그룹함수를 활용한 조회(MAX, SUM) */
//		groupFunctionMethod1(em);
		
		/* 그룹함수를 활용한 조회(AVG, COUNT, MIN) */
//		groupFunctionMethod2(em);
		
		/* 그룹함수를 활용한 조회(GROUP BY, HAVING) */
		groupFunctionMethod3(em);
		
		tx.commit();
		
		em.close();
	}
	
	private static void groupFunctionMethod1(EntityManager em) {

		/* 
		 * OrderAndOrderMenu, MenuAndOrderMenu, OrderMenuAndOrder를 만들고 각각의 양방향 관계,
		 * 시퀀스 설정, 복합키 설정(@IdClass방식)까지 작성 
		 */
		
		/* 두 건의 주문을 하고 그룹함수를 연습할 수 있는 예제를 활용해 보자. */
		
		/* 1. 우선 주문 한 건이 발생하는 것에 대해 작성해 보자 */
		OrderAndOrderMenu oom = new OrderAndOrderMenu();
		oom.setDate(new SimpleDateFormat("yy/MM/dd").format(new java.util.Date(System.currentTimeMillis())));
		oom.setTime(new SimpleDateFormat("hh/mm/ss").format(new java.util.Date(System.currentTimeMillis())));
		
		em.persist(oom);	
		
		/* 2. 지금 발생한 주문 코드를 가져온다.(MAX) */
		String jpql1 = "SELECT MAX(o.code) FROM SECTION05_ORDERANDORDERMENU o";
		int orderCode = em.createQuery(jpql1, Integer.class).getSingleResult();
		System.out.println(orderCode);
		
		oom.setCode(orderCode); 			// 시퀀스가 발생시킨 주문 번호를 주문 객체에 담는다.
		
		/* 3. 메뉴 두 개를 주문한다.(8번 메뉴 3개와 9번 메뉴 2개를 주문) */
		/* 8번 메뉴 조회*/
		MenuAndOrderMenu menu1 = em.find(MenuAndOrderMenu.class, 18);
		
		/* 9번 메뉴 조회*/
		MenuAndOrderMenu menu2 = em.find(MenuAndOrderMenu.class, 14);
		
		/* 조회한 메뉴들을 주문 수량과 함께 주문한 메뉴로 등록한다. */
		OrderMenuAndOrderAndMenu omo1 = new OrderMenuAndOrderAndMenu();
		omo1.setOrder(oom);
		omo1.setMenuCode(menu1);
		omo1.setOrderAmount(2);		// 3개 주문
		
		em.persist(omo1);
		
		
		OrderMenuAndOrderAndMenu omo2 = new OrderMenuAndOrderAndMenu();
		omo2.setOrder(oom);
		omo2.setMenuCode(menu2);
		omo2.setOrderAmount(3);		// 2개 주문
		
		em.persist(omo2);
		
		/* 4. 방금 주문한 메뉴의 총 금액(SUM)을 구한다. */
		String jpql2 = "SELECT SUM(b.price * a.orderAmount) FROM SECTION05_ORDERMENUANDORDERANDMENU a "
				     + "JOIN a.menuCode b "
				     + "WHERE a.orderCode = " + orderCode;
				     
		
		long sum = em.createQuery(jpql2, Long.class).getSingleResult();
		System.out.println("방금 주문한 주문 총 합 : " + sum);
		
		oom.setTotalOrderPrice((int)sum);
		
	}
	
	private static void groupFunctionMethod2(EntityManager em) {
		
		/* 메뉴들의 평균 가격(AVG), 메뉴 개수(COUNT), 메뉴들 중 최소 메뉴 금액(MIN)을 한번에 조회 */
		String jpql = "SELECT AVG(m.price), COUNT(m), MIN(m.price) FROM SECTION05_MENUANDORDERMENU m";
		
		/* 따로 세개의 스칼라 값들을 받아줄 객체를 만들지 않고 Object[]로 받아주자.(Object형으로 메소드는 반환하지만 실제로는 Object[]로 처리되어 반환된다.) */
		Object[] row = (Object[])em.createQuery(jpql).getSingleResult();		// getSingleResult()는 
		
		System.out.println(row[0]);		// AVG의 결과값
		System.out.println(row[1]);		// COUNT의 결과값
		System.out.println(row[2]);		// MIN의 결과값
	}

	private static void groupFunctionMethod3(EntityManager em) {
		
		/* GROUP BY와 HAVING은 그룹을 묶고 그룹에 대한 필터링 역할(조건처리)을 할 수 있다. */
		
		/* 
		 * 주문별 합계를 구해보자.(여기서는 스칼라 값들을 받아줄 용도의 객체인 OrderSum를 만들어 new 명령어로 조회하자.)
		 * (주의!)우리는 a.orderCode를 단순히 code번호라고 생각할 수 있지만 객체 모델에서 현재 우리가 접근한
		 * 것은 실제로 OrderAndOrderMenu이다. 따라서 new명령어를 사용해 조회 결과를 담을 객체인 OrderSum의
		 * 필드 자료형에 유의해야 한다.
		 */
		
		/* 우선 주문별 주문메뉴 총합 금액을 조회하자(GROUP BY) */
//		String jpql1 = "SELECT new com.ohgiraffers.section05.groupfunction.OrderSum(a.orderCode, SUM(b.price * a.orderAmount)) FROM SECTION05_ORDERMENUANDORDERANDMENU a "
//			        + "JOIN a.menuCode b "			// (주의!)하나의 jpql문장을 늘려나갈 때 띄어쓰기를 포함해야 할 경우 반드시 포함해 주어야 한다.
//				    + "GROUP BY a.orderCode "
//			        + "ORDER BY 1";
//		
//		List<OrderSum> eachOrderSum = em.createQuery(jpql1, OrderSum.class).getResultList();
//
//		for (OrderSum orderSum : eachOrderSum) {
//			System.out.println("주문코드 : " + orderSum.getOrderCode().getCode());		// 코드만 확인하기 위해 주문 코드용 객체인 OrderAndOrderMenu에서 코드만 접근하여 조회 
//			System.out.println("주문별 주문 합계 : " + orderSum.getOrderSum());
//		}
		
		/* 주문합계가 70000원 이상 주문에 대해서만 조회하자(HAVING) */
		String jpql2 = "SELECT new com.ohgiraffers.section05.groupfunction.OrderSum(a.orderCode, SUM(b.price * a.orderAmount)) FROM SECTION05_ORDERMENUANDORDERANDMENU a "
		        + "JOIN a.menuCode b "
			    + "GROUP BY a.orderCode "
		        + "HAVING SUM(b.price * a.orderAmount) >= 70000 "
		        + "ORDER BY 1";
	
		List<OrderSum> eachOrderSumAfter11 = em.createQuery(jpql2, OrderSum.class).getResultList();
	
		for (OrderSum orderSum : eachOrderSumAfter11) {
			System.out.println("주문코드 : " + orderSum.getOrderCode().getCode());		// 코드만 확인하기 위해 주문 코드용 객체인 OrderAndOrderMenu에서 코드만 접근하여 조회 
			System.out.println("주문별 주문 합계 : " + orderSum.getOrderSum());
		}
	}
}







