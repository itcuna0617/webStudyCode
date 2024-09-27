package com.greedy.section01.properties;

import java.util.Date;

public class Bread extends Product{
	private java.util.Date bakedDate;

	public Bread() {
	}
	public Bread(Date bakedDate) {
		this.bakedDate = bakedDate;
	}
	public Bread(String name, int price, Date bakedDate) {
		super(name, price);
		this.bakedDate = bakedDate;
	}
	
	public java.util.Date getBakedDate() {
		return bakedDate;
	}

	public void setBakedDate(java.util.Date bakedDate) {
		this.bakedDate = bakedDate;
	}

	@Override
	public String toString() {
		return super.toString() + "Bread [bakedDate=" + bakedDate + "]";
	}
}
