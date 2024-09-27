package com.ohgiraffers.bootrestapi.review.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_REVIEW")
@SequenceGenerator(
	name = "REVIEW_SEQ_GENERATOR",
	sequenceName = "SEQ_REVIEW_CODE",
	initialValue = 1, allocationSize = 1
)
public class Review {
	
	@Id
	@Column(name = "REVIEW_CODE")
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "REVIEW_SEQ_GENERATOR"
	)
    private int reviewCode;
	
	@Column(name = "PRODUCT_CODE")
    private int productCode;
	
	@Column(name = "MEMBER_CODE")
    private int memberCode;
	
	@Column(name = "REVIEW_TITLE")
    private String reviewTitle;
	
	@Column(name = "REVIEW_CONTENT")
    private String reviewContent;
	
	@Column(name = "REVIEW_CREATE_DATE")
    private String reviewCreateDate;

	public Review() {
	}
	public Review(int reviewCode, int productCode, int memberCode, String reviewTitle, String reviewContent,
			String reviewCreateDate) {
		this.reviewCode = reviewCode;
		this.productCode = productCode;
		this.memberCode = memberCode;
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
	public int getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
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
		return "Review [reviewCode=" + reviewCode + ", productCode=" + productCode + ", memberCode=" + memberCode
				+ ", reviewTitle=" + reviewTitle + ", reviewContent=" + reviewContent + ", reviewCreateDate="
				+ reviewCreateDate + "]";
	}
}
