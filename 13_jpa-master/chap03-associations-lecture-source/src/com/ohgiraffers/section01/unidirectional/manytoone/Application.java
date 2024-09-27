package com.ohgiraffers.section01.unidirectional.manytoone;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/* 단방향 연관관계에서 다대일 일 때 어떻게 매핑해야 하는지에 대해 알아보자 */
public class Application {

	/*
	 * 객체 관계 매핑(ORM)에서 가장 어려운 것이 객체 연관관계와 테이블 연관관계를 매핑하는 일이다.
	 * 객체 연관관계는 참조(주소)를 사용해서 맺고 테이블은 외래 키를 사용해서 관계를 맺는다.
	 * 이번 chap03프로젝트에서는 객체의 참조와 테이블의 외래 키를 매핑하는 것이 주된 내용이다.
	 * 
	 * 연관관계를 매핑하기 위한 키워드
	 *  방향(Direction): 방향은 단방향과 양방향이 있다. 객체 관계에서는 한번에 한 객체가 다른 한쪽을 참조하므로
	 *                  단방향과 양방향이라는 개념이 따로 있다.
	 *                  메뉴 -> 카테고리, 카테고리 -> 메뉴이면 단방향으로 각각 참조하면서 서로가 서로를 참조하니
	 *                  양방향이라고도 볼 수 있다.
	 *                  하지만 테이블 관계에서는 항상 양방향이다.(외래키를 통한 관계만 맺으면 서로 조인할 수 있으므로)
	 *  다중성(Multiplicity): 연관관계가 있는 객체 관계 혹은 테이블 관계에서 실제로 연관을 가지는(매핑되는)
	 *                      객체의 수(객체 관계) 또는 행(테이블 관계)의 수에 따라 1:1, 1:N. N:1, N:N이라는
	 *                      다중성을 가진다.
	 *  연관관계의 주인(Owner): 외래 키 관리자이며 객체를 양방향 연관 관계로 만들면 연관관계의 주인을 정해야 한다.                  
	 */
	public static void main(String[] args) {
		
		/* 메뉴 -> 카테고리의 단방향 관계(다대일, N:1)를 먼저 살펴보자. */
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		/* 조회 */
		selectMethod(em);
		
		try {
			
		/* 저장 */
//		tx.begin();
//		insertMethod(em);
//		tx.commit();
		
		/* 수정 */
//		tx.begin();
//		updateMethod(em);
//		tx.commit();
		
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
        
		em.close();
	}
	
	private static void selectMethod(EntityManager em) {
		
		/* 
		 * 연관관계가 있는 엔티티를 조회하는 방법은 크게 2가지가 있다.
		 * 1. 객체 그래프 탐색(객체 연관관계를 사용한 조회)
		 * 2. 객체지향 쿼리 사용(JPQL)
		 */
		
		/* 아래 두 가지 방법으로 15번 메뉴의 카테고리 이름을 조회해 보자. */
		/* 1. 객체 그래프 탐색 */
		/* 객체를 통해 연관된 엔티티를 조회하는 것을 객체 그래프 탐색이라 한다. */
		MenuAndCategory menu = em.find(MenuAndCategory.class, 1);
		Category category = menu.getCategory();
		System.out.println("해당 메뉴가 가진 카테고리의 상세 정보: " + category);
		System.out.println("카테고리 이름: " + category.getName());
		
		/* 2. 객체지향 쿼리(JPQL) 사용(JPQL 구경 정도로 생각할 것) */
		/* 
		 * 객체지향 쿼리인 JPQL도 조인을 SQL과는 다소 문법적 차이가 있지만 지원한다.
		 * (JPQL은 이후 chapter4(이후 하게 될 프로젝트)에서 하게 될 예정이니 지금은 구경만)
		 * ((주의!) FROM 다음 오게 되는 것은 테이블이 아닌 반드시 엔티티의 이름을 써야 한다.)
		 */
//		String jpql = "SELECT c.name FROM SECTION01_MENUANDCATEGORY_MANYTOONE m JOIN m.category c WHERE m.code = 15";
//		String category = em.createQuery(jpql, String.class)
//                			.getSingleResult();
//		System.out.println("카테고리 이름: " + category);
		
		/* JPQL은 객체(엔티티)를 대상으로 하며 SQL보다 간결하고 여러 행의 결과가 나오는 것에 대해서도 처리할 수 있다.(바인딩 처리도 가능하다는 걸 한번 보여주기) */
//		String jpql = "SELECT m FROM SECTION01_MENUANDCATEGORY_MANYTOONE m JOIN m.category c WHERE c.name = :categoryName";	// :(콜론)은 파라미터를 바인딩 하는 문법
//		
//		List<MenuAndCategory> resultList = em.createQuery(jpql, MenuAndCategory.class)
//				                                .setParameter("categoryName", "한식")
//				                                .getResultList();
//		
//		for (MenuAndCategory m : resultList) {
//			System.out.println(m.getName());
//		}
	}

	private static void insertMethod(EntityManager em) {
		
		/* 새로운 카테고리 추가 */
		Category newCate = new Category(13, "군것질", 3);
		em.persist(newCate);
		
		/* 새로운 카테고리인 군것질용 메뉴 추가 */
		MenuAndCategory newMenu = new MenuAndCategory(32, "겨자맛달고나", 2000, newCate, "Y");
		em.persist(newMenu);
	}

	private static void updateMethod(EntityManager em) {
		
		/* 기존에 추가한 메뉴의 카테고리를 '동양'(카테고리 코드 11번)으로 바꿔보자 */
		Category selectedCategory = em.find(Category.class, 11);
				
		MenuAndCategory selectedMenu1 = em.find(MenuAndCategory.class, 31);
		selectedMenu1.setCategory(selectedCategory);
		
		/* 연관관계를 제거할 수도 있다 (메뉴가 카테고리와의 연관관계를 제거함을 의미) 
		 * (기존 TBL_MENU 테이블의 CATEGORY_CODE 컬럼이 NOT NULL제약조건이 있어 에러가 날 수 있으니
		 *  ALTER TABLE TBL_MENU MODIFY CATEGORY_CODE NULL;
		 *  를 작성해 보고 진행도록 한다.)
		 */
		MenuAndCategory selectedMenu2 = em.find(MenuAndCategory.class, 31);
		selectedMenu2.setCategory(null);
	}
}



