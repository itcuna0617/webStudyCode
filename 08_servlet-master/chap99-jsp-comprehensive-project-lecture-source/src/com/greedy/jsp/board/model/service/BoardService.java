package com.greedy.jsp.board.model.service;

import static com.greedy.jsp.common.jdbc.JDBCTemplate.close;
import static com.greedy.jsp.common.jdbc.JDBCTemplate.commit;
import static com.greedy.jsp.common.jdbc.JDBCTemplate.getConnection;
import static com.greedy.jsp.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.greedy.jsp.board.model.dao.BoardDAO;
import com.greedy.jsp.board.model.dto.AttachmentDTO;
import com.greedy.jsp.board.model.dto.BoardDTO;
import com.greedy.jsp.board.model.dto.PageInfoDTO;

public class BoardService {
	
	private final BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
	}

	/* 페이징 처리를 위한 전체 게시물 수 조회용 메소드 */
	public int selectTotalCount() {
		
		Connection con = getConnection();
		
		int totalCount = boardDAO.selectTotalCount(con);
		
		close(con);
		
		return totalCount;
	}

	/* 게시물 전체 조회용 메소드 */
	public List<BoardDTO> selectBoardList(PageInfoDTO pageInfo) {
		
		Connection con = getConnection();
		
		List<BoardDTO> boardList = boardDAO.selectBoardList(con, pageInfo);
		
		close(con);
		
		return boardList;
	}

	/* 게시물 등록용 메소드 */
	public int insertBoard(BoardDTO newBoard) {

		Connection con = getConnection();
		
		int result = boardDAO.insertBoard(con, newBoard);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	/* 게시판 검색 결과 갯수 조회용 메소드 */
	public int searchBoardCount(String condition, String value) {

		Connection con = getConnection();
		
		int totalCount = boardDAO.searchBoardCount(con, condition, value);
		
		close(con);
		
		return totalCount;
	}

	/* 게시판 검색 결과 조회용 메소드 */
	public List<BoardDTO> searchBoardList(PageInfoDTO pageInfo, String condition, String value) {
		
		Connection con = getConnection();
		
		List<BoardDTO> boardList = boardDAO.searchBoardList(con, pageInfo, condition, value);
		
		close(con);
		
		return boardList;
	}

	/* 사진 게시글 입력용 메소드 */
	public int insertThumbnail(BoardDTO thumbnail) {
		
		Connection con = getConnection();
		
		/* 최종적으로 반환 할 result 선언 */
		int result = 0;
		
		/* 먼저 board 테이블부터 thumbnail 내용에서 insert 작업을 한다. */
		int boardResult = boardDAO.insertThumbnailContent(con, thumbnail);
		
		/* 방금 추가한 게시물 번호(sequence를 통해 생성된 번호)를 조회해 오자 */
		int boardNo = boardDAO.selectThumbnailSequence(con);
		
		/* Attachment List를 불러온다. */
		List<AttachmentDTO> fileList = thumbnail.getAttachmentList();
		
		/* 각각의 AttachmentDTO(파일 정보)들에 boardNo를 넣는다. */
		for(int i = 0 ; i < fileList.size() ; i++) {
			fileList.get(i).setRefBoardNo(boardNo);
		}
		
		/* Attachment 테이블에 list size()만큼 insert한다. */
		int attachmentResult = 0;
		for(int i = 0 ; i < fileList.size() ; i++) {
			attachmentResult += boardDAO.insertAttachment(con, fileList.get(i));
		}
		
		/* 모든 결과(하나의 트랜잭션)가 성공이면 트랜잭션 처리를 완료한다. */
		if(boardResult > 0 && attachmentResult == fileList.size()) {
			commit(con);
			result = 1;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	/* 썸네일 게시판 조회용 메소드 */
	public List<BoardDTO> selectThumbnailList() {
		
		Connection con = getConnection();
		
		List<BoardDTO> thumbnailList = boardDAO.selectThumbnailList(con);
		
		close(con);
		
		return thumbnailList;
	}

	/* 조회수 증가 및 선택된 사진 게시글 select용 메소드 */
	public BoardDTO selectOneThumbnailBoard(int no) {
		
		Connection con = getConnection();
		
		BoardDTO thumbnail = null;
		
		/* 조회수 증가 */
		int result = boardDAO.incrementBoardCount(con, no);
		
		if(result > 0) {
			thumbnail = boardDAO.selectOneThumbnailBoard(con, no);
			
			if(thumbnail != null) {
				commit(con);
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		
		close(con);
		
		return thumbnail;
	}
	
	
}




















