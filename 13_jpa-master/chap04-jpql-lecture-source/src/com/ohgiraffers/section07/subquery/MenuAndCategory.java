package com.ohgiraffers.section07.subquery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "SECTION07_MENUANDCATEGORY")
@Table(name = "TBL_MENU")
public class MenuAndCategory implements java.io.Serializable {
	private static final long serialVersionUID = -8153671423892313687L;

	@Id
	@Column(name = "MENU_CODE")
	private int code;
	
	@Column(name = "MENU_NAME")
	private String name;
	
	@Column(name = "MENU_PRICE")
	private int price;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_CODE")
	private CategoryAndMenu category;			
	
	@Column(name = "ORDERABLE_STATUS")
	private String orderableStatus;
	
	public MenuAndCategory() {
	}
	public MenuAndCategory(int code, String name, int price, CategoryAndMenu category, String orderableStatus) {
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
	public CategoryAndMenu getCategory() {
		return category;
	}
	public void setCategory(CategoryAndMenu category) {
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
		return "MenuAndCategory [code=" + code + ", name=" + name + ", price=" + price + ", category=" + category.getName()
				+ ", orderableStatus=" + orderableStatus + "]";
	}
}
