package com.greedy.section03.overriding;

public class SuperClass {				// class의 접근 제한자는 public과 default 뿐이다.

	public Object method(int num) {return null;}
	
	private void privateMethod() {}
	
	public final void finalMethod() {}
	
	protected void protectedMethod() {}
}
