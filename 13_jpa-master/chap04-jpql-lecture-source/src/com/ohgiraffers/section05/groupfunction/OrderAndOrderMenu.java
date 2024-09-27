package com.ohgiraffers.section05.groupfunction;

import java.io.Serializable;
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

@Entity(name = "SECTION05_ORDERANDORDERMENU")
@Table(name = "TBL_ORDER")
@SequenceGenerator(
name = "ORDER_SEQ_GENERATOR",
sequenceName = "SEQ_ORDER_CODE",
initialValue = 1, allocationSize = 1)
public class OrderAndOrderMenu implements Serializable{
	private static final long serialVersionUID = -5208350082848419797L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,	generator = "ORDER_SEQ_GENERATOR")	
	@Column(name = "ORDER_CODE")
	private int code;
	
	@Column(name = "ORDER_DATE")
	private String date;

	@Column(name = "ORDER_TIME")
	private String time;
	
	@Column(name = "TOTAL_ORDER_PRICE")
	private int totalOrderPrice;
	
	@OneToMany(mappedBy = "orderCode")
	private List<OrderMenuAndOrderAndMenu> orderMenuList = new ArrayList<>();			

	public OrderAndOrderMenu() {
	}
	public OrderAndOrderMenu(int code, String date, String time, int totalOrderPrice,
			List<OrderMenuAndOrderAndMenu> orderMenuList) {
		this.code = code;
		this.date = date;
		this.time = time;
		this.totalOrderPrice = totalOrderPrice;
		this.orderMenuList = orderMenuList;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getTotalOrderPrice() {
		return totalOrderPrice;
	}
	public void setTotalOrderPrice(int totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}
	public List<OrderMenuAndOrderAndMenu> getMenuList() {
		return orderMenuList;
	}
	public void setMenuList(List<OrderMenuAndOrderAndMenu> orderMenuList) {
		this.orderMenuList = orderMenuList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "OrderAndOrderMenu [code=" + code + ", date=" + date + ", time=" + time + ", totalOrderPrice="
				+ totalOrderPrice + ", orderMenuList=" + orderMenuList + "]";
	}
}
