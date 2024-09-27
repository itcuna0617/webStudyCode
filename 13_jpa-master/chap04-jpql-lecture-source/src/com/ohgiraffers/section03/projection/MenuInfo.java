package com.ohgiraffers.section03.projection;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/*
 * JPA는 엔티티 타입 vs 값 타입(기본 값 타입(Integer, String...), 임베디드 타입, 컬렉션 값 타입(List, Map, ...)
 * 
 * 임베디드 타입(embedded type, 복합 값 타입 또는 내장 타입)
 *   새로운 값 타입을 직접 정의한 것으로 주로 기본 값 타입을 모아서 만든 하나의 타입을 말한다.
 *   엔티티의 필드중 일부분을 하나의 임베디드 타입으로 정의하면 알아보기 쉽고, 재사용성이 높게
 *   디자인할 수 있어 유지보수에 용이하다.
 *   
 *   하이버네이트는 임베디드 타입을 컴포넌트(components)라고도 부른다.
 *   (엔티티의 생명주기에 의존하므로 엔티티와 임베디드 타입은 컴포지션(composition)관계이기 때문이다.) 
 *   
 *   장점
 *   1. 재사용성이 좋다.
 *   2. 응집도가 높다
 *   3. 해당 임베디드 타입의 값만 사용하는 메소드를 엔티티에 만들어 활용할 수 있다.
 *   
 *   @Embeddable: 값 타입을 정의하기 위한 어노테이션
 *   @Embedded: 값 타입을 사용하는 곳에 적용하는 어노테이션
 *   (참고로, @Embeddable과 @Embedded 어노테이션은 둘 중 하나만 적용해도 돌아가지만 둘 다 적는것을 권장한다.)
 */
@Embeddable					// 임베디드 타입을 의미하는 클래스가 될 수 있도록 어노테이션 적용
public class MenuInfo {
	private static final long serialVersionUID = -3397539754878078529L;

	@Column(name = "MENU_NAME")
	private String name;
	@Column(name = "MENU_PRICE")
	private int price;
	
	/* 기본생성자가 필수이다. */
	public MenuInfo() {
	}
	public MenuInfo(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "EmbeddedMenu [name=" + name + ", price=" + price + "]";
	}
}
