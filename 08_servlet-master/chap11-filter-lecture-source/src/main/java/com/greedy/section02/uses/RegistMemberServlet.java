package com.greedy.section02.uses;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@WebServlet("/member/regist")
public class RegistMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		System.out.println("아이디: " + userId);
		System.out.println("비밀번호: " + password);
		System.out.println("이름: " + name);
	
		/* 
		 * 아래 코드는 로그인을 처리하는 서블릿에서 구현할 수 있는 코드를 작성해 둔 것이다.
		 * 로그인 시에는 DB에 저장된 암호화된 데이터와 사용자가 로그인 당시 입력한 비밀번호를 비교해야 한다.
		 * 하지만 이미 암호화 된 데이터는 복호화를 할 수 없다.(일방향암호화를 적용했기 때문에)
		 * BCryptPasswordEncoder는 대신 matches를 통해 평문과 암호화 된 값(다이제스트)을 인자로 넘겨주면
		 * 두 값을 활용하여 해당 평문과 암호화된 값이 일치하는 값이라는 것을 boolean형으로 반환하며 알려준다.
		 * 
		 * matches(로그인 당시 사용자가 입력한 비밀번호 평문, DB에 저장 된 다이제스트);
		 */
		
		BCryptPasswordEncoder passwordEncoder = 
				new BCryptPasswordEncoder();
		System.out.println("비밀번호가 pass01인지 확인: " 
							+ passwordEncoder.matches("pass01", password));	
		System.out.println("비밀번호가 pass02인지 확인: " 
							+ passwordEncoder.matches("pass02", password));
	}
}










