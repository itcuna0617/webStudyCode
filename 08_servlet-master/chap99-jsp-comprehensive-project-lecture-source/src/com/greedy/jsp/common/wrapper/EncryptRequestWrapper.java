package com.greedy.jsp.common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptRequestWrapper extends HttpServletRequestWrapper{

	/* 부모 쪽에 기본 생성자가 존재하지 않기 때문에 request를 전달해 주는 생성자가 필요하다. */
	public EncryptRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String key) {
		
		String value="";
		if("memberPwd".equals(key)) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			value = passwordEncoder.encode(super.getParameter(key));
		} else {
			value = super.getParameter(key);
		}
		
		return value;
	}

}
