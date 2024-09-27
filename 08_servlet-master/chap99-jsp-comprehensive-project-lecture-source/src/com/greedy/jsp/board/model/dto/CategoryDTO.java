package com.greedy.jsp.board.model.dto;

public class CategoryDTO implements java.io.Serializable{

	private static final long serialVersionUID = 7207248429844598472L;

	private int code;
	private String name;
	
	public CategoryDTO() {
	}
	public CategoryDTO(int code, String name) {
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CategoryDTO [code=" + code + ", name=" + name + "]";
	}
	
	
}
