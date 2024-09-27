package com.ohgiraffers.section04.paging;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.util.List;

import javax.persistence.EntityManager;

/* 페이징 처리를 손쉽게 처리하기 위해 JPA에서 제공하는 페이징 API를 알아본다.(정렬(ORDER BY) 포함) */
public class Application {

	public static void main(String[] args) {
		
		/*
		 * 반복적이고 지저분하며 지루할 수 있는 페이징 처리용 SQL은 심지어 DBMS에 따라
		 * 각각 문법이 다른 문제점을 안고 있다.
		 * JPA는 이런 페이징을 API를 통해 추상화해서 간단하게 처리할 수 있도록 제공해 준다.
		 * 
		 * setFirstResult(int startPosition): 조회를 시작할 위치(0부터 시작)
		 * setMaxResults(int maxResult): 조회할 데이터 수
		 */
		EntityManager em = getEntityManager();
		
		/* paging API를 활용한 조회 */
		pagingApiMethod(em);
		
		em.close();
	}
	
	private static void pagingApiMethod(EntityManager em) {
		
		/* 
		 * JPQL에서 정렬을 담당하는 orderby절은 ORACLE의 ORDER BY와 다른 것이 없다.
		 *   ORDER BY {필드명 | 필드 서순 [ASC | DESC]}
		 */
		
		/* ResultSet에서 rownum이 5~10까지의 메뉴 조회 */
		String jpql = "SELECT m FROM SECTION04_MENU m ORDER BY m.code DESC";			// 최근 등록된 메뉴부터 조회(메뉴코드 내림차순) 	
		List<Menu> menuList =  em.createQuery(jpql, Menu.class)
								  .setFirstResult(10)		// 내림차순 결과의 6번째 행부터	
								  .setMaxResults(5)			// 5행을 조회
								  .getResultList();
		
		/* 
		 * 실행해 보면 우리는 oracle dialect로 persistence.xml에 설정해 두었기 때문에
		 * 쿼리가 제대로 서브쿼리 형식과 정렬까지 잘 만들어 지는 것을 확인할 수 있다.
		 * (JPA는 물론 그 외의 DBMS에 대해서도 지원하고 있다.) 
		 */
		for (Menu menu : menuList) {
			System.out.println(menu);
		}
	}
}
