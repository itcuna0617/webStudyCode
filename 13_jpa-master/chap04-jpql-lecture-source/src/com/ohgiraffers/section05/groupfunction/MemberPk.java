package com.ohgiraffers.section05.groupfunction;

import java.io.Serializable;

public class MemberPk implements Serializable{				
	private static final long serialVersionUID = -2727663364418817778L;

	private OrderAndOrderMenu orderCode;		// 복합키의 필드가 외래키일 때는 타입에 주의해서 작성할 것!(다른 엔티티 객체 타입이다.)
	private MenuAndOrderMenu menuCode;   // 복합키의 필드가 외래키일 때는 타입에 주의해서 작성할 것!(다른 엔티티 객체 타입이다.)
	
	public MemberPk() {
	}
	public MemberPk(OrderAndOrderMenu orderCode, MenuAndOrderMenu menuCode) {
		this.orderCode = orderCode;
		this.menuCode = menuCode;
	}

	public OrderAndOrderMenu getOrder() {
		return orderCode;
	}
	public void setOrder(OrderAndOrderMenu order) {
		this.orderCode = order;
	}
	public MenuAndOrderMenu getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(MenuAndOrderMenu menuCode) {
		this.menuCode = menuCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "MemberPk [orderCode=" + orderCode + ", menuCode=" + menuCode + "]";
	}
}
