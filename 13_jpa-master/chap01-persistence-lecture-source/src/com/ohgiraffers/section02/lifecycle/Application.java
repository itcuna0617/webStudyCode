package com.ohgiraffers.section02.lifecycle;

import static com.ohgiraffers.section02.lifecycle.Template.getEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/* 영속성 컨텍스트가 관리하는 엔티티 객체의 생명주기를 알아보자. */
public class Application {

	public static void main(String[] args) {
		
		/* 엔티티 매니저가 매번 새로 생성되는지 확인하기 */
		System.out.println(getEntityManager());
		System.out.println(getEntityManager());
		System.out.println(getEntityManager());
		System.out.println(getEntityManager());
		System.out.println(getEntityManager());
		
		/* 
		 * 영속성 컨텍스트(persistence context)
		 * 엔티티 매니저가 엔티티를 저장하는 공간으로 엔티티를 보관하고 관리한다. 
		 * (엔티티 매니저가 생성될 때 하나의 영속성 컨텍스트가 만들어 진다.)
		 * 
		 * 엔티티의 생명주기
		 * 비영속(new/transient): 영속성 컨텍스트와 전혀 관계가 없는 상태
		 * 영속(managed): 영속성 컨텍스트에 저장된 상태
		 * 준영속(detached): 영속성 컨텍스트에 저장되었다가 분리된 상태
		 * 삭제(removed): 삭제된 상태
		 * 병합(merge): 준영속 상태인 엔티티가 다시 영속상태로 변경된 상태
		 */
		
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		/* 1. 비영속 */
		/* 객체만 생성하면 영속성 컨텍스트나 데이터 베이스와 관련 없는 비영속 상태이다.(가격 제외 insert) */
		Menu menu = new Menu();
        menu.setCode(31);
        menu.setName("메론죽");
        menu.setCategoryCode(4);
        menu.setOrderableStatus("N");
        
        tx.begin();
        
        /* 2. 영속 */
        /* 
         * 엔티티 매니저가 영속성 컨텍스트에 저장하면 영속성 컨텍스트가 엔티티를 관리하게 되고 이를 영속 상태라고 한다. 
         * (em.find()나 JPQL을 사용한 조회도 영속상태가 된다.) 
         */
        em.persist(menu);		 
        
        /* 3. 준영속 */
        /* 
         * 영속성 컨텍스트가 관리하던 엔티티를 관리하지 않는 상태가 되면 준영속 상태라고 한다.
         * 준영속 상태인 것은 데이터베이스에 반영되지 않는다.(삽입, 수정, 삭제 모두 적용 안됨)
         * (em.detach()를 통해 준영속을 만들 수 있으며 em.close()로 영속성 컨텍스트를 닫거나
         *  em.clear()를 통해 영속성 컨텍스트를 초기화해도 관리하던 엔티티들이 준영속 상태가 된다.)
         *  
         * 		em.detach(): 특정 엔티티만 준영속 상태로 만듬
         *      em.clear(): 영속성 컨텍스트를 완전히 초기화
         *      em.close(): 영속성 컨텍스트를 종료
         */
//        em.detach(menu);
        
        /* 4. 삭제 */
        /* 엔티티를 영속성 컨텍스트 및 데이터베이스에서 삭제한다.(detach 구문 주석하고 할 것, detach인 것은 데이터베이스에 반영되지 않기 때문에) */
//        em.remove(menu);
        
        /* 
         * 트랜잭션을 커밋하는 순간 영속성 컨텍스트에서 관리하는 엔티티가 데이터베이스에 반영되게 된다. (이를 flush라 한다.)
         * - flush: 영속성 컨텍스트의 변경 내용을 데이터베이스에 동기화하는 작업(등록, 수정, 삭제한 엔티티를 데이터베이스에 반영)
         */
//        tx.commit();
        
        /* 5. 병합 */        
        /* 
         * 준영속 상태의 엔티티를 받아서 그 정보로 새로운 영속 상태의 엔티티를 반환한다. 
         * (병합은 detach 이후 commit하고 새로운 트랜잭션으로 하지 않으면
         * RollbackException: Error while committing the transaction 에러가 발생한다.)  
         */
//		em.detach(menu);				// menu를 준영속 상태로 다시 변경
//		
//        menu.setName("수박죽");			// 준영속 상태에서 변경(위의 detach 구문 주석 해제하고 할것) */
//        tx.begin();
//        Menu menu2 = em.merge(menu);	// merge() 메소드로 준영속 상태인 menu 엔티티의 식별자 값으로 1차 캐시(다음 섹션에서 설명)에서 엔티티를 조회해 영속상태인 엔티티 객체를 반환 받는다.
//        tx.commit();					// (위의 em.remove()를 주석하고 실행하면 update가 실행되고 remove()를 주석하지 않고 실행하면 insert가 된다.(영속상태가 된 엔티티 객체가 DB로 flush될 때 DB에 값이 있는지 없는지에 따라 다르게 실행되기 때문이다.) 
//        
        System.out.println("엔티티 매니저가 menu를 포함하는지 : " + em.contains(menu));		// 여전히 준영속 상태
//        System.out.println("엔티티 매니저가 menu2를 포함하는지 : " + em.contains(menu2));		// 영속상태이므로 엔티티 매니저가 객체를 가지고 있다.
	}
}















