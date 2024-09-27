package com.ohgiraffers.section02.named;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


/* Named 네이티브 SQL을 사용하는 문법과 방법(xml방식까지)을 알아본다. */
public class Application {

	public static void main(String[] args) {
		
		/* 
		 * Named 네이티브 SQL
		 * 네이티브 SQL도 JPQL처럼 @NamedNativeQuery를 사용해 정적 SQL을 만들어 두고 쓸 수 있다.
		 */
		
		EntityManager em = getEntityManager();
		
		/* Named 네이티브 SQL을 활용하고 결과 매핑으로 조회 */
		/* @NamedNativeQuery를 Category에 작성하고 올 것 */
//		resultMappingWithNamedMethod(em);
		
		/* Named 네이티브 SQL을 xml에 작성하고 xml방식으로 결과 매핑까지 진행하여 조회 */
		xmlNamedMethod(em);
		
		em.close();
	}
	
	private static void resultMappingWithNamedMethod(EntityManager em) {
		
		/* createNamedQuery 메소드로 조회해야 한다. */
//		Query nativeQuery = em.createNamedQuery("Category.categoryWithMenuCount");
//		List<Object[]> resultList = nativeQuery.getResultList();
//		for (Object[] row : resultList) {
//			Category cate = (Category) row[0];
//			BigDecimal menuCount = (BigDecimal)row[1];			// COUNT의 결과는 BigDecimal로 다운 캐스팅 해야 한다.
//			
//			System.out.println("카테고리 : " + cate);
//			System.out.println("해당 카테고리의 메뉴 갯수 : " + menuCount);
//		}
		
		/* Category에 여러 개의 정적쿼리를 사용할 경우 */
		Query nativeQuery2 = em.createNamedQuery("Category.categoryInfo");
		List<Object> resultList2 = nativeQuery2.getResultList(); 
		for(Object CategoryName : resultList2) {
			System.out.println("카테고리 이름 : " + (String)CategoryName);
		}
	}
	
	private static void xmlNamedMethod(EntityManager em) {
		
		/* category-mapper.xml 파일을 만들고 Named 네이티브 SQL에 대한 설정을 한다. */
		Query nativeQuery = em.createNamedQuery("Category.categoryWithMenuCountXml");	// named-native-query 엘리먼트의 이름을 작성
		List<Object[]> resultList = nativeQuery.getResultList();
		for (Object[] row : resultList) {
			Category cate = (Category) row[0];
			BigDecimal menuCount = (BigDecimal)row[1];			// COUNT의 결과는 BigDecimal로 다운 캐스팅 해야 한다.
			
			System.out.println("카테고리 : " + row[0]);
			System.out.println("해당 카테고리의 메뉴 갯수 : " + row[1]);
		}
	}
}








