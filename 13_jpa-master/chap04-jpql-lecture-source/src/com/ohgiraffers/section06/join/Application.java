package com.ohgiraffers.section06.join;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/* JPQL에서 제공하는 조인을 알아본다. */
public class Application {

	public static void main(String[] args) {
		
		/*
		 * 이미 조인은 앞선 예제들에서 한번씩 다뤄 보았다.
		 * JPQL 조인도 SQL조인과 기능은 같으면서 문법에 있어 약간씩 차이가 있는 정도이다.
		 * 
		 * JPQL 조인은 4가지로 나뉜다.
		 * 1. 내부 조인
		 * 2. 외부 조인
		 * 3. 컬렉션 조인
		 * 4. 세타 조인
		 */
		
		EntityManager em = getEntityManager();
		
		/* 1. 내부 조인 */
//		innerJoinMethod(em);
		
		/* 2. 외부 조인 */
//		outerJoinMethod(em);
		
		/* 3. 컬렉션 조인 */
//		collectionJoinMethod(em);
		
		/* 4. 세타 조인 */
		thetaJoinMethod(em);
		
		em.close();
	}
	
	private static void innerJoinMethod(EntityManager em) {
		
		/* 내부 조인은 INNER JOIN을 사용하지만 INNER는 SQL처럼 생략할 수 있다. */
		
		/* 메뉴 이름과 카테고리 이름 조회 */
//		String jpql = "SELECT m.name, c.name FROM SECTION06_MENUANDCATEGORY m "
//				    + "INNER JOIN m.category c";
		String jpql = "SELECT m.name, c.name FROM SECTION06_MENUANDCATEGORY m "
			        + "JOIN m.category c";
		
		List<Object[]> resultList = em.createQuery(jpql, Object[].class).getResultList();
		
		for (Object[] row : resultList) {
			System.out.println("메뉴명 : " + row[0] + ", 카테고리명 : " + row[1]);
		}
	}
	
	private static void outerJoinMethod(EntityManager em) {
		
		/* 
		 * 외부 조인 역시 SQL과 같기 때문에 LEFT JOIN 혹은 RIGHT JOIN을 적용할 수 있다.
		 * 내부 조인은 카테고리 중에 상위 카테고리에 해당하는 카테고리가 나오지 않았지만 외부 조인으로
		 * 카테고리가 다 나올 수 있도록 해보자.(RIGHT JOIN)
		 */
		String jpql = "SELECT m.name, c.name FROM SECTION06_MENUANDCATEGORY m "
			        + "RIGHT JOIN m.category c";
		
		List<Object[]> resultList = em.createQuery(jpql, Object[].class).getResultList();
		
		for (Object[] row : resultList) {
			System.out.println("메뉴명 : " + row[0] + ", 카테고리명 : " + row[1]);
		}
	}
	
	private static void collectionJoinMethod(EntityManager em) {
		
		/* 컬렉션 조인은 의미상 분류된 것으로 컬렉션을 지니고 있는 엔티티를 기준으로 조인하는 것을 말한다. */
		String jpql = "SELECT c.name, m.name FROM SECTION06_CATEGORYANDMENU c "
			        + "LEFT JOIN c.menuList m";
		
		List<Object[]> resultList = em.createQuery(jpql, Object[].class).getResultList();
		
		for (Object[] row : resultList) {
			System.out.println("메뉴명 : " + row[0] + ", 카테고리명 : " + row[1]);
		}
	}
	
	private static void thetaJoinMethod(EntityManager em) {
		
		/* 
		 * WHERE 절을 사용해서 전혀 관계 없는 엔티티도 조인할 수 있다. 
		 * (세타 조인은 내부 조인만 지원하며 SQL의 CROSS JOIN과 같다.)
		 */
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		Member member = new Member();
        member.setNo(1);
        member.setId("user01");
        member.setPwd("pass01");
        member.setNickname("생갈치쉐이크");
        member.setPhone("010-1234-1234");
        member.setAddress("서울시 서초구");
        member.setEnrollDate(new Date(System.currentTimeMillis()));
        member.setRole("ADMIN");
        member.setStatus("Y");
        
        em.persist(member);
        
        tx.commit();
        
		/* 회원과 메뉴를 조인한다. */
		String jpql = "SELECT a.nickname, b.name FROM SECTION06_MEMBER a, SECTION06_MENUANDCATEGORY b "
					+ "WHERE a.nickname = b.name";
		
		List<Object[]> resultList = em.createQuery(jpql, Object[].class).getResultList();
		
		for (Object[] row : resultList) {
			System.out.println("회원명 : " + row[0] + ", 메뉴명 : " + row[1]);
		}
	}
}
