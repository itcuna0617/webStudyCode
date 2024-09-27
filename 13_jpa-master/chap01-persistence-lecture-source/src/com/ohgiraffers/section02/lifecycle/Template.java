package com.ohgiraffers.section02.lifecycle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Template {
	private static EntityManagerFactory entityManagerFactory;
	
	public static EntityManager getEntityManager() {
		
		/* 
		 * 엔티티 매니저 팩토리는 만드는데 비용이 커서 하나만 만들고 쓰레드 간에 공유해도 되지만
		 * 엔티티 매니저는 여러 스레드가 동시에 접근하면 동시성 문제가 발생해서 스레드 간에 공유되면 안된다.
		 */
		if(entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
		}
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		return entityManager;
	}
	
	
}
