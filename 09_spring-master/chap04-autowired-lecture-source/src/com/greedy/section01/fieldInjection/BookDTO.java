package com.greedy.section01.fieldInjection;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("book")
public class BookDTO {
	private int sequence;					// 시퀀스 번호
	private int bookNum;					// 책번호
	private String title;					// 제목
	private String author;					// 작가
	private String publisher;				// 출판사
	private java.util.Date createDate;		// 출판일
	
	public BookDTO() {
	}
	public BookDTO(int sequence, int bookNum, String title, String author, String publisher, Date createDate) {
		this.sequence = sequence;
		this.bookNum = bookNum;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.createDate = createDate;
	}
	
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public int getBookNum() {
		return bookNum;
	}
	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public String toString() {
		return "BookDTO [sequence=" + sequence + ", bookNum=" + bookNum + ", title=" + title + ", author=" + author
				+ ", publisher=" + publisher + ", createDate=" + createDate + "]";
	}
}
