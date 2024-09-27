package com.greedy.section04.constructor.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = -954918020934688722L;
	
	/*
	 * 자바 빈(Java Bean)이란?
	 * JSP에서 배우게 되는 표준 액션 태그로 접근할 수 있는 자바 클래스이다.
	 * (그 외의 Framework들을 사용 시에도 필요)
	 * 자바 코드를 모르는 웹 퍼블리셔들도 자바 코드를 사용할 수 있도록 태그 형식으로 지원하는
	 * 문법을 다룰 때, 사용할 수 있도록 규칙을 지정해 놓은 java 클래스를 자바 빈(Java Bean)이라고
	 * 부른다.
	 * 
	 * 자바 빈 작성 규칙
	 * 1. 자바 빈은 특정 패키지에 속해 있어야 함.(default 패키지 사용 금지)
	 * 2. 멤버 변수의 접근 제어자는 private로 선언해야 함(캡슐화 적용)
	 * 3. 기본 생성자가 명시적으로 존재해야 한다.(매개변수 있는 생성자는 선택사항)
	 * 4. 멤버 변수에 접근 가능한 설정자(setter)와 접근자(getter)가 public으로 작성되어 있어야 함
	 * 5. 직렬화(Serializable 구현)가 되어야 한다.(선택사항) <- 직렬화는 나중에 입출력에서 다루게 됨 
	 */
	
	/* 모든 필드를 private 접근 제한으로 캡슐화를 적용하자. */
	private String id;
	private String pwd;
	private String name;
	private java.util.Date enrollDate;
	
	/* 기본 생성자 */
	public UserDTO() {}
	
	/* 매개변수 있는 생성자는 선택사항이지만 모든 필드를 초기화하는 매개변수 있는 생성자는 보통 만든다. */
	public UserDTO(String id, String pwd, String name, java.util.Date enrollDate) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.enrollDate = enrollDate;
	}
	
	/* 필드 각각에 대한 setter와 getter */
	/* setter(설정자) */
	public void setId(String id) {
		this.id = id;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEnrollDate(java.util.Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	/* getter(접근자) */
	public String getId() {
		return this.id;
	}
	public String getPwd() {
		return this.pwd;
	}
	public String getName() {
		return this.name;
	}
	public java.util.Date getEnrollDate(){
		return this.enrollDate;
	}
	
	/* 전체 필드값을 String(문자열로) 한번에 반환하는 메소드(일일이 하나씩 필드 값을 getter로 확인하기 번거롭기 때문에) */
	public String getInformation() {
		return "id: " + this.id + ", pwd: " + this.pwd + ", name: " + this.name
				+ ", enrollDate: " + this.enrollDate;
	}
}
