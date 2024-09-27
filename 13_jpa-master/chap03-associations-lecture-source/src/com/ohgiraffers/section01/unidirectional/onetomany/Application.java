package com.ohgiraffers.section01.unidirectional.onetomany;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/* 단방향 연관관계에서 다대일 일 때 어떻게 매핑해야 하는지에 대해 알아보자 */
public class Application {

	public static void main(String[] args) {
		
		/* 카테고리 -> 메뉴의 단방향 관계(일대다, 1:N)를 먼저 살펴보자. */
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
		
		/* 기타((카테고리 코드 10번) 카테고리의 메뉴를 조회해 보자. */
		CategoryAndMenu category = em.find(CategoryAndMenu.class, 4);
		List<Menu> menuList = category.getMenuList();
		
		for (Menu menu : menuList) {
			System.out.println(menu);
		}
	}

	private static void insertMethod(EntityManager em) {
		
		/* 새로운 카테고리 추가 */
		Category newCate = new Category(14, "인도식", 1);
		em.persist(newCate);
	}

	private static void updateMethod(EntityManager em) {
		
		/* 기존에 추가한 카테고리를 '남미식'으로 바꿔보자 */
		Category selectedCategory = em.find(Category.class, 14);
				
		selectedCategory.setName("남미식");
	}
}
