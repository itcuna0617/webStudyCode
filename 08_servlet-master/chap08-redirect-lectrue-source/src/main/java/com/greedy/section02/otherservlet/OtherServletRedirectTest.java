package com.greedy.section02.otherservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/otherservlet")
public class OtherServletRedirectTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("나 otherservlet이야");
		
		/* redirect해서 재요청을 할 서블릿에게 쿼리스트링 방식의 파라미터 개념으로 값을 넘겨줄 수 있다. */
		response.sendRedirect("redirect?test=abc");			// abc라는 값을 test라는 이름으로 넘겨보기
		
		/* request의 attribute로는 redirect 시 값을 다른 서블릿에게 넘겨줄 수 없다. */
//		request.setAttribute("test", "abc");
//		response.sendRedirect("redirect");
		
		/* 
		 * redirect 시에는 request의 attribute 개념으로 다른 서블릿에게 값(상태)을 전달할 수 없다.
		 * 왜냐하면 다음 서블릿으로 클라이언트가 새로 요청하면 기존의 request와 response 객체가 아닌
		 * 새로운 request와 response 객체가 만들어지며 요청하기 때문이다.
		 * 
		 * 추가로, cookie나 session같은 공간을 활용하는 방법을 사용해서 값을 공유할 수도 있다.
		 */
		
		/*
		 * 포워딩 vs 리다이렉트
		 * - request나 response 객체를 유지하여 요청 처리를 하고 싶다면 포워딩을 사용하자.
		 * - 클라이언트(브라우저)가 새로고침을 할 때마다 처음 요청하는 서블릿을 다시 호출하게 하고 싶지 않다면
		 *   리다이렉트를 사용하자.(특히, 처음 요청하는 서블릿이 insert작업을 하는 경우 고려할 것)
		 *   
		 * - 포워딩은 request의 attribute를 활용하여 다른 서블릿으로 값을 넘겨줄 수 있다.
		 * - 리다이렉트는 쿼리스트링 형태로 parameter의 개념을 활용하여 다른 서블릿으로 값을 넘겨줄 수 있다.
		 */
	}
}
