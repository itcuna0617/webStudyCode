package com.ohgiraffers.section08.namedquery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name = "SECTION08_MENU")
//@NamedQuery(									// @NamedQuery 어노테이션으로 정적 쿼리를 만들고 이름을 부여
//		name = "Menu.selectMenuName",		// Menu.은 따로 기능이 있진 않지만 Named 쿼리가 영속성 유닛 단위로 관리되어 충돌을 방지하기 위해 작성함
//		query = "SELECT m from SECTION08_MENU m"
//		)

/* NamedQuery가 두 개 이상일 경우 */
@NamedQueries({									// @NamedQueries를 통해 @NameQuery들을 여러개 작성할 수 있다.
	@NamedQuery(									
			name = "Menu.selectMenuName",		
			query = "SELECT m FROM SECTION08_MENU m"
			),
	@NamedQuery(									
			name = "Menu.selectMenuNameByCode",	
			query = "SELECT m FROM SECTION08_MENU m WHERE m.code = :menuCode"
			)
})
@Table(name = "TBL_MENU")
public class Menu implements java.io.Serializable {
	private static final long serialVersionUID = 6742013428452920767L;
	
	@Id
	@Column(name = "MENU_CODE")
	private int code;
	
	@Column(name = "MENU_NAME")
	private String name;
	
	@Column(name = "MENU_PRICE")
	private int price;
	
	@Column(name = "CATEGORY_CODE", nullable = true)
	private Integer categoryCode;
	
	@Column(name = "ORDERABLE_STATUS")
	private String orderableStatus;
	
	public Menu() {
	}

	public Menu(int code, String name, int price, Integer categoryCode, String orderableStatus) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.categoryCode = categoryCode;
		this.orderableStatus = orderableStatus;
	}

	int getCode() {
		return code;
	}

	void setCode(int code) {
		this.code = code;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	int getPrice() {
		return price;
	}

	void setPrice(int price) {
		this.price = price;
	}

	Integer getCategoryCode() {
		return categoryCode;
	}

	void setCategoryCode(Integer categoryCode) {
		this.categoryCode = categoryCode;
	}

	String getOrderableStatus() {
		return orderableStatus;
	}

	void setOrderableStatus(String orderableStatus) {
		this.orderableStatus = orderableStatus;
	}

	static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Menu [code=" + code + ", name=" + name + ", price=" + price + ", categoryCode=" + categoryCode
				+ ", orderableStatus=" + orderableStatus + "]";
	}
}
