package com.greedy.jsp.board.model.dao;

import static com.greedy.jsp.common.jdbc.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.greedy.jsp.board.model.dto.AttachmentDTO;
import com.greedy.jsp.board.model.dto.BoardDTO;
import com.greedy.jsp.board.model.dto.CategoryDTO;
import com.greedy.jsp.board.model.dto.PageInfoDTO;
import com.greedy.jsp.common.config.ConfigLocation;
import com.greedy.jsp.member.model.dto.MemberDTO;

public class BoardDAO {

	private final Properties prop;
	
	public BoardDAO() {
		prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "board/board-mapper.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* 전체 게시글 갯수 반환용 메소드 */
	public int selectTotalCount(Connection con) {

		Statement stmt = null;
		ResultSet rset = null;
		
		int totalCount = 0;
		
		String query = prop.getProperty("selectTotalCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
//				totalCount = rset.getInt("COUNT(*)");
				totalCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return totalCount;
	}

	/* 페이지에 뿌려질 게시판 리스트 조회용 메소드 */
	public List<BoardDTO> selectBoardList(Connection con, PageInfoDTO pageInfo) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<BoardDTO> boardList = null;
		
		String query = prop.getProperty("selectBoardList");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pageInfo.getStartRow());
			pstmt.setInt(2, pageInfo.getEndRow());
			
			rset = pstmt.executeQuery();
			
			boardList = new ArrayList<>();
			
			while(rset.next()) {
				BoardDTO board = new BoardDTO();
				board.setCategory(new CategoryDTO());
				board.setWriter(new MemberDTO());
				
				board.setNo(rset.getInt("BOARD_NO"));
				board.setCategoryCode(rset.getInt("CATEGORY_CODE"));
				board.getCategory().setName(rset.getString("CATEGORY_NAME"));
				board.setTitle(rset.getString("BOARD_TITLE"));
				board.setBody(rset.getString("BOARD_BODY"));
				board.setWriterMemberNo(rset.getInt("BOARD_WRITER_MEMBER_NO"));
				board.getWriter().setNickname(rset.getString("NICKNAME"));
				board.setCount(rset.getInt("BOARD_COUNT"));
				board.setCreateDate(rset.getDate("CREATED_DATE"));
				board.setStatus(rset.getString("BOARD_STATUS"));
				
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return boardList;
	}

	/* 게시판 insert용 메소드 */
	public int insertBoard(Connection con, BoardDTO newBoard) {

		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("insertBoard");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, newBoard.getCategoryCode());
			pstmt.setString(2, newBoard.getTitle());
			pstmt.setString(3, newBoard.getBody());
			pstmt.setInt(4, newBoard.getWriterMemberNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 검색 결과 게시판 갯수 반환 메소드 */
	public int searchBoardCount(Connection con, String condition, String value) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = null;
		int boardCount = 0;
		
		if(condition.equals("category")) {
			
			query = prop.getProperty("searchCategoryBoardCount");
		} else if(condition.equals("writer")) {
			
			query = prop.getProperty("searchWriterBoardCount");
		} else if(condition.equals("title")) {
			
			query = prop.getProperty("searchTitleBoardCount");
		} else if(condition.equals("content")) {
			
			query = prop.getProperty("searchBodyBoardCount");
		}
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, value);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				boardCount = rset.getInt("COUNT(*)");
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return boardCount;
	}

	/* 검색 결과 게시글 리스트 조회용 메소드 */
	public List<BoardDTO> searchBoardList(Connection con, PageInfoDTO pageInfo, String condition, String value) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = null;
		List<BoardDTO> boardList = null;
		
		if(condition.equals("category")) {
			
			query = prop.getProperty("searchCategoryBoard");
		} else if(condition.equals("writer")) {
			
			query = prop.getProperty("searchWriterBoard");
		} else if(condition.equals("title")) {
			
			query = prop.getProperty("searchTitleBoard");
		} else if(condition.equals("content")) {
			
			query = prop.getProperty("searchBodyBoard");
		}
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, value);
			pstmt.setInt(2, pageInfo.getStartRow());
			pstmt.setInt(3, pageInfo.getEndRow());
			
			rset = pstmt.executeQuery();
			
			boardList = new ArrayList<>();
			
			while(rset.next()) {
				BoardDTO board = new BoardDTO();
				board.setCategory(new CategoryDTO());
				board.setWriter(new MemberDTO());
				
				board.setNo(rset.getInt("BOARD_NO"));
				board.setCategoryCode(rset.getInt("CATEGORY_CODE"));
				board.getCategory().setName(rset.getString("CATEGORY_NAME"));
				board.setTitle(rset.getString("BOARD_TITLE"));
				board.setBody(rset.getString("BOARD_BODY"));
				board.setWriterMemberNo(rset.getInt("BOARD_WRITER_MEMBER_NO"));
				board.getWriter().setNickname(rset.getString("NICKNAME"));
				board.setCount(rset.getInt("BOARD_COUNT"));
				board.setCreateDate(rset.getDate("CREATED_DATE"));
				board.setStatus(rset.getString("BOARD_STATUS"));
				
				boardList.add(board);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return boardList;
	}
	
	/* 썸네일 제목과 내용, 작성자 insert용 메소드 */
	public int insertThumbnailContent(Connection con, BoardDTO thumbnail) {
		
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("insertThumbnailContent");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, thumbnail.getTitle());
			pstmt.setString(2, thumbnail.getBody());
			pstmt.setInt(3, thumbnail.getWriterMemberNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/* 추가된 사진 게시판 번호 조회 메소드 */
	public int selectThumbnailSequence(Connection con) {
		
		Statement stmt = null;
		ResultSet rset = null;
		
		int lastBoardNo = 0;
		
		String query = prop.getProperty("selectThumbnailSequence");
		
		try {
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				lastBoardNo = rset.getInt("CURRVAL");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return lastBoardNo;
	}

	/* Attachment 테이블에 insert */
	public int insertAttachment(Connection con, AttachmentDTO file) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAttachment");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, file.getRefBoardNo());
			pstmt.setString(2, file.getOriginalName());
			pstmt.setString(3, file.getSavedName());
			pstmt.setString(4, file.getSavePath());
			pstmt.setString(5, file.getFileType());
			pstmt.setString(6, file.getThumbnailPath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<BoardDTO> selectThumbnailList(Connection con) {

		Statement stmt = null;
		ResultSet rset = null;
		
		List<BoardDTO> thumbnailList = null;
		
		String query = prop.getProperty("selectThumbnailList");
		
		try {
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(query);
			
			thumbnailList = new ArrayList<>();
			
			while(rset.next()) {
				BoardDTO thumbnailBoard = new BoardDTO();
				MemberDTO thumbnailWriter = new MemberDTO();
				AttachmentDTO thumbnailAttachment = new AttachmentDTO();
				
				thumbnailBoard.setNo(rset.getInt("BOARD_NO"));
				thumbnailBoard.setTitle(rset.getString("BOARD_TITLE"));
				thumbnailBoard.setCount(rset.getInt("BOARD_COUNT"));
				thumbnailBoard.setCreateDate(rset.getDate("CREATED_DATE"));
				thumbnailWriter.setNickname(rset.getString("NICKNAME"));
				thumbnailAttachment.setNo(rset.getInt("ATTACHMENT_NO"));
				thumbnailAttachment.setOriginalName(rset.getString("ORIGINAL_NAME"));  
				thumbnailAttachment.setSavedName(rset.getString("SAVED_NAME"));
				thumbnailAttachment.setSavePath(rset.getString("SAVE_PATH"));
				thumbnailAttachment.setThumbnailPath(rset.getString("THUMBNAIL_PATH"));
				
				List<AttachmentDTO> attachmentList = new ArrayList<>();
				attachmentList.add(thumbnailAttachment);
				
				thumbnailBoard.setWriter(thumbnailWriter);
				thumbnailBoard.setAttachmentList(attachmentList);
				
				thumbnailList.add(thumbnailBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thumbnailList;
	}

	public int incrementBoardCount(Connection con, int no) {

		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("incrementBoardCount");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public BoardDTO selectOneThumbnailBoard(Connection con, int no) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		BoardDTO thumbnail = null;
		
		String query = prop.getProperty("selectOneThumbnailBoard");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			thumbnail = new BoardDTO();
			MemberDTO thumbnailWriter = new MemberDTO();
			List<AttachmentDTO> attachmentList = new ArrayList<>();
			
			while(rset.next()) {
				
				AttachmentDTO attachment = new AttachmentDTO();
				
				thumbnail.setNo(rset.getInt("BOARD_NO"));
				thumbnail.setTitle(rset.getString("BOARD_TITLE"));
				thumbnail.setBody(rset.getString("BOARD_BODY"));
				thumbnail.setCount(rset.getInt("BOARD_COUNT"));
				thumbnail.setCreateDate(rset.getDate("CREATED_DATE"));
				thumbnail.setWriterMemberNo(rset.getInt("BOARD_WRITER_MEMBER_NO"));
				thumbnailWriter.setNickname(rset.getString("NICKNAME"));
				attachment.setNo(rset.getInt("ATTACHMENT_NO"));
				attachment.setOriginalName(rset.getString("ORIGINAL_NAME"));
				attachment.setSavedName(rset.getString("SAVED_NAME"));
				attachment.setSavePath(rset.getString("SAVE_PATH"));
				attachment.setThumbnailPath(rset.getString("THUMBNAIL_PATH"));
				
				attachmentList.add(attachment);
			}
			thumbnail.setWriter(thumbnailWriter);
			thumbnail.setAttachmentList(attachmentList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return thumbnail;
	}
}













