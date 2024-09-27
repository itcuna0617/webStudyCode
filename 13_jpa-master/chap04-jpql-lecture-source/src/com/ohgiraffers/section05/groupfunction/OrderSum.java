package com.ohgiraffers.section05.groupfunction;

public class OrderSum {
	private OrderAndOrderMenu orderCode;
	private long orderSum;
	
	public OrderSum() {
	}
	public OrderSum(OrderAndOrderMenu orderCode, long orderSum) {
		this.orderCode = orderCode;
		this.orderSum = orderSum;
	}

	public OrderAndOrderMenu getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(OrderAndOrderMenu orderCode) {
		this.orderCode = orderCode;
	}

	public long getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(long orderSum) {
		this.orderSum = orderSum;
	}

	@Override
	public String toString() {
		return "OrderSum [orderCode=" + orderCode + ", orderSum=" + orderSum + "]";
	}
}
