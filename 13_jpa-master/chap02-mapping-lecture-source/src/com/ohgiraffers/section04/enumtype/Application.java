package com.ohgiraffers.section04.enumtype;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/* enum 클래스를 활용하는 방법을 알아본다. */
public class Application {

	public static void main(String[] args) {
		
		/* 
		 * @Enumerated
		 *	enum 클래스를 활용해서 enum에 속한 값만(도메인) DB에 저장할 경우 크게는 문자열과 숫자 두가지로 저장할 수 있다.
		 *  1. EnumType.ORDINAL은 enum에 정의된 순서대로 0, 1, ...값을 데이터베이스에 저장한다.
		 *     장점: 데이터베이스에 저장되는 데이터의 크기가 작다.
		 *     단점: 이미 저장된 enum의 순서를 변경할 수 없다.
		 *  2. EnumType.STRING은 enum에 저장된 이름 그대로 'ADMIN', 'MEMBER'와 같은 문자로 데이터베이스에 저장한다.
		 *     장점: 저장된 enum의 순서가 바뀌거나 enum이 추가되어도 안전하다.
		 *     단점: 데이터베이스에 저장되는 데이터 크기가 ORDINAL에 비해 크다. 
		 */
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Member member = new Member();
        member.setNo(1);
        member.setId("user01");
        member.setPwd("pass01");
        member.setNickname("홍길동");
        member.setPhone("010-1234-1234");
        member.setAddress("서울시 서초구");
        member.setEnrollDate(new Date(System.currentTimeMillis()));
//        member.setRole("ADMIN");
        member.setRole(RoleType.ADMIN);
        member.setStatus("Y");
        
		Member member2 = new Member();
        member2.setNo(2);
        member2.setId("user02");
        member2.setPwd("pass02");
        member2.setNickname("유관순");
        member2.setPhone("010-777-7777");
        member2.setAddress("서울시 강남구");
        member2.setEnrollDate(new Date(System.currentTimeMillis()));
//        member.setRole("MEMBER");
        member2.setRole(RoleType.MEMBER);
        member2.setStatus("Y");
        
        tx.begin();
        
        em.persist(member);	
        em.persist(member2);
        
        tx.commit();
		em.close();
	}

}
