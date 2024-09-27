package com.greedy.jsp.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greedy.jsp.member.model.dto.MemberDTO;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/updateMemberForm.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberNo = ((MemberDTO)request.getSession().getAttribute("loginMember")).getNo();
		
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		String nickname = request.getParameter("nickname");
		String phone = request.getParameter("phone").replace("-", "");
		String email = request.getParameter("email");
		String address = request.getParameter("zipCode") + "$" + request.getParameter("address1")
		               + "$" + request.getParameter("address2");
		
		MemberDTO requestMember = new MemberDTO();
		requestMember.setId(memberId);
		requestMember.setPwd(memberPwd);
		requestMember.setNickname(nickname);
		requestMember.setPhone(phone);
		requestMember.setEmail(email);
		requestMember.setAddress(address);
		
		System.out.println("memberController requestMember : " + requestMember);
		
		/* 정상적으로 회원 정보를 업데이트 한 경우에는 변경된 회원 정보를 DB에서 다시 조회해 온다.
		 * 조회한 회원 정보를 session에 동일한 key로 담아준 후 success.jsp로 updateMember라는 코드를
		 * 보내 alert를 띄운 후 회원 정보를 수정하는 페이지로(/member/update)경로를 이동시킨다.
		 */
		
	
	}

}






