package com.ohgiraffers.section01.selectmethods;

import static com.ohgiraffers.common.Template.getEntityManager;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


/* 네이티브 SQL을 사용하는 목적과 문법에 대해 알아본다. */
public class Application {

	public static void main(String[] args) {
		
		/* 
		 * 네이티브 SQL
		 * - JPQL은 데이터베이스들이 따로 지원하는 것들에 있어 모든 것을 SQL로 자동으로 바꿔주지 않는다.
		 *   (인라인 뷰, UNION, INTERSECT 등등)
		 * - 네이티브 SQL은 SQL을 개발자가 직접 정의해서 사용할 수 있도록 해주는 수동모드이다.
		 *   쉽게 말해, 어떠한 다양한 이유로 JPQL을 사용할 수 없는 경우나 SQL쿼리를 최적화해서 데이터
		 *   베이스의 성능을 향상시킬 때 JPA는 Native SQL을 통해 SQL을 직접 사용할 수 있는 기능을 제공해 준다.
		 * 
		 * JDBC API와의 차이점
		 * - 직접 SQL을 작성하는 JDBC API와는 달리 네이티브 SQL은 JPA의 영속성 컨텍스트의 기능을
		 *   그대로 사용할 수 있다.
		 *
		 * 네이티브 쿼리 API는 다음의 3가지가 있다.
		 *   1. 결과 타입 정의
		 *   public Query createNativeQuery(String sqlString, Class resultClass);
		 * 
		 *   2. 결과 타입을 정의할 수 없을 때
		 *   public Query createNativeQuery(String sqlString);
		 * 
		 *   3. 결과 매핑 사용
		 *   public Query createNativeQuery(String sqlString, String resultSetMapping);
		 */
		
		EntityManager em = getEntityManager();
		
		/* 1. 결과 타입으로 조회(특정 엔티티로 조회) */
//		resultTypeMethod(em);
		
		/* 2. 결과 타입 없이 조회(스칼라 값들로 조회) */
//		nonResultTypeMethod(em);
		
		/* 3. 결과 매핑으로 조회(엔티티와 스칼라 값들로 조회) */
		resultMappingMethod(em);
		
		em.close();
	}
	
	private static void resultTypeMethod(EntityManager em) {
		
		/* JPQL과의 차이점은 해당 데이터베이스 고유의 SQL문법을 사용한다는 것과 위치기반 파라미터만 지원하는 것이다. */
		String sql1 = "SELECT A.* "
				    + "FROM TBL_MENU A WHERE A.MENU_NAME = ?";
		
		/* 타입을 명시했는데 TypeQuery가 아닌 Query가 리턴되는 것은 JPA1.0에서 API 규약이 정의되었기 때문으로 크게 신경 쓰지 않아도 된다. */
		Query nativeQuery1 = em.createNativeQuery(sql1, Menu.class).setParameter(1, "우럭스무디");		//(주의!) 조회할 때 모든 컬럼을 조회할 때만 Menu에 담을 수 있다.
																										// 그렇지 않고 일부 컬럼들만 스칼라값으로 조회할 때는 Object[]이나
																										// 그 값들을 담을 객체를 활용해야 한다.
		Menu menu = (Menu)nativeQuery1.getSingleResult(); 										
		System.out.println(menu);
	}
	
	private static void nonResultTypeMethod(EntityManager em) {
		String sql2 = "SELECT A.MENU_NAME, A.MENU_PRICE "
			    + "FROM TBL_MENU A";
	
		Query nativeQuery2 = em.createNativeQuery(sql2);
		List<Object[]> resultList = nativeQuery2.getResultList(); 
		for(Object[] row : resultList) {
			System.out.println("메뉴 이름 : " + row[0] + ", 메뉴 가격 : " + row[1]);
		}
	}
	
	private static void resultMappingMethod(EntityManager em) {
		
		/* 
		 * 조회할 값들이 엔티티와 스칼라 값이 섞여있는 복잡한 경우에는
		 * @SqlResultSetMapping을 정의해서 결과 매핑을 사용해야 한다. 
		 */

		/* 각 카테고리에 메뉴가 몇 개씩 있는지 서브쿼리와 조인을 활용한 코드(sqlDeveloper에서 바로 복붙해서 \r\n이 포함되어 있다.) */
		String sql = "SELECT\r\n"
				   + "       A.CATEGORY_CODE, A.CATEGORY_NAME, A.REF_CATEGORY_CODE, D.MENU_COUNT\r\n"
				   + "  FROM TBL_CATEGORY A\r\n"
				   + "  LEFT JOIN (SELECT COUNT(*) AS MENU_COUNT, B.CATEGORY_CODE \r\n"
				   + "               FROM TBL_CATEGORY B\r\n"
				   + "               JOIN TBL_MENU C ON (B.CATEGORY_CODE = C.CATEGORY_CODE)\r\n"
				   + "               GROUP BY B.CATEGORY_CODE) D ON (A.CATEGORY_CODE = D.CATEGORY_CODE)\r\n"
				   + " ORDER BY 1";
		
		/* Category에 @SqlResultSetMapping을 정의하고 결과 매핑 설정을 하자 */
		Query nativeQuery = em.createNativeQuery(sql, "categoryWithMenuCountMap");
		List<Object[]> resultList = nativeQuery.getResultList();
		for (Object[] row : resultList) {
			Category cate = (Category) row[0];
			BigDecimal menuCount = (BigDecimal)row[1];			// COUNT의 결과는 BigDecimal로 다운 캐스팅 해야 한다.
			
			System.out.println("카테고리 : " + cate);
			System.out.println("해당 카테고리의 메뉴 갯수 : " + row[1]);
		}
	}
}
