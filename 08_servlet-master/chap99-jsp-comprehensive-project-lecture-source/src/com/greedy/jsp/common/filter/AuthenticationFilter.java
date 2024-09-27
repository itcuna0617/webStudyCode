package com.greedy.jsp.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.greedy.jsp.member.model.dto.MemberDTO;

@WebFilter(urlPatterns={"/notice/*","/member/*","/board/*","/thumbnail/*"})
public class AuthenticationFilter implements Filter {

	Map<String, List<String>> permitURIList;
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hrequest = (HttpServletRequest) request;
		String uri = hrequest.getRequestURI();
		String intent = uri.replace(hrequest.getContextPath(), "");
		
		System.out.println("intent : " + intent);
		
		/* 세션에 권한이 있는지 확인하여 허용된 url에만 접근 가능하도록 설정한다. */
		HttpSession requestSession = hrequest.getSession();
		MemberDTO loginMember = (MemberDTO) requestSession.getAttribute("loginMember");
		System.out.println(loginMember);
		/* 허용 여부 변수 지정 */
		boolean isAuthorized = false;
		if(loginMember != null) {	// 로그인을 했다면
			
			boolean isPermitAdmin = permitURIList.get("adminPermitList").contains(intent);
			boolean isPermitMember = permitURIList.get("memberPermitList").contains(intent);
			boolean isPermitAll = permitURIList.get("allPermitList").contains(intent);
			
			/* 로그인 한 사람이 관리자인지 */
			if("ADMIN".equals(loginMember.getRole())) {				
				if(isPermitAdmin || isPermitMember || isPermitAll) {
					isAuthorized = true;
				}
			/* 로그인 한 사람이 일반 회원인지 */
			} else if("MEMBER".equals(loginMember.getRole())) {
				if((isPermitMember || isPermitAll) && !isPermitAdmin) {
					isAuthorized = true;
				}
			}
			
			/* 허용 여부값에 따른 처리 */
			if(isAuthorized) {
				chain.doFilter(request, response);				
			} else {
				((HttpServletResponse) response).sendError(403);
			}
		
		} else {					// 로그인을 안 했다면
			if(permitURIList.get("allPermitList").contains(intent)) {
				chain.doFilter(request, response);		
			} else {
				request.setAttribute("message", "로그인이 필요한 서비스 입니다.");
				request.getRequestDispatcher("/WEB-INF/views/common/failed.jsp").forward(hrequest, response);
			}
		} 
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
		permitURIList = new HashMap<>();
		List<String> adminPermitList = new ArrayList<>();
		List<String> memberPermitList = new ArrayList<>();
		List<String> allPermitList = new ArrayList<>();
		
		adminPermitList.add("/notice/insert");
		adminPermitList.add("/notice/update");
		
		memberPermitList.add("/notice/list");
		memberPermitList.add("/notice/detail");
		memberPermitList.add("/board/list");
		memberPermitList.add("/board/insert");
		memberPermitList.add("/board/search");
		memberPermitList.add("/thumbnail/list");
		memberPermitList.add("/thumbnail/insert");
		memberPermitList.add("/thumbnail/detail");
		
		allPermitList.add("/member/regist");
		allPermitList.add("/member/login");
		allPermitList.add("/member/logout");
		
		permitURIList.put("adminPermitList", adminPermitList);
		permitURIList.put("memberPermitList", memberPermitList);
		permitURIList.put("allPermitList", allPermitList);
		
	}
}










