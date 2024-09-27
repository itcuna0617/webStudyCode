package com.ohgiraffers.section03.projection;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.util.List;

import javax.persistence.EntityManager;

/* JPQL에서 제공하는 프로젝션을 통해 조회할 값들에 따라 어떤 방식으로 처리할 수 있는지에 대해 살펴본다. */
public class Application {

	public static void main(String[] args) {
		
		/* 
		 * 프로젝션(projection)
		 * SELECT 절에 조회할 대상을 지정하는 것을 프로젝션이라고 한다.
		 * (SELECT {프로젝션 대상} FROM)
		 * 
		 * 프로젝션 대상은 4가지 방식이 있다.
		 * 1. 엔티티 프로젝션
		 *    원하는 객체를 바로 조회할 수 있다.
		 *    조회된 엔티티는 영속성 컨텍스트가 관리한다.
		 *    
		 * 2. 임베디드 타입 프로젝션(임베디드 타입에 대한 설명은 MenuInfo 클래스에서 설명)
		 *    엔티티와 거의 비슷하게 사용되며 조회의 시작점이 될 수 없다.
		 *    엔티티 타입이 아닌 값 타입으로 조회한 임베디드 타입은 영속성 컨텍스트에서 관리되지 않는다.
		 *    
		 * 3. 스칼라 타입 프로젝션
		 *    숫자, 문자, 날짜 같은 기본 데이터 타입이다.
		 *    스칼라 타입은 영속성 컨텍스트에서 관리되지 않는다.
		 *    
		 * 4. new 명령어를 활용한 프로젝션
		 *    다양한 종류의 단순 값들을 DTO로 바로 조회하는 방식으로 new 패키지명.DTO명을 쓰면
		 *    해당 DTO로 바로 반환받을 수 있다.
		 *    new 명령어를 사용한 클래스의 객체는 엔티티가 아니므로 영속성 컨텍스트에서 관리되지 않는다.
		 */
		EntityManager em = getEntityManager();
		
		/* 1. 엔티티 */
//		entityMethod(em);
		
		/* 2. 임베디드 타입 */
//		embeddedMethod(em);
		
		/* 3. 스칼라 타입 */
//		scalarMethod(em);
		
		/* 4. new 명령어 */
		newMethod(em);
		
		em.close();
	}
	
	private static void entityMethod(EntityManager em) {
		
		/* 메뉴 엔티티만 가지고 진행 */
//		String jpql = "SELECT m FROM SECTION03_MENU m";
//		List<Menu> menuList = em.createQuery(jpql, Menu.class).getResultList();
//		
//		for (Menu menu : menuList) {
//			System.out.println(menu);
//		}
		
		/* 양방향 연관관계에 있는 엔티티들로 진행 */
		String jpql = "SELECT m.category FROM SECTION03_MENUANDCATEGORY m WHERE m.code = 3";
		CategoryAndMenu menu = em.createQuery(jpql, CategoryAndMenu.class).getSingleResult();
		
		System.out.println(menu);
	}
	
	private static void embeddedMethod(EntityManager em) {
		
		/* 
		 * 임베디드 타입을 프로젝션하기 위해서 임베디드 타입(MenuInfo)과 
		 * 임베디드 타입을 활용하는 엔티티(EmbeddedMenu)를 만들고 아래 예제 작성
		 */
		String jpql = "SELECT m.menuInfo FROM SECTION03_EMBEDDEDMENU m";
		
		/* 임베디드 타입을 프로젝션 했으므로 임베디드 타입이 나오게 된다. */
		List<MenuInfo> menuInfoList = em.createQuery(jpql, MenuInfo.class).getResultList();
		
		for (MenuInfo menuInfo : menuInfoList) {
			System.out.println(menuInfo);
		}
	}
	
	private static void scalarMethod(EntityManager em) {
		
		/* 기본 데이터 타입을 프로젝션 하는 것으로 진행 */
//		String jpql = "SELECT c.name FROM SECTION03_CATEGORY c";
//		List<String> categoryList = em.createQuery(jpql, String.class).getResultList();
//		for (String category : categoryList) {
//			System.out.println(category);
//		}
		
		/* 
		 * 여러 타입의 다중열을 프로젝션할 경우 (이 때는 TypeQuery 대신 Query를 사용해야 한다.)
		 * (실제 어플리케이션 개발시에는 Object[]을 반환받아 사용하지 않고 다음 예제은 new명령어를 활용해서
		 *  의미있는 객체로 변환해서 사용하게 된다.) 
		 */
		String jpql = "SELECT c.code, c.name FROM SECTION03_CATEGORY c";
		List<Object[]> categoryList = em.createQuery(jpql).getResultList();
		for (Object[] row : categoryList) {
			System.out.print((int)row[0] + ", ");		// Object[]이라 각각의 값을 다시 다운캐스팅 하기 번거롭다.
			System.out.println((String)row[1]);
		}
	}
	
	private static void newMethod(EntityManager em) {
		
		/* 
		 * 앞서 살펴본 Query를 활용하여 여러 타입을 프로젝션 할 경우 번거로운 객체 변환작업을 줄이기 위해
		 * 조회된 값(Category의 code와 name)을 담을 용도로만 쓸 객체인 CategoryCodeAndName(엔티티 아님)를
		 * new 명령어를 통해 만들 수 있다.
		 */
		String jpql = "SELECT new com.ohgiraffers.section03.projection.CategoryCodeAndName(c.code, c.name) "
				    + "FROM SECTION03_CATEGORY c";
		List<CategoryCodeAndName> categoryCodeAndMenuList = em.createQuery(jpql, CategoryCodeAndName.class)
				                                                 .getResultList();
		for (CategoryCodeAndName categoryCodeAndMenu : categoryCodeAndMenuList) {
			System.out.println(categoryCodeAndMenu.getCode());		// 다운캐스팅의 번거로움이 없다.
			System.out.println(categoryCodeAndMenu.getName());
		}
	}
}
