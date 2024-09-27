package com.ohgiraffers.section01.ddl;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/* 자동 DDL 작업을 위한 persistence.xml의 설정에 대해 알아본다. */
public class Application {

	public static void main(String[] args) {
		
		/*
		 * 엔티티와 테이블을 정확히 매핑하는 것이 JPA의 핵심이다.
		 * 이를 위해 다양한 매핑 어노테이션(mapping annotation)이 지원되는데 크게 4가지로 분류할 수 있다.
		 *  
		 * 객체와 테이블: @Entity, @Table
		 * 기본 키 매핑: @Id
		 * 필드와 컬럼 매핑: @Column
		 * 연관관계 매핑: @ManyToOne, @OneToMany, @JoinColumn 
		 * 
		 * 이번 섹션에서 알아볼 것은 기본적인 매핑 어노테이션을 활용하여 데이터베이스 스키마(간략하게 테이블로 설명)를 자동 생성해 볼 것이다.
		 */
		
		/* 
		 * 자동 스키마 생성(테이블 생성)을 위한 설정을 위해 persistence.xml에 가서
		 * <property name="hibernate.hbm2ddl.auto" value="create" />을 추가해 준다
		 */
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Member member = new Member();
        member.setNo(10);
        member.setId("user01");
        member.setPwd("pass01");
        member.setNickname("홍길동");
        member.setPhone("010-1234-1234");
        member.setAddress("서울시 서초구");
        member.setEnrollDate(new Date(System.currentTimeMillis()));
        member.setRole("ADMIN");
        member.setStatus("Y");
        
        Member member2 = new Member();
        member2.setNo(20);
        member2.setId("user02");
        member2.setPwd("pass02");
        member2.setNickname("유관순");
        member2.setPhone("010-777-7777");
        member2.setAddress("서울시 강남구");
        member2.setEnrollDate(new Date(System.currentTimeMillis()));
        member2.setRole("MEMBER");
        member2.setStatus("Y");
        
        tx.begin();
        
        em.persist(member);		// 기존에 있는 테이블이더라도 DROP TABLE을 실행해 주고 새로 CREATE TABLE을 해 주며 insert도 진행된다.
        em.persist(member2);
        
        tx.commit();
		em.close();
	}

}
