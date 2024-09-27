package com.greedy.section01.model.dto;

import java.io.Serializable;

public class OrderDTO implements Serializable{
	private static final long serialVersionUID = 6913544195772379650L;
	
	private int orderCode;
	private String orderDate;
	private String orderTime;
	private int totalOrderPrice;
	
	public OrderDTO() {
	}
	public OrderDTO(int orderCode, String orderDate, String orderTime, int totalOrderPrice) {
		this.orderCode = orderCode;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.totalOrderPrice = totalOrderPrice;
	}
	
	public int getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public int getTotalOrderPrice() {
		return totalOrderPrice;
	}
	public void setTotalOrderPrice(int totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "OrderDTO [orderCode=" + orderCode + ", orderDate=" + orderDate + ", orderTime=" + orderTime
				+ ", totalOrderPrice=" + totalOrderPrice + "]";
	}
}
