package com.ohgiraffers.bootrestapi.review.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ohgiraffers.bootrestapi.member.entity.Member;

@Entity
@Table(name = "TBL_REVIEW")
public class ReviewAndMember {
	
	@Id
	@Column(name = "REVIEW_CODE")
    private int reviewCode;
	
	@Column(name = "PRODUCT_CODE")
    private int productCode;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_CODE")
    private Member member;
	
	@Column(name = "REVIEW_TITLE")
    private String reviewTitle;
	
	@Column(name = "REVIEW_CONTENT")
    private String reviewContent;
	
	@Column(name = "REVIEW_CREATE_DATE")
    private String reviewCreateDate;

	public ReviewAndMember() {
	}
	public ReviewAndMember(int reviewCode, int productCode, Member member, String reviewTitle, String reviewContent,
			String reviewCreateDate) {
		this.reviewCode = reviewCode;
		this.productCode = productCode;
		this.member = member;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewCreateDate = reviewCreateDate;
	}
	public int getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(int reviewCode) {
		this.reviewCode = reviewCode;
	}
	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getReviewCreateDate() {
		return reviewCreateDate;
	}
	public void setReviewCreateDate(String reviewCreateDate) {
		this.reviewCreateDate = reviewCreateDate;
	}
	
	@Override
	public String toString() {
		return "ReviewAndMember [reviewCode=" + reviewCode + ", productCode=" + productCode + ", member=" + member
				+ ", reviewTitle=" + reviewTitle + ", reviewContent=" + reviewContent + ", reviewCreateDate="
				+ reviewCreateDate + "]";
	}
}
