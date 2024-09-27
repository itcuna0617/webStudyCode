package com.ohgiraffers.section01.selectmethods;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity(name = "SECTION01_CATEGORY")

/* 엔티티와 스칼라가 섞여있는 조회 결과를 매핑하기 위한 어노테이션을 작성한다. */
/* 
 * 어노테이션 별 속성
 * @SqlResultSetMapping
 * name: 결과 매핑 이름
 * entities: @EntityResult를 사용해서 엔티티를 결과로 매핑({}안에 여러 개의 @EntityResult 가능)
 * columns: @ColumnResult를 사용해서 컬럼을 결과로 매핑({}안에 여러 개의 @ColumnResult 가능)
 * 
 * @EntityResult
 * entityClass: 결과로 사용할 엔티티 클래스를 지정
 * fields: @FieldResult를 사용해서 결과 컬럼을 필드와 매핑
 * 
 * @FieldResult
 * name: 결과를 받을 필드명
 * column: 결과 컬럼명
 * 
 * @ColumnResult
 * name: 결과 컬럼명
 */
//@SqlResultSetMapping(name = "categoryWithMenuCountMap",
//	entities = {@EntityResult(entityClass = Category.class)},
//	columns = {@ColumnResult(name = "MENU_COUNT")}
//)

/* 
 * @FieldResult를 사용해서 컬럼명과 필드명을 수동으로 직접 연결한 경우 
 * (이렇게 하면 필드에 선언한 @Column 어노테이션이 없더라도 조회된 결과가 엔티티 필드에 담기게 된다.)
 * (주의!)@FieldResult를 한번이라도 선언하면 전체 필드들에 @FieldResult 설정을 해 주어야 한다.
 */
@SqlResultSetMapping(name = "categoryWithMenuCountMap",
entities = {@EntityResult(entityClass = Category.class, fields= {
				@FieldResult(name="code", column="CATEGORY_CODE"),
				@FieldResult(name="name", column="CATEGORY_NAME"),
				@FieldResult(name="refCategoryCode", column="REF_CATEGORY_CODE"),
			})},
columns = {@ColumnResult(name = "MENU_COUNT")}
)

/* 여러개의 결과매핑을 사용할 경우 */
//@SqlResultSetMappings({
//	@SqlResultSetMapping(name = "cate1",			
//		entities = {@EntityResult(entityClass = Category.class)},
//		columns = {@ColumnResult(name = "MENU_COUNT")}
//	),
//	@SqlResultSetMapping(name = "cate2",			
//		entities = {@EntityResult(entityClass = Category.class)},
//		columns = {@ColumnResult(name = "MENU_COUNT")}
//	)
//})
@Table(name = "TBL_CATEGORY")
public class Category implements java.io.Serializable {
	private static final long serialVersionUID = 264170629153518154L;

	@Id
//	@Column(name = "CATEGORY_CODE")
	private int code;
	
//	@Column(name = "CATEGORY_NAME")
	private String name;
	
//	@Column(name = "REF_CATEGORY_CODE")
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
