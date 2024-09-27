package com.ohgiraffers.section02.bidirectional;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.ohgiraffers.section01.unidirectional.onetomany.Category;

/* 양방향 연관관계를 어떻게 매핑해야 하는지에 대해 알아보자 */
public class Application {
	public static void main(String[] args) {
		
		/* 
		 * 양방향 매핑은 다소 복잡하다.
		 * 연관관계의 주인을 정하고 두 개의 단방향 연관관계를 양방향으로 만들기 위해 로직도 잘 관리해야 한다.
		 * 양방향이라는 개념은 연관관계가 하나인 단방향 매핑에 주인이 아닌 연관관계를 하나 더 추가하는 방식으로
		 * 작성하게 된다.
		 * 양방향을 하게 되는 이유는 반대방향으로 객체 그래프 탐색 기능이 추가되기 때문에 하게 된다.
		 */
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		/* CategoryAndMenuDTO에서 양방향 관계에 대해 @mappedBy부분을 설명하고 돌아온다. */
		
		/* 조회 */
//        selectMethod(em);
        
		try {
			
		/* 저장 */
		tx.begin();
		insertMethod(em);
		tx.commit();
		
		/* 수정 */
//		tx.begin();
//		updateMethod(em);
//		tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
        
		em.close();
	}
	
	private static void selectMethod(EntityManager em) {
		
		/* 양방향 관계에서 카테고리에 맞는 메뉴 조회하기(카테고리 -> 메뉴 조회) */
//		CategoryAndMenu category = em.find(CategoryAndMenu.class, 10);
//		List<MenuAndCategory> menuList = category.getMenuList();
//		
//		for (MenuAndCategory menu : menuList) {
//			System.out.println(menu);
//		}
		
		/* 양방향 관계에서 메뉴의 카테고리 조회하기(메뉴 -> 카테고리 조회) */
		MenuAndCategory menu = em.find(MenuAndCategory.class, 17);
		CategoryAndMenu category2 = menu.getCategory();
		System.out.println("카테고리 이름: " + category2);
		
		/* 해당 메뉴의 해당 카테고리에 속하는 메뉴들도 같이 넘어오게 된다.(양방향이므로 관계된 모든 것을 인지하고 담아준다.) */
		for(MenuAndCategory eachMenu : category2.getMenuList()) {
			System.out.println("메뉴: " + eachMenu);
		}
			
	}
	
	private static void insertMethod(EntityManager em) {
		
		/* 저장은 카테고리 저장 후 메뉴를 저장하는데 반드시 연관관계의 주인 객체에 연관관계에 있는 객체를 입력해야 한다. */
		/* 
		 * 양방향 연관관계에서 연관관계의 주인이 아닌 객체는 연관관계에 있는 객체의 값을 입력하지 않고
		 * 연관관계의 주인인 객체는 반드시 연관관계에 있는 객체의 값을 입력해야 한다.
		 * (DB모델링 상에서 1:N은 부모와 자식 엔티티에 해당되고 부모엔티티가 있으면 자식 엔티티는 부모엔티티 값을
		 * 참조해서 존재하기 때문이다.)
		 */
		
		/* 새로운 카테고리 추가 */
		/* 연관관계의 주인이 아니므로 menuList에 값을 입력하지 않아도 된다. */
		CategoryAndMenu newCate = new CategoryAndMenu();
		newCate.setCode(17);
		newCate.setName("아랍식");
		newCate.setRefCategoryCode(3);
		
		/* 새로운 카테고리인 군것질용 메뉴 추가 */
		/* 연관관계의 주인에게는 연관관계에 있는 CategoryAndMenuDTO를 반드시 입력 해야 한다. */
		MenuAndCategory newMenu = new MenuAndCategory();
		newMenu.setCode(34);
		newMenu.setName("짜장맛케밥");
		newMenu.setPrice(5600);
		newMenu.setOrderableStatus("Y");
		
		newMenu.setCategory(newCate);				// 메뉴 -> 카테고리 연관관계 설정
		
		/* 
		 * 사실 객체 관점에서는 양쪽 방향에 서로 연관관계에 있는 객체를 넣어주는 것이 가장 안전하긴 하다. 
		 * (JPA와 상관없이 이 객체 자체만 다루게 될 때는 연관관계의 주인이 아닌 객체에서 연관관계의 주인인 객체에
		 * 대한 내용이 없기 때문에 객체에 있어서 양방향의 의미를 잃게 되기 때문이다. 따라서 우리가 하는 ORM은
		 * 객체와 관계형 데이터베이스 둘다 중요하고 둘다 함께 고려해야 한다.)
		 */
		newCate.getMenuList().add(newMenu);			// 카테고리 -> 메뉴 연관관계 설정 (주석 풀어서 category와 menu를 한번더 저장해 볼 것)
		em.persist(newCate);
		em.persist(newMenu);
		
		/* 
		 * (주의!)단순 자동완성 toString을 두 객체(CategoryAndMenuDTO와 MenuAndCategoryDTO)에 그대로 만들면
		 * 서로가 서로를 참조하며 toString을 만들게 되서 무한루프에 빠지게 된다.(stackoverflow 발생)
		 * 따라서 서로가 서로의 toString을 참조하지 않도록 MenuAndCategoryDTO의 toString에서 category의 이름만
		 * 찍어낼 수 있도록 수정하자! 
		 */
		System.out.println("추가한 카테고리와 메뉴 정보(카테고리 객체) : " + newCate);
		System.out.println("추가한 카테고리와 메뉴 정보(메뉴 객체) : " + newMenu);
	}
	
	private static void updateMethod(EntityManager em) {
		
		/* 기타 카테고리의 메뉴 중 첫번째 메뉴의 가격을 20000으로 바꿔보자(카테고리 -> 메뉴 수정) */
		CategoryAndMenu selectedCategory = em.find(CategoryAndMenu.class, 10);
		MenuAndCategory firstMenu = selectedCategory.getMenuList().get(0);
		firstMenu.setPrice(20000);
		
		/* 25번 메뉴의 카테고리 이름을 퓨전으로 바꿔보자(메뉴 -> 카테고리 수정) */
		MenuAndCategory selectedMenu = em.find(MenuAndCategory.class, 25);
		selectedMenu.getCategory().setName("퓨전");
	}
	
	/* 일대일이나 다대다는 정규화를 만족하지 않는 관계로 비정규화 관계에 대한 예제는 생략하도록 한다. */ 
}

