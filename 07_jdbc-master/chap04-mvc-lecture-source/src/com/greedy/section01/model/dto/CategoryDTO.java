package com.greedy.section01.model.dto;

import java.io.Serializable;

public class CategoryDTO implements Serializable{
	private static final long serialVersionUID = 1959137206920406099L;
	
	private int categoryCode;
	private String categoryName;
	private int refCategoryCode;

	public CategoryDTO() {
	}
	public CategoryDTO(int categoryCode, String categoryName, int refCategoryCode) {
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.refCategoryCode = refCategoryCode;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getRefCategoryCode() {
		return refCategoryCode;
	}

	public void setRefCategoryCode(int refCategoryCode) {
		this.refCategoryCode = refCategoryCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "★=" + categoryName;
	}
	
}
