package com.ohgiraffers.section01.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 엔티티에 매핑 작업을 하기 위한 어노테이션
 * (어노테이션과 관련된 패키지는 javax.persistence이다.)
 * @Entity
 *   테이블과 매핑할 엔티티 클래스.
 * @Table
 *   엔티티 클래스에 매핑할 테이블 정보를 name속성을 사용해서 매핑한다.
 *   (생략하면 클래스 이름으로 테이블명과 매핑한다.)
 * @Id
 *   엔티티 클래스의 해당 필드와 테이블의 기본키(Primary Key)와 매핑한다.
 *   (해당 필드는 식별자 필드라고 한다)
 * @Column
 *   엔티티 클래스의 해당 필드와 테이블의 컬럼을 name속성을 사용해서 매핑한다.
 *   (매핑 어노테이션을 생략하면 필드명을 사용해서 해당 컬럼명과 매핑한다.(대소문자를 구분하는 데이터베이스를 사용하면 명시적으로 대문자 컬럼명과 매핑해야 한다.)) 
 */

//@Entity(name="MENU")			// name속성으로 해당 클래스의 엔티티 이름을 지정할 수 있다.
//@Entity							// name속성을 부여하지 않으면 클래스 이름을 그대로 사용한다.
@Entity(name = "SECTION01_MENU")	// 다른 패키지에 이름이 같은 엔티티 클래스가 있다면 이름을 반드시 지정해서 충돌(DuplicateMappingException)나지
									// 않도록 해야 하며 대소문자를 구분해서 인식한다.(중요!!) (예: entity1과 Entity1을 구분함)
@Table(name = "TBL_MENU")
public class Menu implements java.io.Serializable {
	private static final long serialVersionUID = -8747598113043577738L;
	
	@Id
	@Column(name = "MENU_CODE")
	private int code;
	
	@Column(name = "MENU_NAME")
	private String name;
	
	@Column(name = "MENU_PRICE")
	private int price;
	
	@Column(name = "CATEGORY_CODE")
	private int categoryCode;
	
	@Column(name = "ORDERABLE_STATUS")
	private String orderableStatus;
	
	public Menu() {}

	public Menu(int code, String name, int price, int categoryCode, String orderableStatus) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.categoryCode = categoryCode;
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

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getOrderableStatus() {
		return orderableStatus;
	}

	public void setOrderableStatus(String orderableStatus) {
		this.orderableStatus = orderableStatus;
	}

	@Override
	public String toString() {
		return "Menu [code=" + code + ", name=" + name + ", price=" + price + ", categoryCode=" + categoryCode
				+ ", orderableStatus=" + orderableStatus + "]";
	}
	
}

