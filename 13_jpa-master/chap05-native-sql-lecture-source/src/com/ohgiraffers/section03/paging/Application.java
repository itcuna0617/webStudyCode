package com.ohgiraffers.section03.paging;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.util.List;

import javax.persistence.EntityManager;

/* 네이티브 SQL의 페이징 처리 API를 알아본다. */
public class Application {

	public static void main(String[] args) {
		
		/* JPQL처럼 네이티브 SQL도 JPQL API를 그대로 사용해서 다른 점이 없다. */
		EntityManager em = getEntityManager();
		
		/* paging API를 활용한 조회 */
		nativePagingApiMethod(em);
		
		em.close();
	}
	
	private static void nativePagingApiMethod(EntityManager em) {
		
		String sql = "SELECT A.* FROM TBL_MENU A ORDER BY 1 DESC"; 	
		List<Menu> menuList =  em.createNativeQuery(sql, Menu.class)
								  .setFirstResult(5)		// 내림차순 결과의 6번째 행부터	
								  .setMaxResults(5)			// 5행을 조회
								  .getResultList();
		
		for (Menu menu : menuList) {
			System.out.println(menu);
		}
	}
}
