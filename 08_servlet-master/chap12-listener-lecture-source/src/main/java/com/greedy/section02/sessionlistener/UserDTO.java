package com.greedy.section02.sessionlistener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class UserDTO implements HttpSessionBindingListener{
	private String name;
	private int age;
	
	public UserDTO() {
	}
	public UserDTO(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "UserDTO [name=" + name + ", age=" + age + "]";
	}
	
	/* UserDTO 인스턴스가 HttpSession에 attribute로 추가될 때와 제거될 때(로그인과 로그아웃 시점의 의미) 동작하는 메소드들 */
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("UserDTO가 담김");
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("UserDTO 제거됨");
	}
}
