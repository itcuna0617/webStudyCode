package com.ohgiraffers.common;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/* Template은 다른 섹션에서 공통적으로 쓰기 위해 common 패키지로 분리 */
public class Template {
	private static EntityManagerFactory entityManagerFactory;
	
	public static EntityManager getEntityManager() {
		if(entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
		}
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		return entityManager;
	}
	
	
}
