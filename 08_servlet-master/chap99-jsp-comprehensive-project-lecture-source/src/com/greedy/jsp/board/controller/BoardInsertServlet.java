package com.greedy.jsp.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greedy.jsp.board.model.dto.BoardDTO;
import com.greedy.jsp.board.model.service.BoardService;
import com.greedy.jsp.member.model.dto.MemberDTO;

@WebServlet("/board/insert")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/board/insertBoardForm.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int categoryCode = Integer.valueOf(request.getParameter("category"));
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
//		System.out.println(categoryCode + ", " + title + ", " + body);
		int writerMemberNo = ((MemberDTO)request.getSession().getAttribute("loginMember")).getNo();
		
		BoardDTO newBoard = new BoardDTO();
		newBoard.setCategoryCode(categoryCode);
		newBoard.setTitle(title);
		newBoard.setBody(body);
		newBoard.setWriterMemberNo(writerMemberNo);
		
		BoardService boardService = new BoardService();
		int result = boardService.insertBoard(newBoard);
		
		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertBoard");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "게시판 작성에 실패하셨습니다.");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

}










