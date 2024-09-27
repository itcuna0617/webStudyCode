package com.ohgiraffers.section03.persistencecontext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

public class Template {
	private static EntityManagerFactory entityManagerFactory;
	
	public static EntityManager getEntityManager() {
		if(entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
		}
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		/* flushMode설정은 수업을 해 줘도 되고 안해줘도 됨(따로 보여주기가 애매함) */
//		entityManager.setFlushMode(FlushModeType.AUTO);		// 커밋이나 쿼리를 실행할 때 플러시(기본값)
		entityManager.setFlushMode(FlushModeType.COMMIT);	// 커밋할 때만 플러시
		
		return entityManager;
	}
	
	
}
