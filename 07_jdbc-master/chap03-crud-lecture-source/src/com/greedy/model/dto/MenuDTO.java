package com.greedy.model.dto;

import java.io.Serializable;

public class MenuDTO implements Serializable{
	
	private static final long serialVersionUID = 6423198462274269968L;
	
	private int menuCode;
	private String menuName;
	private int menuPrice;
	private int categoryCode;
	private String orderableStatus;
	
	public MenuDTO() {
	}
	public MenuDTO(int menuCode, String menuName, int menuPrice, int categoryCode, String orderableStatus) {
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.categoryCode = categoryCode;
		this.orderableStatus = orderableStatus;
	}
	
	public int getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "MenuDTO [menuCode=" + menuCode + ", menuName=" + menuName + ", menuPrice=" + menuPrice
				+ ", categoryCode=" + categoryCode + ", orderableStatus=" + orderableStatus + "]";
	}
}
