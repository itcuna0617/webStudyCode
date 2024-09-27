package com.ohgiraffers.bootrestapi.purchase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ohgiraffers.bootrestapi.product.entity.Product;

@Entity
@Table(name = "TBL_ORDER")
public class OrderAndProduct {
	
	@Id
	@Column(name = "ORDER_CODE")
	private int orderCode;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_CODE")
	private Product product;
	
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

	public OrderAndProduct() {
	}
	public OrderAndProduct(int orderCode, Product product, int orderMember, String orderPhone, String orderEmail,
			String orderReceiver, String orderAddress, String orderAmount, String orderDate) {
		this.orderCode = orderCode;
		this.product = product;
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
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
		return "OrderAndProduct [orderCode=" + orderCode + ", product=" + product + ", orderMember=" + orderMember
				+ ", orderPhone=" + orderPhone + ", orderEmail=" + orderEmail + ", orderReceiver=" + orderReceiver
				+ ", orderAddress=" + orderAddress + ", orderAmount=" + orderAmount + ", orderDate=" + orderDate + "]";
	}
}
