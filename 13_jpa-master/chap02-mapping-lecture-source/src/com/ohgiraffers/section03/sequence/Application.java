package com.ohgiraffers.section03.sequence;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/* pk가 자동으로 매핑 될 수 있는 두가지 방법에 대해 알아본다. */
public class Application {

	public static void main(String[] args) {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Member member = new Member();
//        member.setNo(1);				// 자동으로 pk가 매핑되게 해 보자.(DTO(엔티티 클래스)에서 sequence 전략과 table 전략 적용)
        member.setId("user01");
        member.setPwd("pass01");
        member.setNickname("홍길동11");
        member.setPhone("010-1234-1234");
        member.setAddress("서울시 서초구");
        member.setEnrollDate(new Date(System.currentTimeMillis()));
        member.setRole("ADMIN");
        member.setStatus("Y");
        
		Member member2 = new Member();
//        member.setNo(2);
        member2.setId("user02");
        member2.setPwd("pass02");
        member2.setNickname("유관순12");
        member2.setPhone("010-777-7777");
        member2.setAddress("서울시 강남구");
        member2.setEnrollDate(new Date(System.currentTimeMillis()));
        member2.setRole("MEMBER");
        member2.setStatus("Y");
        
        tx.begin();
        
        em.persist(member);	
        em.persist(member2);
        
        tx.commit();
		em.close();
	}


}
