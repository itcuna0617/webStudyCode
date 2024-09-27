package com.ohgiraffers.section01.unidirectional.manytoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "SECTION01_MENUANDCATEGORY_MANYTOONE")
@Table(name = "TBL_MENU")
public class MenuAndCategory implements java.io.Serializable {
	private static final long serialVersionUID = -8153671423892313687L;

	@Id
	@Column(name = "MENU_CODE")
	private int code;							// 상태 필드
	
	@Column(name = "MENU_NAME")
	private String name;						// 상태 필드
	
	@Column(name = "MENU_PRICE")
	private int price;							// 상태 필드
	
	/*
	 * @ManyToOne: 다대일(N:1)관계라는 매핑정보이며 다중성을 나타내는 어노테이션은 필수로 사용해야 한다.
	 * @JoinColumn: 조인컬럼은 조인에 사용되는 키(자식 엔티티는 외래키) 이름을 지정한다.
	 */
	@ManyToOne
	@JoinColumn(name = "CATEGORY_CODE")			// TBL_MENU 테이블의 컬럼명(주의)
	private Category category;					// 연관 필드(단일 값 연관 필드)
	
	@Column(name = "ORDERABLE_STATUS")
	private String orderableStatus;				// 상태 필드
	
	public MenuAndCategory() {
	}
	public MenuAndCategory(int code, String name, int price, Category category, String orderableStatus) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.category = category;
		this.orderableStatus = orderableStatus;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getOrderableStatus() {
		return orderableStatus;
	}
	public void setOrderableStatus(String orderableStatus) {
		this.orderableStatus = orderableStatus;
	}
	
	@Override
	public String toString() {
		return "MenuAndCategory [code=" + code + ", name=" + name + ", price=" + price + ", category=" + category
				+ ", orderableStatus=" + orderableStatus + "]";
	}
}
