package com.ohgiraffers.section05.groupfunction;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "SECTION05_ORDERMENUANDORDERANDMENU")
@Table(name = "TBL_ORDER_MENU")
@IdClass(MemberPk.class)
public class OrderMenuAndOrderAndMenu implements Serializable{
	private static final long serialVersionUID = 2710402859295615855L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "ORDER_CODE")
	private OrderAndOrderMenu orderCode;			// 외래키이자 식별자로 PFK에 해당 되는 경우이다.
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MENU_CODE")
	private MenuAndOrderMenu menuCode;		// 외래키이자 식별자로 PFK에 해당 되는 경우이다.
	
	@Column(name = "ORDER_AMOUNT")
	private int orderAmount;

	public OrderMenuAndOrderAndMenu() {
	}
	public OrderMenuAndOrderAndMenu(OrderAndOrderMenu orderCode, MenuAndOrderMenu menuCode, int orderAmount) {
		this.orderCode = orderCode;
		this.menuCode = menuCode;
		this.orderAmount = orderAmount;
	}

	public OrderAndOrderMenu getOrder() {
		return orderCode;
	}
	public void setOrder(OrderAndOrderMenu orderCode) {
		this.orderCode = orderCode;
	}
	public MenuAndOrderMenu getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(MenuAndOrderMenu menuCode) {
		this.menuCode = menuCode;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "OrderMenuAndOrderDTO [orderCode=" + orderCode.getCode() + ", menuCode=" + menuCode.getCode() + ", orderAmount=" + orderAmount + "]";
	}
}
