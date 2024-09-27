package com.greedy.section01.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/first/*")		// WebFilter 어노테이션에 url 경로를 넣어 해당 요청 시 filter가 동작하게 함
						    // '/*'은 모든 요청을 뜻함
public class FirstFilter implements Filter {

    public FirstFilter() {
    	
    	/* 기본생성자 */
    	/* 필터는 톰캣을 start할 시점(처음)부터 인스턴스를 미리 생성한다. */
    	System.out.println("FirstFilter 인스턴스 생성!");
    }

	public void destroy() {
		System.out.println("FirstFilter destroy 호출");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("FirstFilter doFilter 호출");
		
		/* 다음 필터 또는 서블릿으로 가기 전에 전처리를 위한 코드를 여기에 작성 */
		
		chain.doFilter(request, response);		// 다음 필터 또는 서블릿을 진행시킴
		
		System.out.println("서블릿 다녀 온 후");
		/* 서블릿에서 클라이언트로 가기 전에 후처리를 위한 코드를 여기에 작성 */
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("FirstFilter init 호출");
	}
}











