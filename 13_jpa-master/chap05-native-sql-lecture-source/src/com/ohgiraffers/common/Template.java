package com.ohgiraffers.common;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
