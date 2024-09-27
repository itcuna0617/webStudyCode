package com.ohgiraffers.section06.compositekey;

import static com.ohgiraffers.common.Template.getEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/* 복합키를 적용하기 위한 두가지 방법에 대해 알아보자 */
public class Application {

	public static void main(String[] args) {
		
		/* 
		 * 복합키를 따로 저장하기 위한 PK 저장용 객체가 있어야 한다.
		 * @Embeddable과 @IdClass 두가지 방식이 있다.
		 * (두 방식 모두 PK 저장용 Class는 엔티티가 아니다.)
		 * 두 방식은 물리적 모델 관점에서는 차이가 없다.
		 * 
		 * 굳이 차이를 따지자면,
		 * 1. @EmbeddedId는 @Embeddable(이후 chap04의 section03에서 다시 다룰 예정)이 있는 임베디드 타입을
		 *    활용하는 것을 강조하는 의미가 있다.(임베디드 타입은 영속성 컨텍스트가 관리하지 않는다.(엔티티가 아니다.))
		 * 2. @IdClass에서 사용하는 복합키 저장용 객체 역시 엔티티가 아닌 복합키 저장 외에 특별한 의미가 없는 객체를 
		 *    활용하게 되며 딱히 강조하는 의미는 없다.
		 */
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		/* 1. @Embeddable을 이용하는 방법 */
//		compositeKeyMethod1(em);
		
		/* 2. @IdClass를 이용하는 방법 */
		compositeKeyMethod2(em);
		
		tx.commit();
		em.close();
	}
	
	private static void compositeKeyMethod1(EntityManager em) {
		MemberPk1 memberPk1 = new MemberPk1();
		memberPk1.setNickname("슈퍼맨");
		memberPk1.setEmail("superman@gmail.com");
		
		Member1 member1 = new Member1();
		member1.setMemberPk(memberPk1);
		member1.setAddress("서울시 서초구");
        
        em.persist(member1);	
        
        Member1 selectMember = em.find(Member1.class, memberPk1);
        System.out.println(selectMember);	
	}
	
	private static void compositeKeyMethod2(EntityManager em) {
		Member2 member2 = new Member2();
		member2.setNickname("슈퍼맨");
		member2.setEmail("superman@gmail.com");
		member2.setAddress("서울시 서초구");
        
        em.persist(member2);	
        
		MemberPk2 memberPk2 = new MemberPk2();
		memberPk2.setNickname("슈퍼맨");
		memberPk2.setEmail("superman@gmail.com");
        
        Member2 selectMember = em.find(Member2.class, memberPk2);
        System.out.println(selectMember);	
	}

}
