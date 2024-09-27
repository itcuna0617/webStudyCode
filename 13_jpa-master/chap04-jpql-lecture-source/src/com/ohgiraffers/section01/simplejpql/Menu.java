package com.ohgiraffers.section01.simplejpql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "SECTION01_MENU")
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

	public Integer getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(Integer categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getOrderableStatus() {
		return orderableStatus;
	}

	public void setOrderableStatus(String orderableStatus) {
		this.orderableStatus = orderableStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Menu [code=" + code + ", name=" + name + ", price=" + price + ", categoryCode=" + categoryCode
				+ ", orderableStatus=" + orderableStatus + "]";
	}
}
