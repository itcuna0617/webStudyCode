package com.greedy.section01;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	private final List<Product> items;
	
	public ShoppingCart() {
		items = new ArrayList<>();
	}

	/* 쇼핑 카트에 상품 담기 */
	public void addItem(Product item) {
		items.add(item);
	}

	/* 쇼핑 카트의 상품을 확인(꺼내기) */
	public List<Product> getItem() {
		return items;
	}
}




