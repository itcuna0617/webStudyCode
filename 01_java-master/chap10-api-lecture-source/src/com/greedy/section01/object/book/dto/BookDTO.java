package com.greedy.section01.object.book.dto;

import java.util.Objects;

public class BookDTO {

	/* 책 정보를 관리할 수 있는 DTO 클래스를 작성하자. */
	private int number;		// 책 번호
	private String title; 	// 책 제목
	private String author;	// 저자
	private int price; 		// 책 가격
	
	public BookDTO() {
	}
	public BookDTO(int number, String title, String author, int price) {
		this.number = number;
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
//	@Override
//	public String toString() {
//		return "BookDTO [number=" + number + ", title=" + title + ", author=" + author + ", price=" + price + "]";
//	}
	
	/* BookDTO 객체간의 동등 비교를 위해 equals() 메소드를 오버라이딩 */
//	@Override
//	public boolean equals(Object obj) {
//		BookDTO book = null;
//		if(obj instanceof BookDTO) {
//			book = (BookDTO)obj;
//		}
//		if(this.number == book.number && this.title.equals(book.title) && 
//				this.author.equals(book.author) && this.price == book.price) {
//			return true;
//		}
//		return false;
//	}
	
	/* hashCode와 equals를 자동완성 해서 일반적으로 사용하게 된다. */
	@Override
	public int hashCode() {
		return Objects.hash(author, number, price, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookDTO other = (BookDTO) obj;
		return Objects.equals(author, other.author) && number == other.number && price == other.price
				&& Objects.equals(title, other.title);
	}
}
