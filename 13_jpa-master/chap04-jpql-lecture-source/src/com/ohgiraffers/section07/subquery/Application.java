package com.ohgiraffers.section07.subquery;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.util.List;

import javax.persistence.EntityManager;

/* JPQL의 서브쿼리에 대해 알아본다. */
public class Application {

	public static void main(String[] args) {
		
		/*
		 * JPQL도 SQL처럼 서브 쿼리를 지원한다.
		 * 하지만 SELECT, FROM절에서는 사용할 수 없고 WHERE, HAVING절에서만 사용이 가능하다. 
		 */
		
		EntityManager em = getEntityManager();
		
		/* WHERE절에서 서브쿼리로 조회하기 */
//		basicSubqueryMethod(em);
		
		/* 서브쿼리 함수 활용해 보기 */
		subqueryFunctionMethod(em);
		
		em.close();
	}
	
	private static void basicSubqueryMethod(EntityManager em) {
		
		/* 메뉴들 중 평균 가격보다 높은 메뉴만 조회 */
		
		/* 조회 결과를 확인하기 위해 평균 가격부터 조회하자(서브쿼리 먼저 작성) */
		String jpql1 = "SELECT AVG(a.price) FROM SECTION07_MENUANDCATEGORY a";
		Double avg = em.createQuery(jpql1, Double.class).getSingleResult();
		System.out.println("메뉴들의 평균 가격 : " + avg);
		
		/* WHERE절에 서브쿼리를 활용하여 평균가보다 큰 가격의 메뉴를 조회하자 */
		String jpql2 = "SELECT a FROM SECTION07_MENUANDCATEGORY a "
				    + "WHERE a.price > (SELECT AVG(b.price) FROM SECTION07_MENUANDCATEGORY b)";
		List<MenuAndCategory> menuList = em.createQuery(jpql2, MenuAndCategory.class).getResultList();
		
		for (MenuAndCategory menu : menuList) {
			System.out.println(menu);
		}
	}
	
	private static void subqueryFunctionMethod(EntityManager em) {
		
		/* 
		 * 단일행 단일열의 결과가 나오는 서브쿼리가 아닌 이상 서브쿼리 함수를 활용해서 조회해야 한다.
		 * 서브쿼리 함수의 종류는 다음과 같다.(SQL과 다르지 않음)
		 * [NOT] EXISTS (서브쿼리)
		 * {ALL | ANY | SOME} (서브쿼리)
		 * [NOT] IN (서브쿼리)
		 */
		
		/* 상위 카테고리 '식사'(REF_CATEGORY_CODE가 1)인 메뉴 조회하기(EXISTS) */
//		String jpql1 = "SELECT a FROM SECTION07_MENUANDCATEGORY a "
//				     + "WHERE EXISTS (SELECT b FROM a.category b WHERE b.refCategoryCode = 1)";
//		List<MenuAndCategory> menuList1 = em.createQuery(jpql1, MenuAndCategory.class).getResultList();
//		
//		for (MenuAndCategory menu : menuList1) {
//			System.out.println(menu);
//		}
		
		/* 메뉴가 존재하지 않는 카테고리 조회하기(ALL) */
//		String jpql2 = "SELECT a FROM SECTION07_CATEGORYANDMENU a "
//			     	 + "WHERE a.code <> ALL (SELECT b.category.code FROM SECTION07_MENUANDCATEGORY b)";
//		List<CategoryAndMenu> categoryList = em.createQuery(jpql2, CategoryAndMenu.class).getResultList();
//		
//		for (CategoryAndMenu cate : categoryList) {
//			System.out.println(cate);
//		}
	
		/* 7000원인 갈릭미역파르페 메뉴 조회하기(IN) */
		String jpql3 = "SELECT a FROM SECTION07_MENUANDCATEGORY a "
			         + "WHERE (a.name, a.price) IN "
			         + "(SELECT b.name, b.price FROM SECTION07_MENUANDCATEGORY b "
			         + "WHERE b.name = '갈릭미역파르페' AND b.price = 7000)";
		List<MenuAndCategory> menuList2 = em.createQuery(jpql3, MenuAndCategory.class).getResultList();
		
		for (MenuAndCategory menu : menuList2) {
			System.out.println(menu);
		}
	}
	
	

}
