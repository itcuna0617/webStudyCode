package com.greedy.section01.header;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/headers")
public class RequestHeaderPrintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("get요청 들어옴");
		
		/*
		 * 요청 시 전달되는 헤더라는 것이 어떤 정보들을 포함하고 있는지 확인해 보자.
		 * 헤더의 종류는 전통적으로 4가지 카테고리로 구분된다.
		 * 
		 * 1. General Header: 요청 및 응답 모두에 적용되지만 최종적으로는 body에 전송되는 것과는 관련이 없는 헤더이다.
		 * 2. Request Header: 패치 될 리소스나 클라이언트 자체에 대한 상세 정보를 포함하는 헤더이다.
		 * 3. Response Header: 위치나 서버 자체와 같은 응답에 대한 부가적인 정보를 갖는 헤더이다.
		 * 4. Entity Header: 컨텐츠 길이나 MIME 타입과 같은 엔티티 바디에 대한 상세 정보를 포함하는 헤더이다.
		 *                   (요청 및 응답 모두 사용되며, 메시지 body의 컨텐츠를 나타내기에 GET요청은 해당되지 않는다.)
		 *                   (Content-Length, Content-Type, Content-Language, Content-Encoding)
		 */
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			System.out.println(headerNames.nextElement());
		}
	}
}










