package com.greedy.section02.uses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RequestWrapper extends HttpServletRequestWrapper{

	public RequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String key) {
		
		String value = "";
		if("password".equals(key)) {		    // 매개변수로 넘어온 값이 "password"이면
//			value = "비밀번호 암호화 됨";
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			value = passwordEncoder.encode(super.getParameter("password"));
		} else {								// 그 외에 나머지
			value = super.getParameter(key);	// 기존대로
		}
		
		return value;
	}

}
