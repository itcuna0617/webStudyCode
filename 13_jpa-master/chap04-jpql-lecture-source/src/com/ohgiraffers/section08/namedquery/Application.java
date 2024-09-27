package com.ohgiraffers.section08.namedquery;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.util.List;

import javax.persistence.EntityManager;

/* 동적 쿼리와 정적 쿼리의 개념 및 정적 쿼리인 Named 쿼리를 사용하는 방법(xml방식까지)을 알아본다. */
public class Application {

	public static void main(String[] args) {
		
		/*
		 * 동적쿼리: 현재 우리가 하는 방식처럼 EntityManager가 제공하는 메소드를 이용하여 JPQL을 문자열로
		 *          런타임 시점에 동적으로 쿼리를 만드는 방식을 말한다.
		 *          (동적으로 만들어질 쿼리를 위한 조건식이나 반복문은 자바를 활용하면 된다.)
		 * 정적쿼리: 미리 쿼리를 정의하고 변경하지 않고 사용하는 쿼리를 말하며 미리 정의한 코드는 이름을
		 *         부여해서 사용하게 된다. 이런것을 Named 쿼리라고 한다.
		 *         어노테이션 방식과 xml방식 두가지가 있는데 쿼리가 복잡할 수록 xml방식이 선호된다.
		 *         (문자열로 쿼리를 만드는 것은 힘들기 때문이다.)
		 */
		EntityManager em = getEntityManager();
		
		/* 네이티브 SQL을 활용하여 조회 */
//		namedQueryMethod(em);
		
		/* 네이티브 SQL을 xml에 작성하고 xml방식으로 조회 */
		xmlNamedMethod(em);
		
		em.close();
	}
	
	public static void namedQueryMethod(EntityManager em) {
		
		/* Menu 엔티티에 @NamedQuery어노테이션을 작성한다. */
		/* createQuery 대신 EntityManager에서 제공하는 createNamedQuery를 사용해야 한다. */ 
		List<Menu> menuList1 = em.createNamedQuery("Menu.selectMenuName", Menu.class).getResultList();
		
		for (Menu menu : menuList1) {
			System.out.println(menu);
		}
		
		/* Menu 엔티티에 @NamedQueries어노테이션으로 바꿔서 작성한다. */
		List<Menu> menuList2 = em.createNamedQuery("Menu.selectMenuNameByCode", Menu.class)
								    .setParameter("menuCode", 2)
				                    .getResultList();
		
		for (Menu menu : menuList2) {
			System.out.println(menu);
		}
		
		/* 
		 * 어노테이션 방식이 끝나면 XML방식도 진행해 본다.(어노테이션 방식의 @NamedQuery관련 어노테이션들은 주석하고 확인할 것)
		 * META-INF에 menu-mapper.xml을 작성하고
		 * persistence.xml에 <mapping-file>META-INF/menu-mapper.xml</mapping-file>라는 매핑 파일 등록용 엘리먼트도 추가해 준다.
		 *   
		 */
	}
	
	public static void xmlNamedMethod(EntityManager em) {
		
		/* menu-mapper.xml 파일을 만들고 네이티브 SQL에 대한 설정을 한다. */
		List<Menu> menuList1 = em.createNamedQuery("Menu.selectMenuNameXml", Menu.class).getResultList();
		
		for (Menu menu : menuList1) {
			System.out.println(menu);
		}
		
		/* Menu 엔티티에 @NamedQueries어노테이션으로 바꿔서 작성한다. */
		List<Menu> menuList2 = em.createNamedQuery("Menu.selectMenuNameByCodeXml", Menu.class)
								    .setParameter("menuCode", 2)
				                    .getResultList();
		
		for (Menu menu : menuList2) {
			System.out.println(menu);
		}
	}

}
