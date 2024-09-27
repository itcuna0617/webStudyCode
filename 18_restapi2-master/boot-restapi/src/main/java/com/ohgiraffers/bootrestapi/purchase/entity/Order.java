package com.ohgiraffers.bootrestapi.purchase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_ORDER")
@SequenceGenerator(
	name = "ORDER_SEQ_GENERATOR",
	sequenceName = "SEQ_ORDER_CODE",
	initialValue = 1, allocationSize = 1
)
public class Order {
	
	@Id
	@Column(name = "ORDER_CODE")
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "ORDER_SEQ_GENERATOR"
	)
	private int orderCode;
	
	@Column(name = "PRODUCT_CODE")
	private int productCode;
	
	@Column(name = "ORDER_MEMBER")
	private int orderMember;
	
	@Column(name = "ORDER_PHONE")
	private String orderPhone;
	
	@Column(name = "ORDER_EMAIL")
	private String orderEmail;
	
	@Column(name = "ORDER_RECEIVER")
	private String orderReceiver;
	
	@Column(name = "ORDER_ADDRESS")
	private String orderAddress;
	
	@Column(name = "ORDER_AMOUNT")
	private String orderAmount;
	
	@Column(name = "ORDER_DATE")
	private String orderDate;

	public Order() {
	}
	public Order(int orderCode, int productCode, int orderMember, String orderPhone, String orderEmail,
			String orderReceiver, String orderAddress, String orderAmount, String orderDate) {
		this.orderCode = orderCode;
		this.productCode = productCode;
		this.orderMember = orderMember;
		this.orderPhone = orderPhone;
		this.orderEmail = orderEmail;
		this.orderReceiver = orderReceiver;
		this.orderAddress = orderAddress;
		this.orderAmount = orderAmount;
		this.orderDate = orderDate;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public int getOrderMember() {
		return orderMember;
	}

	public void setOrderMember(int orderMember) {
		this.orderMember = orderMember;
	}

	public String getOrderPhone() {
		return orderPhone;
	}

	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}

	public String getOrderEmail() {
		return orderEmail;
	}

	public void setOrderEmail(String orderEmail) {
		this.orderEmail = orderEmail;
	}

	public String getOrderReceiver() {
		return orderReceiver;
	}

	public void setOrderReceiver(String orderReceiver) {
		this.orderReceiver = orderReceiver;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Order [orderCode=" + orderCode + ", productCode=" + productCode + ", orderMember=" + orderMember
				+ ", orderPhone=" + orderPhone + ", orderEmail=" + orderEmail + ", orderReceiver=" + orderReceiver
				+ ", orderAddress=" + orderAddress + ", orderAmount=" + orderAmount + ", orderDate=" + orderDate + "]";
	}
}
