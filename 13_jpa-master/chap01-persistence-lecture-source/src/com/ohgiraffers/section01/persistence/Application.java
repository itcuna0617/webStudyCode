package com.ohgiraffers.section01.persistence;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/* JPA 튜토리얼 사이트: http://www.java2s.com/Tutorials/Java/JPA/index.htm */
/* JPA 애플리케이션을 개발하기 위한 설정 및 간단한 CRUD를 진행해 본다. */
public class Application {

	public static void main(String[] args) {
		
		/* JPA 애플리케이션을 개발하는 것은 엔티티 1. 매니저 설정, 2. 트랜잭션 관리, 3. 비지니스 로직으로 나뉜다. */
		
		/* 1. 엔티티 매니저 설정 */
		/* 엔티티 매니저 팩토리 생성 (매니저 팩토리를 만드는 과정은 비용이 굉장히 많이 든다.) */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        
        /* 엔티티 매니저 생성 (데이터 베이스와 CRUD를 할 수 있다.) */
        EntityManager em = emf.createEntityManager();

        /* 2. 트랜잭션 관리 */
        /* 
         * 트랜잭션 처리를 위한 인터페이스 구현객체 생성 
         * (엔티티 매니저는 데이터베이스 연결이 꼭 필요한 시점까지 커넥션을 얻지 않는다.
         *  보통 트랜잭션을 시작할 때 커넥션을 획득한다.)
         */
        EntityTransaction tx = em.getTransaction();
        
        try {
	        tx.begin();			// 트랜잭션 시작
	        
	        logic(em);			// 비지니스 로직

	        tx.commit();		// 트랜잭션 
        } catch (Exception e) {
        	e.printStackTrace();
        	tx.rollback();		// 예외 발생시 롤백 (이렇게 예외처리 후 롤백을 해야하지만 이후 예제부터는 따로 처리 안할 예정)
        } finally{
        	
        	/* 엔티티 매니저와 엔티티 매니저 팩토리는 반드시 종료해야 한다. */
        	em.close();
        }
        emf.close();
	}
	
	public static void logic(EntityManager em) {
        
		/* 3. 비지니스 로직 */
        /* 3-1. 메뉴 코드로 메뉴 한 개 조회하기 */
        /* 하나 이상 조회는 JPQL에서 다룰 예정 */
        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴 번호를 하나 입력 하시오 : ");
        int code = sc.nextInt();
        Menu selectedMenu = em.find(Menu.class, code);
        
        System.out.println(selectedMenu);
//        
//        /* 3-2. 조회 한 메뉴 수정하기 (따로 update 메소드가 없다.) */
//        selectedMenu.setPrice(6000);		// JPA에는 엔티티가 변경되었는지를 추적하는 기능이 있다.(변경이 감지되면 UPDATE 구문 생성)
        
        /* 3-3. 메뉴 한 개 추가하기 */
//        Menu insertMenu = new Menu();
//        insertMenu.setCode(27);
//        insertMenu.setName("초코맛우동");
//        insertMenu.setCategoryCode(6);
//        insertMenu.setOrderableStatus("Y");
//        
//        em.persist(insertMenu);
        
        /* 3-4. 추가 했던 메뉴 수정하기  */
//        insertMenu.setPrice(6000);		
        
        /* 3-5. 추가 했던 메뉴 삭제하기 */
//        em.remove(insertMenu);
	}
}
















