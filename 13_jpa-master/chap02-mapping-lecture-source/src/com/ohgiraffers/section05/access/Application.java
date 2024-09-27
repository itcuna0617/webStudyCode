package com.ohgiraffers.section05.access;

import static com.ohgiraffers.common.Template.getEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/* Access 어노테이션을 활용하는 접근 방식 두가지를 알아본다. */
public class Application {

	public static void main(String[] args) {
		
		/*
		 * @Access
		 * JPA가 엔티티 데이터에 접근하는 방식을 지정할 수 있으며 두 가지 방식이 있다.
		 * 1. 필드 접근: 필드에 직접 접근하며 필드 접근 권한이 private여도 접근할 수 있다.
		 * 2. 프로퍼티 접근: 접근자(Getter)를 사용하여 접근하는 방식이다.
		 * 
		 * @Access를 설정하지 않으면 @Id의 위치를 기준으로 접근 방식이 설정된다.
		 */
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Member member = new Member();
        member.setNo(1);
        member.setId("user01");
        member.setPwd("pass01");
        member.setRrn("900101-1234567");
        
        tx.begin();
        
        em.persist(member);	
        
        /*
         *  조회한 객체는 영속성 컨텍스트의 1차캐시에 저장된 객체를 가져왔기 때문에
         *  pwd와 rrn의 값을 지니고 있지만 @Transient이기 때문에 DB에는 적용되지 않는다.
         */
        Member selectMember = em.find(Member.class, 1);
        System.out.println(selectMember);		
        
        tx.commit();
		em.close();
	}

}
