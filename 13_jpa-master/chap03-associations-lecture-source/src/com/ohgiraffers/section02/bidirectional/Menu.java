package com.ohgiraffers.section02.bidirectional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "SECTION03_MENU_BI")
@Table(name = "TBL_MENU")
public class Menu implements java.io.Serializable {
	private static final long serialVersionUID = -7605368788188471945L;

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
	
	public Menu() {
	}
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
