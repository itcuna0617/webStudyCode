package com.ohgiraffers.section02.bidirectional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "SECTION03_CATEGORYANDMENU_BI")
@Table(name = "TBL_CATEGORY")
public class CategoryAndMenu implements java.io.Serializable{
	private static final long serialVersionUID = -1576238854160612873L;

	@Id
	@Column(name = "CATEGORY_CODE")
	private int code;
	
	@Column(name = "CATEGORY_NAME")
	private String name;
	
	@Column(name = "REF_CATEGORY_CODE")
	private Integer refCategoryCode;
	
	/*
	 * @mappedBy: 객체 관계에서는 양방향 연관관계라는 직접적인 관계는 없고 서로 다른 단방향 연관관계 2개를
	 * 로직으로 묶어 양방향인 것처럼 보이게 한다.
	 * 객체의 참조는 둘인데 외래 키는 하나인 이 상황을 해결하기 외해 두 객체의 연관관계 중 하나를 정해서 테이블의
	 * 외래키를 관리해야 하는데 이를 연관관계의 주인(Owner)라고 한다.
	 * 연관관계의 주인을 정하기 위해 연관관계의 주인이 아닌 객체에 @mappedBy를 써서 연관관계의 주인 객체의 필드명을
	 * 매핑 시켜 놓으면 로직으로 양방향 관계를 적용할 수 있다.
	 * 
	 * 연관관계의 주인
	 * 데이터베이스 연관관계와 매핑되고 외래 키를 관리(등록, 수정, 삭제)하는 관리자를 뜻한다.
	 * 반면에 주인이 아닌 쪽은 외래 키를 읽기만 할 수 있다.
	 * 연관관계의 주인은 mappedBy 속성을 사용하지 않는다.
	 * 
	 * (연관관계의 주인이 아닌 객체에 @mappedBy 속성을 설정함으로써)
	 * 다대일(N:1)이나 일대다(1:N) 관계에서 다(N)에 해당하는 연관관계의 주인은 테이블에 외래 키가 있는 곳으로 정해야 한다.
	 */	
	@OneToMany(mappedBy = "category")									// mappedBy에 쓰이는 속성명은 자식 엔티티의 속성명(외래키에 해당)
	private List<MenuAndCategory> menuList = new ArrayList<>();			// (주의!)MenuDTO가 아닌 MenuAndCategoryDTO로 바꾼다.
	
	public CategoryAndMenu() {
	}
	public CategoryAndMenu(int code, String name, Integer refCategoryCode, List<MenuAndCategory> menuList) {
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
	public List<MenuAndCategory> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<MenuAndCategory> menuList) {
		this.menuList = menuList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String toString() {
		return "CategoryAndMenuDTO [code=" + code + ", name=" + name + ", refCategoryCode=" + refCategoryCode
				+ ", menuList=" + menuList + "]";
	}
}
