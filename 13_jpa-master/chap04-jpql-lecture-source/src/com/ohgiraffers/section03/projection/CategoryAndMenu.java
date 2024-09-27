package com.ohgiraffers.section03.projection;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "SECTION03_CATEGORYANDMENU")
@Table(name = "TBL_CATEGORY")
public class CategoryAndMenu implements java.io.Serializable{
	private static final long serialVersionUID = -1576238854160612873L;

	@Id
	@Column(name = "CATEGORY_CODE")
	private int code;
	
	@Column(name = "CATEGORY_NAME")
	private String name;
	
	@Column(name = "REF_CATEGORY_CODE")
	private Integer refCategoryCode;
	
	@OneToMany(mappedBy = "category")
	private List<MenuAndCategory> menuList = new ArrayList<>();			
	
	public CategoryAndMenu() {
	}
	public CategoryAndMenu(int code, String name, Integer refCategoryCode, List<MenuAndCategory> menuList) {
		this.code = code;
		this.name = name;
		this.refCategoryCode = refCategoryCode;
		this.menuList = menuList;
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
	public List<MenuAndCategory> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<MenuAndCategory> menuList) {
		this.menuList = menuList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String toString() {
		return "CategoryAndMenu [code=" + code + ", name=" + name + ", refCategoryCode=" + refCategoryCode
				+ ", menuList=" + menuList + "]";
	}
}
