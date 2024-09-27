package com.ohgiraffers.section04.paging;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "SECTION04_MENU")
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
