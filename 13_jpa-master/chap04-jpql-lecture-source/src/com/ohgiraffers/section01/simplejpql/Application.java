package com.ohgiraffers.section01.simplejpql;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/* JPQL의 기본적인 문법(DISTINCT와 기본 연산자 포함)과 조회를 위한 기초적인 사용법을 알아본다. */
public class Application {

	public static void main(String[] args) {
		
		/*
		 * JPQL(Java Persistence Query Language)
		 * - 엔티티 객체를 중심으로 개발할 수 있는 객체지향 쿼리이다.
		 * - JPQL은 SQL보다 간결하며 DBMS에 상관없이 개발이 가능하다.
		 *   (방언을 통해 해결되며 해당 DBMS에 맞는 SQL실행, SQL을 추상화해서 특정 데이터베이스 SQL에 의존하지 않는다.)
		 * - JPQL은 find()메소드를 통한 조회와 다르게 항상 데이터베이스에 SQL을 실행해서 결과를 조회한다.
		 *   (영속성 컨텍스트에 이미 존재하면 기존 엔티티를 반환하고 조회한 것은 버린다.)
		 * - JPQL은 엔티티 객체를 대상으로 쿼리를 질의하고 SQL은 데이터베이스의 테이블을 대상으로 질의한다.
		 * - JPQL은 결국 SQL로 변환된다.
		 * 
		 * - JPA의 공식 지원 기능 -
		 *   Criteria 쿼리(Criteria Query): JPQL을 편하게 작성하도록 도와주는 API
		 *   네이티브 SQL(Native SQL): JPA에서 JPQL 대신 직접 SQL을 사용
		 * 
		 * - JPA의 비공식 지원 기능 - 
		 * 	 QueryDSL: Criteria 쿼리처럼 JPQL을 편하게 작성하도록 도와주는 빌더 클래스 모음(비표준 오픈소스 프레임워크)
		 *   JDBC 직접 사용 또는 Mybatis같은 SQL 매퍼 프레임워크: JDBC를 직접 작업해서 사용
		 */
		
		/*
		 * JPQL의 기본 문법
		 * 1. CRUD
		 * - select:
		 *     select_절
		 *     from_절
		 *     [where_절]
		 *     [groupby_절]
		 *     [having_절]
		 *     [orderby_절]
		 *   
		 * - insert: insert문은 EntityManager가 제공하는 persist()메소드를 사용하면 되서 따로 없다.
		 *   
		 * - update:
		 *     update_절
		 *     [where_절]
		 *   
		 * - delete:
		 *     delete_절
		 *     [where_절]
		 * 
		 * 2. 특징
		 * - (주의!)엔티티와 속성은 대소문자를 구분한다
		 * - SELECT, from과 같은 JPQL의 기본 키워드들은 대소문자를 구분하지 않는다.(관례상 대문자를 써서 구분하기 좋게 하자)
		 * - 엔티티명은 클래스명이 아니라 엔티티명이다.
		 *   (우리는 하나의 프로젝트에서 같은 이름의 엔티티 클래스를 사용해서 엔티티 명을 각각 다르게 주고 있지만
		 *    기본값인 클래스명을 엔티티 명으로 사용하는 것을 추천)
		 * - JPQL은 별칭을 필수로 사용해야 하며 별칭 없이 작성하면 에러가 발생한다.
		 */
		
		EntityManager em = getEntityManager();
		
		/* 기본 문법 및 쿼리 실행 */
		/* 1. 단일 행 조회 */
		/* 1-1. 단일 행 단일 열 조회 */
//		selectSingleMethod1(em);
		
		/* 1-2. 단일 행 다중 열 조회 */
//		selectSingleMethod2(em);
		
		/* 2. 다중 행 조회 */
//		selectMultiMethod(em);
		
		/* 3. 연산자 */
		operatorMethod(em);
		
		em.close();
	}
	
	private static void selectSingleMethod1(EntityManager em) {
		/* 
		 * JPQL 사용 방법
		 * 1. 작성한 JPQL(문자열)을 em.createQuery메소드로를 통해 쿼리 객체로 만든다.
		 *    쿼리 객체는 TypdeQuery와 Query 두가지가 있다.
		 *    - TypedQuery: 반환할 타입을 명확하게 지정하는 방식일 때 사용(쿼리 객체의 메소드 실행 결과로 지정한 타입이 반환 됨) 
		 *    - Query: 반환할 타입을 명확하게 지정할 수 없을 때 사용(쿼리 객체의 메소드 실행 결과로 Object 혹은 Object[]이 반환 됨)
		 *    
		 * 2. 쿼리 객체에서 제공하는 메소드 getSingleResult() 혹은 getResultList()를 호출해서
		 *    쿼리를 실행하고 데이터베이스를 조회한다.
		 *    - getSingleResult(): 결과(행)가 정확히 하나일 때 사용(결과가 없거나 하나보다 많으면 예외가 발생한다.)
		 *    - getResultList(): 결과(행)가 2개 이상일 때 사용하며 컬렉션을 반환한다.(결과가 없으면 빈 컬렉션을 반환한다.)
		 *    
		 * (주의!)Query가 제공하는 getSingleResult()나 getResultList()는 Object를 반환하지만 조회 결과가 여러 스칼라값들의 모음일 경우
		 * (Object[])로 다운 캐스팅 해야 한다.(section03의 scalar프로젝션과 section05의 그룹함수결과들을 한번에 조회하는 곳에서 이 부분은 확인할 수 있다.)
		 */
		
		/* 7번 메뉴 한 개 조회(getSingleResult()) */
		/* CATEGORY_CODE 조회 */
		String jpql = "SELECT SECTION01_MENU.code FROM SECTION01_MENU WHERE SECTION01_MENU.code = 7";		// IllegalArgumentException 발생!
//		String jpql = "SELECT m.code FROM SECTION01_MENU as m WHERE m.code = 7";	// 반드시 식별자를 써야 한다.
		
		/* TypedQuery 사용 시 */
		TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
		int menuCode = query.getSingleResult();
		
		/* Query 사용 시 */
//		Query query = em.createQuery(jpql);		// 변환할 타입을 명시하지 않는다.
//		Object menuCode = query.getSingleResult();
		
		System.out.println(menuCode);
	}

	private static void selectSingleMethod2(EntityManager em) {
		
		/* MENU_NAME 조회 */
		String jpql = "SELECT m FROM SECTION01_MENU as m WHERE m.code = 7";	
		TypedQuery<Menu> query = em.createQuery(jpql, Menu.class);
		Menu menu = query.getSingleResult();
		
		System.out.println(menu);
	}

	private static void selectMultiMethod(EntityManager em) {
		
		/* 모든 메뉴 조회(getResultList()) */
//		String jpql = "SELECT m FROM SECTION01_MENU as m";
////		
////		/* TypedQuery 사용 시 */
//		TypedQuery<Menu> query = em.createQuery(jpql, Menu.class);
//		List<Menu> menuList = query.getResultList();
//		
//		for (Menu menu : menuList) {
//			System.out.println(menu);
//		}
		
//		/* Query 사용 시 */
////		Query query = em.createQuery(jpql);
////		List<Object> menuList = query.getResultList();
////		
//		for (Object o : menuList) {
//			System.out.println((Menu)o);
//		}
		
		/* DISTINCT 사용해 보기 */
		String jpql2 = "SELECT DISTINCT m.categoryCode FROM SECTION01_MENU m ORDER BY m.categoryCode DESC";
		TypedQuery<Integer> query2 = em.createQuery(jpql2, Integer.class);
		List<Integer> distinctCategoryCodeList = query2.getResultList();
		
		System.out.println("DINSTINCT 적용해 보기 : ");
		
		for (Integer distinctCate : distinctCategoryCodeList) {
			System.out.println(distinctCate);
		}
	}

	private static void operatorMethod(EntityManager em) {
		
		/* 연산자는 SQL과 다르지 않으므로 종류 설명 및 몇 가지만 진행해 본다. */
		/*
		 * 연산자 우선순위 및 종류
		 * 1. 경로 탐색 연산(.)
		 * 2. 수학 연산: +, -(단항 연산자), *, /, +, -
		 * 3. 비교 연산: =, >, >=, <=, <>(다름은 =!나 =^는 적용 안됨), 
		 *            [NOT] BETWEEN, [NOT] LIKE, [NOT] IN,
		 *            IS [NOT] NULL, IS [NOT] EMPTY, [NOT] MEMBER [OF], [NOT] EXISTS
		 * 4. 논리 연산: NOT, AND, OR
		 */
		
		/* IN과 LIKE만 살펴보자 */
		/* 6번 카테고리와 10번 카테고리 메뉴만 조회 */
//		String jpql1 = "SELECT m FROM SECTION01_MENU m WHERE m.categoryCode IN (6, 10)";
//		List<Menu> menuList1 = em.createQuery(jpql1, Menu.class).getResultList();
//		
//		for (Menu menu : menuList1) {
//			System.out.println(menu);
//		}
		
		/* '나'가 들어가는 이름의 메뉴만 조회 */
		String jpql2 = "SELECT m FROM SECTION01_MENU m WHERE m.name LIKE '%나%'";
		List<Menu> menuList2 = em.createQuery(jpql2, Menu.class).getResultList();
		
		for (Menu menu : menuList2) {
			System.out.println(menu);
		}
	}
}















