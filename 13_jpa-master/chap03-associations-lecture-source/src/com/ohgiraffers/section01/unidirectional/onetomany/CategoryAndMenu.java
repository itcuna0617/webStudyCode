package com.ohgiraffers.section01.unidirectional.onetomany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "SECTION02_CATEGORYANDMENU_ONETOMANY")
@Table(name = "TBL_CATEGORY")
public class CategoryAndMenu implements java.io.Serializable{
	private static final long serialVersionUID = -1576238854160612873L;

	@Id
	@Column(name = "CATEGORY_CODE")
	private int code;										// 상태 필드
	
	@Column(name = "CATEGORY_NAME")
	private String name;									// 상태 필드
	
	@Column(name = "REF_CATEGORY_CODE")
	private Integer refCategoryCode;						// 상태 필드
	
	/*
	 * @OneToMany: 일대다(1:N)관계라는 매핑정보이며 다중성을 나타내는 어노테이션은 필수로 사용해야 한다.
	 * @JoinColumn: 조인컬럼은 조인에 사용되는 키(부모 엔티티는 기본키) 이름을 지정한다.
	 */
	@OneToMany
	@JoinColumn(name = "CATEGORY_CODE")						// TBL_CATEGORY 테이블의 컬럼명(주의)
	private List<Menu> menuList = new ArrayList<>();		// 연관 필드(컬렉션 값 연관 필드)
															// 관례상 NPE(NullPointerException) 방지를 위해 초기화
	
	public CategoryAndMenu() {
	}
	public CategoryAndMenu(int code, String name, Integer refCategoryCode, List<Menu> menuList) {
		this.code = code;
		this.name = name;
		this.refCategoryCode = refCategoryCode;
		this.menuList = menuList;
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
	public List<Menu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CategoryAndMenu [code=" + code + ", name=" + name + ", refCategoryCode=" + refCategoryCode
				+ ", menuList=" + menuList + "]";
	}
}
