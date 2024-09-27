package com.ohgiraffers.section03.projection;

/* 다중열로 다양한 데이터 타입을 프로젝션해서 조회할 경우 활용할 객체 */
public class CategoryCodeAndName {

	private int code;
	private String name;
	
	public CategoryCodeAndName() {
	}
	public CategoryCodeAndName(int code, String name) {
		this.code = code;
		this.name = name;
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

	@Override
	public String toString() {
		return "CategoryCodeAndName [code=" + code + ", name=" + name + "]";
	}
}
