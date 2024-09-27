package com.ohgiraffers.section02.fieldMapping;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/* 좀 더 구체적으로 매핑을 위한 매핑 어노테이션을 살펴보고 DDL을 하되 다양한 제약 조건을 다뤄 본다. */
public class Application {

	public static void main(String[] args) {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Member member = new Member();
        member.setNo(1);
        member.setId("user01");
        member.setPwd("pass01");
        member.setNickname("홍길동");
        member.setPhone("010-1234-1234");			// @Colum의 columnDefinition에서 default 체크할 때 실행할 것
        member.setAddress("서울시 서초구");
        member.setEnrollDate(new Date(System.currentTimeMillis()));
        member.setRole("ADMIN");
        member.setStatus("Y");
        
		Member member2 = new Member();
        member2.setNo(2);
        member2.setId("user02");
        member2.setPwd("pass02");
        member2.setNickname("유관순");
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
