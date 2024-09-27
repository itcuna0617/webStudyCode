package com.greedy.jsp.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greedy.jsp.board.model.dto.BoardDTO;
import com.greedy.jsp.board.model.service.BoardService;

@WebServlet("/thumbnail/list")
public class ThumbnailSelectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* insert 작업 전 */
//		String path = "/WEB-INF/views/thumbnail/thumbnailList.jsp";
//		
//		request.getRequestDispatcher(path).forward(request, response);
		
		/* insert 한 후 다시 목록 조회할 것 */
		BoardService boardService = new BoardService();
		
		/* 이번 예제는 페이징 처리 없이 진행 */
		List<BoardDTO> thumbnailList = boardService.selectThumbnailList();
		
		for(BoardDTO b : thumbnailList) {
			System.out.println(b);
		}
		
		String path = "";
		if(!thumbnailList.isEmpty()) {
			path = "/WEB-INF/views/thumbnail/thumbnailList.jsp";
			request.setAttribute("thumbnailList", thumbnailList);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "썸네일 게시판 조회 실패!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}













