package com.ohgiraffers.section05.groupfunction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "SECTION05_MENUANDORDERMENU")
@Table(name = "TBL_MENU")
@SequenceGenerator(
name = "MENU_SEQ_GENERATOR",
sequenceName = "SEQ_MENU_CODE",
initialValue = 1, allocationSize = 1)
public class MenuAndOrderMenu implements java.io.Serializable {
	private static final long serialVersionUID = 6742013428452920767L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,	generator = "MENU_SEQ_GENERATOR")	
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
	
	@OneToMany(mappedBy = "menuCode")
	private List<OrderMenuAndOrderAndMenu> orderMenuList = new ArrayList<>();
	
	public MenuAndOrderMenu() {
	}
	public MenuAndOrderMenu(int code, String name, int price, int categoryCode, String orderableStatus,
			List<OrderMenuAndOrderAndMenu> orderMenuList) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.categoryCode = categoryCode;
		this.orderableStatus = orderableStatus;
		this.orderMenuList = orderMenuList;
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

	public List<OrderMenuAndOrderAndMenu> getOrderMenuList() {
		return orderMenuList;
	}

	public void setOrderMenuList(List<OrderMenuAndOrderAndMenu> orderMenuList) {
		this.orderMenuList = orderMenuList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MenuAndOrderMenu [code=" + code + ", name=" + name + ", price=" + price + ", categoryCode="
				+ categoryCode + ", orderableStatus=" + orderableStatus + ", orderMenuList=" + orderMenuList + "]";
	}
}
