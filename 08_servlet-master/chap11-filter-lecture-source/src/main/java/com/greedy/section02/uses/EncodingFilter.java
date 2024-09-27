package com.greedy.section02.uses;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/member/*")
public class EncodingFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		/* ServletRequest를 HttpServletRequest로 다운캐스팅 해서 getMethod()를 사용 후 요청 httpMethod 종류를 파악 */
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		
//		System.out.println(httpRequest.getMethod());
		if("POST".equals(httpRequest.getMethod())) {
			httpRequest.setCharacterEncoding("UTF-8");
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
