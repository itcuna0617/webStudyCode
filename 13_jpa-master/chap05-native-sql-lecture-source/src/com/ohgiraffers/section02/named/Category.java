package com.ohgiraffers.section02.named;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

@Entity(name = "SECTION02_CATEGORY")

/* 결과 매핑용 어노테이션 */
@SqlResultSetMapping(name = "categoryWithMenuCountMap2",			// section1의 매핑 이름과 다른 이름을 주어야 한다.
	entities = {@EntityResult(entityClass = Category.class)},
	columns = {@ColumnResult(name = "MENU_COUNT")}
)

/* Named 네이티브 SQL 설정 */
//@NamedNativeQuery(
//	name = "Category.categoryWithMenuCount",
//	query = "SELECT\r\n"
//		  + "       A.CATEGORY_CODE, A.CATEGORY_NAME, A.REF_CATEGORY_CODE, D.MENU_COUNT\r\n"
//		  + "  FROM TBL_CATEGORY A\r\n"
//		  + "  LEFT JOIN (SELECT COUNT(*) AS MENU_COUNT, B.CATEGORY_CODE \r\n"
//		  + "               FROM TBL_CATEGORY B\r\n"
//		  + "               JOIN TBL_MENU C ON (B.CATEGORY_CODE = C.CATEGORY_CODE)\r\n"
//		  + "               GROUP BY B.CATEGORY_CODE) D ON (A.CATEGORY_CODE = D.CATEGORY_CODE)\r\n"
//		  + " ORDER BY 1",
//	resultSetMapping = "categoryWithMenuCountMap2"					// 조회결과를 매핑할 결과 매핑
//)

/* 여러 개의 정적 쿼리를 사용할 경우 */
@NamedNativeQueries({
	@NamedNativeQuery(
			name = "Category.categoryWithMenuCount",
			query = "SELECT\r\n"
				  + "       A.CATEGORY_CODE, A.CATEGORY_NAME, A.REF_CATEGORY_CODE, D.MENU_COUNT\r\n"
				  + "  FROM TBL_CATEGORY A\r\n"
				  + "  LEFT JOIN (SELECT COUNT(*) AS MENU_COUNT, B.CATEGORY_CODE \r\n"
				  + "               FROM TBL_CATEGORY B\r\n"
				  + "               JOIN TBL_MENU C ON (B.CATEGORY_CODE = C.CATEGORY_CODE)\r\n"
				  + "               GROUP BY B.CATEGORY_CODE) D ON (A.CATEGORY_CODE = D.CATEGORY_CODE)\r\n"
				  + " ORDER BY 1",
			resultSetMapping = "categoryWithMenuCountMap2"					// 조회결과를 매핑할 결과 매핑
	),
	@NamedNativeQuery(
			name = "Category.categoryInfo",
			query = "SELECT A.CATEGORY_NAME "
					  + "FROM TBL_CATEGORY A "
					  + "ORDER BY A.CATEGORY_CODE"
	)
})
@Table(name = "TBL_CATEGORY")
public class Category implements java.io.Serializable {
	private static final long serialVersionUID = 264170629153518154L;

	@Id
	@Column(name = "CATEGORY_CODE")
	private int code;
	
	@Column(name = "CATEGORY_NAME")
	private String name;
	
	@Column(name = "REF_CATEGORY_CODE")
	private Integer refCategoryCode;
	
	public Category() {
	}
	public Category(int code, String name, Integer refCategoryCode) {
		this.code = code;
		this.name = name;
		this.refCategoryCode = refCategoryCode;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getRefCategoryCode() {
		return refCategoryCode;
	}
	public void setRefCategoryCode(Integer refCategoryCode) {
		this.refCategoryCode = refCategoryCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Category [code=" + code + ", name=" + name + ", refCategoryCode=" + refCategoryCode + "]";
	}
}
