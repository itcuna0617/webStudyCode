package com.ohgiraffers.section03.persistencecontext;

import static com.ohgiraffers.section03.persistencecontext.Template.getEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


/* 엔티티와 SQL을 관리하는 영속성 컨텍스트에 대해 알아본다. */
public class Application {

	public static void main(String[] args) {
		
		/*
		 * 영속성 컨텍스트가 관리했을 때의 장점
		 * 
		 * 1. 1차 캐시 
		 *  - 영속성 컨텍스트 내부에 Map으로 관리되는 캐시(key는 @Id이며 매핑한 식별자이고 value은 엔티티 인스턴스이다.)이며
		 *    이 곳에 있는 엔티티는 캐시에서 바로 불러와서 조회 성능이 올라간다.
		 *             
		 * 2. 동일성 보장 
		 *  - 반복해서 호출 시 1차 캐시에서 같은 엔티티 인스턴스를 가져올 수 있다.
		 *   
		 * 3. 트랜잭션을 지원하는 쓰기 지연(transactional write-behind)
         *  - (엔티티 등록(INSERT)을 예로 들면) 엔티티 매니저는 트랜잭션을 커밋하기 직전까지 데이터베이스에 저장(flush) 대신 쓰기 지연 SQL 저장소에
		 *    INSERT SQL을 차곡차곡 쌓게 되며 커밋 시에 쿼리를 데이터베이스로 보내는데 이를 트랜잭션을 지원하는 쓰기 지연이라고 한다.
		 *    
		 *    플러시(flush): flush()는 영속성 컨텍스트의 변경 내용을 데이터베이스에 반영한다.
		 *    플러시 절차: a. 영속성 컨텍스트에 보관할 때 최초 엔티티 상태를 복사해서 스냅샷으로 저장해 두고 모든 엔티티를 스냅샷과 비교해서
		 *                  수정된 엔티티를 찾아서 수정 쿼리를 만들어 쓰기 지연 SQL 저장소에 보낸다. 
		 *               b. 쓰기 지연 SQL 저장소의 쿼리를 데이터베이스에 저장한다.
		 *    플러시를 하는 경우: a. em.flush()를 직접 호출한다.
		 *                    b. 트랜잭션 커밋 시 플러시가 자동 호출한다.
		 *                    c. JPQL 쿼리 실행 시 플러시가 자동 호출한다.
		 *    
		 * 4. 변경 감지(dirty checking)
		 *  - SQL에 의존적이지 않도록 엔티티의 데이터 변경을 감지하고 데이터베이스에 자동으로 반영하는 기능을 변경 감지라고 한다.
		 *    영속성 컨텍스트에 보관할 때 최초 엔티티 상태를 복사해서 저장한 스냅샷과 이를 비교하여 감지한다.
		 *    영속 상태의 엔티티에만 적용된다.(준영속이나 비영속은 해당되지 않는다.)
		 *   
		 *    변경 감지는 아래의 절차를 따른다.(커밋 실행 시)
		 *    a. 우선 엔티티 매니저 내부에서 먼저 플러시(flush)가 호출된다.
		 *    b. 엔티티와 스냅샷을 비교해서 변경된 엔티티를 찾는다.
		 *    c. 변경된 엔티티와 관련된 수정 쿼리를 생성해서 쓰기 지연 SQL 저장소에 보낸다.
		 *    d. 쓰기 지연 저장소의 SQL을 데이터베이스로 보낸다.
		 *    e. 데이터베이스에서 트랜잭션을 커밋한다. 
		 * 
		 */
		
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Menu menu = new Menu();
        menu.setCode(29);
        menu.setName("캐러멜라멘");
        menu.setCategoryCode(6);
        menu.setOrderableStatus("Y");
        
        tx.begin();
        
//        em.persist(menu);	// 내부 캐시에는 @id(키) : 29, Entity(값) : persist에 활용된 Menu 객체(엔티티)
        
        /* 1. 1차 캐시 */
//        Menu findMenu1 = em.find(Menu.class, 24);		// 조회된 는 영속성 컨텍스트의 1차 캐시에 저장 된 식별자인 키를 통해 값을 조회
//        Menu findMenu2 = em.find(Menu.class, 21);		// 영속성 컨텍스트에 없는 값은 데이터베이스에서 조회하고 1차 캐시에 저장한다. 
//                                                            // (영속성 컨텍스트의 1차 캐시에 @id(키) : 21, Entity(값) : 데이터베이스에서 조회된 엔티티 객체
//        System.out.println(findMenu1);
//        System.out.println(findMenu2);
        
        /* 2. 동일성 보장 */
//        Menu findMenu3 = em.find(Menu.class, 15);
        Menu findMenu4 = em.find(Menu.class, 15);
//        System.out.println("동일성 비교 : " + (findMenu3 == findMenu4));
        
        /* 3. 트랜잭션을 지원하는 쓰기 지연 */
        /* 이는 내부 원리이므로 예제를 생략 */
        
        /* 4. 변경 감지 */
        /* 
         * 실행 후 커밋 전에 스냅샷과 비교하여 변화를 감지하고 쿼리를 만들어 실행함을 확인하자. (모든 필드를 수정하는 UPDATE문으로 작성되는 것도 같이 확인) 
         * 
         * 모든 필드를 수정하는 UPDATE문으로 작성 되는 이유
         * - 수정 쿼리가 항상 같아 수정쿼리를 재사용할 수 있다.
         * - 데이터베이스 입장에서도 동일한 쿼리를 받으면 이미 파싱된 쿼리를 재사용할 수 있다.
         */
        findMenu4.setName("바닐라우동");
        findMenu4.setOrderableStatus("N");
        
        tx.commit();
        
        em.close();
	}

}
