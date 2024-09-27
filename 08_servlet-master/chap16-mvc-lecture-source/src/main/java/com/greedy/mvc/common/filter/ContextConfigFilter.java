package com.greedy.mvc.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.greedy.mvc.common.config.ConfigLocation;

@WebFilter("/*")
public class ContextConfigFilter implements Filter {

    public ContextConfigFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if(ConfigLocation.CONNECTION_CONFIG_LOCATION == null) {
			/* 1. root 경로 추출 */
			String root = request.getServletContext().getRealPath("");
	//		System.out.println("root 경로 추출: " + root);
			
			/* 2. DB 연결 정보 파일(properties) 경로 추출 */
			String connectionInfoPath =
					request.getServletContext().getInitParameter("connection-info");
	//		System.out.println("web.xml에서 던져준 DB연결을 위한 properties 파일의 경로: " + connectionInfoPath);
			
			ConfigLocation.CONNECTION_CONFIG_LOCATION = root + connectionInfoPath;
	//		System.out.println("static 변수에 담긴 DB연결을 위한 파일 경로: " 
	//								+ ConfigLocation.CONNECTION_CONFIG_LOCATION);
		}
		
		if(ConfigLocation.MAPPER_LOCATION == null) {
			String root = request.getServletContext().getRealPath("");
			
			/* 3. query들이 담긴 파일(xml) 경로 추출 */
			String mapperInfoPath =
					request.getServletContext().getInitParameter("mapper-location");
			
			ConfigLocation.MAPPER_LOCATION = root + mapperInfoPath;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
