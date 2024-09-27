package com.ohgiraffers.section03.projection;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "SECTION03_EMBEDDEDMENU")
@Table(name = "TBL_MENU")
public class EmbeddedMenu implements java.io.Serializable {
	private static final long serialVersionUID = 6742013428452920767L;
	
	@Id
	@Column(name = "MENU_CODE")
	private int code;
	
	@Embedded									// 임베디드 타입을 필드로 가질 수 있도록 어노테이션 적용
	private MenuInfo menuInfo;		
	
	@Column(name = "CATEGORY_CODE")
	private int categoryCode;
	
	@Column(name = "ORDERABLE_STATUS")
	private String orderableStatus;
	
	public EmbeddedMenu() {
	}
	public EmbeddedMenu(int code, MenuInfo menuInfo, int categoryCode, String orderableStatus) {
		this.code = code;
		this.menuInfo = menuInfo;
		this.categoryCode = categoryCode;
		this.orderableStatus = orderableStatus;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public MenuInfo getMenuInfo() {
		return menuInfo;
	}

	public void setMenuInfo(MenuInfo menuInfo) {
		this.menuInfo = menuInfo;
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
		return "EmbeddedMenu [code=" + code + ", menuInfo=" + menuInfo + ", categoryCode=" + categoryCode
				+ ", orderableStatus=" + orderableStatus + "]";
	}
}
