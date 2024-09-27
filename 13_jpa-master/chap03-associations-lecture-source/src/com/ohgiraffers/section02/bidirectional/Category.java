package com.ohgiraffers.section02.bidirectional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "SECTION03_CATEGORY_BI")
@Table(name = "TBL_CATEGORY")
public class Category implements java.io.Serializable {
	private static final long serialVersionUID = 264170629153518154L;

	@Id
	@Column(name = "CATEGORY_CODE")
	private int code;
	
	@Column(name = "CATEGORY_NAME")
	private String name;
	
	@Column(name = "REF_CATEGORY_CODE")
	private Integer refCategoryCode;
	
	public Category() {
	}
	public Category(int code, String name, Integer refCategoryCode) {
		this.code = code;
		this.name = name;
		this.refCategoryCode = refCategoryCode;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getRefCategoryCode() {
		return refCategoryCode;
	}
	public void setRefCategoryCode(Integer refCategoryCode) {
		this.refCategoryCode = refCategoryCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Category [code=" + code + ", name=" + name + ", refCategoryCode=" + refCategoryCode + "]";
	}
}
