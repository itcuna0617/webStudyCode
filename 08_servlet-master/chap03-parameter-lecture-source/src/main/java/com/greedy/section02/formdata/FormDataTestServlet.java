package com.greedy.section02.formdata;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/formdata")
public class FormDataTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FormDataTestServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * GET 방식 요청을 하게 되면 URLEncoder를 사용해 UTF-8로 인코딩을 자동으로 해 주지만
		 * POST 방식 요청의 경우 인코딩 방식을 명시해 주지 않으면 글자가 깨지는 현상이 발생한다.
		 * 따라서 POST 요청 처리를 위해서는 HttpServletRequest가 제공하는 setCharaterEncoding()을 활용해서
		 * UTF-8로 인코딩 처리하자.
		 */
		System.out.println("post 방식으로 넘어온 데이터의 기본 인코딩 설정: " + request.getCharacterEncoding());
		
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("인코딩 처리 후: " + request.getCharacterEncoding());
//		System.out.println(request.getParameter("name"));
		
		/*
		 * 만약, 클라이언트 쪽에서 요청한 key와 value를 일일이 확인하기 힘들다면
		 * 모든 데이터의 key를 이용하여 전송된 값들을 편하게 일괄 처리할 수도 있다.
		 */
//		Map<String, String[]> requestMap = request.getParameterMap();	// 넘어온 파라미터들을 Map으로 반환
//		Set<String> keySet = requestMap.keySet();
//		Iterator<String> keyIter = keySet.iterator();
//		
//		while(keyIter.hasNext()) {
//			String key = keyIter.next();
//			System.out.print("key값: " + key);
//			
//			System.out.println(", value값: ");
////			System.out.println(requestMap.get(key));
//			for(String str : requestMap.get(key)) {
//				System.out.println(str);
//			}
//			
//		}
		
		/*
		 * 파라미터로 전달 된 키 목록만 Enumeration으로도 확인할 수 있다.
		 * request.getParameterNames()를 이용한다.
		 * 
		 * iterator와 차이점
		 * - 스레드에 안전하다.
		 * - 컬렉션 삭제 기능은 없다.
		 */
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			System.out.println(names.nextElement());
		}
	}
}










