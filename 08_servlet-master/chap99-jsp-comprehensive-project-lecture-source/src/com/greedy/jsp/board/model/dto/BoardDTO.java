package com.greedy.jsp.board.model.dto;

import java.sql.Date;
import java.util.List;

import com.greedy.jsp.member.model.dto.MemberDTO;

public class BoardDTO implements java.io.Serializable{

	private static final long serialVersionUID = -4856722019109151211L;

	private int no;
	private Integer type;
	private int categoryCode;
	private CategoryDTO category;
	private String title;
	private String body;
	private int writerMemberNo;
	private MemberDTO writer;
	private int count;
	private java.sql.Date createDate;
	private String status;
	
	/* 이건 첨부파일 조회할 때 추가 함 */
	private List<AttachmentDTO> attachmentList;
	
	public BoardDTO() {
	}
	public BoardDTO(int no, Integer type, int categoryCode, CategoryDTO category, String title, String body,
			int writerMemberNo, MemberDTO writer, int count, Date createDate, String status) {
		this.no = no;
		this.type = type;
		this.categoryCode = categoryCode;
		this.category = category;
		this.title = title;
		this.body = body;
		this.writerMemberNo = writerMemberNo;
		this.writer = writer;
		this.count = count;
		this.createDate = createDate;
		this.status = status;
	}
	
	public BoardDTO(int no, Integer type, int categoryCode, CategoryDTO category, String title, String body,
			int writerMemberNo, MemberDTO writer, int count, Date createDate, String status,
			List<AttachmentDTO> attachmentList) {
		this.no = no;
		this.type = type;
		this.categoryCode = categoryCode;
		this.category = category;
		this.title = title;
		this.body = body;
		this.writerMemberNo = writerMemberNo;
		this.writer = writer;
		this.count = count;
		this.createDate = createDate;
		this.status = status;
		this.attachmentList = attachmentList;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public int getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	public CategoryDTO getCategory() {
		return category;
	}
	public void setCategory(CategoryDTO category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getWriterMemberNo() {
		return writerMemberNo;
	}
	public void setWriterMemberNo(int writerMemberNo) {
		this.writerMemberNo = writerMemberNo;
	}
	public MemberDTO getWriter() {
		return writer;
	}
	public void setWriter(MemberDTO writer) {
		this.writer = writer;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public java.sql.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.sql.Date createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<AttachmentDTO> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<AttachmentDTO> attachmentList) {
		this.attachmentList = attachmentList;
	}
	
	@Override
	public String toString() {
		return "BoardDTO [no=" + no + ", type=" + type + ", categoryCode=" + categoryCode + ", category=" + category
				+ ", title=" + title + ", body=" + body + ", writerMemberNo=" + writerMemberNo + ", writer=" + writer
				+ ", count=" + count + ", createDate=" + createDate + ", status=" + status + ", attachmentList="
				+ attachmentList + "]";
	}

}
