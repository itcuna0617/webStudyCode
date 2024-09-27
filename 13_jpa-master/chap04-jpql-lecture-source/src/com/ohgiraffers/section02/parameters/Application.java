package com.ohgiraffers.section02.parameters;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/* JPQL의 파라미터 바인딩에 대해 알아본다. */
public class Application {

	public static void main(String[] args) {
		
		/*
		 * 파라미터를 바인딩하는 방법
		 * 1. 이름 기준 파라미터(named parameters)
		 * 	  ':' 다음에 이름 기준 파라미터를 지정한다. 
		 * 2. 위치 기준 파라미터(positional parameters)(JDBC는 위치기준 파라미터만 지원함)
		 * 	  '?' 다음에 값을 주고 위치 값은 1부터 시작한다.
		 */
		EntityManager em = getEntityManager();
		
		/* 1. 이름 기준 파라미터 */
//		namedParamMethod(em);
		
		/* 2. 위치 기준 파라미터 */
		positionalParamMethod(em);
		
		em.close();
	}
	
	private static void namedParamMethod(EntityManager em) {
		String menuNameParam = "한우딸기국밥";
		
//		TypedQuery<Menu> query =
//				em.createQuery("SELECT m FROM SECTION02_MENU m WHERE m.name = :menuName", Menu.class);
//
//		query.setParameter("menuName", menuNameParam);
//		List<Menu> resultList = query.getResultList();
		
		/* 위의 과정을 한번에 이어서 쓸 수도 있다.(TypedQuery 변수로 따로 안담고 해보기) */
		List<Menu> resultList = 
				em.createQuery("SELECT m FROM SECTION02_MENU m WHERE m.name = :menuName", Menu.class)
				  .setParameter("menuName", menuNameParam)
				  .getResultList();	// 메뉴 이름은 같은 값이 있을 수 있으므로 다중행 조회
		
		for (Menu menu : resultList) {
			System.out.println(menu);
		}
	}

	private static void positionalParamMethod(EntityManager em) {
		String menuNameParam = "한우딸기국밥";
		
		List<Menu> resultList =
				em.createQuery("SELECT m FROM SECTION02_MENU m WHERE m.name = ?1 AND m.price = ?2", Menu.class)
				  .setParameter(1, menuNameParam)
				  .setParameter(2, 20000)
				  .getResultList();

		for (Menu menu : resultList) {
			System.out.println(menu);
		}
	}
}
