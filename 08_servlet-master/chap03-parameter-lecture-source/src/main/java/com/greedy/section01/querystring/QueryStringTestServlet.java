package com.greedy.section01.querystring;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/querystring")
public class QueryStringTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QueryStringTestServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("화면단에서 Get요청 들어옴");
		
		String name = request.getParameter("name");
		System.out.println("넘겨받은 name값: " + name);
		
		int age = Integer.valueOf(request.getParameter("age"));
		System.out.println("넘겨받은 age값: " + age);
		
		java.sql.Date birthday = java.sql.Date.valueOf(request.getParameter("birthday"));
		System.out.println("넘겨받은 birthday값: " + birthday);
		
		String gender = request.getParameter("gender");
		System.out.println("넘겨받은 gender값: " + gender);
		
		String national = request.getParameter("national");
		System.out.println("넘겨받은 national값: " + national);
		
		/* 같은 키값(화면단 태그의 name값)으로 여러개의 데이터가 넘어왔을 때 getParameter는 하나만 추출할 수 있다. */
//		String hobbies = request.getParameter("hobbies");
//		System.out.println("넘겨받은 hobbies값: " + hobbies);
		
		/* getParameterValues를 통해 같은 키값으로 넘어온 parmeter들을 String[]로 반환 받을 수 있다. */
		String[] hobbies = request.getParameterValues("hobbies");
		System.out.println("넘겨받은 hobbies값: " + Arrays.toString(hobbies));
	}
}
