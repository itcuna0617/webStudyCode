package com.ohgiraffers.section04.enumtype;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "SECTION04_MEMBER")	
@Table(name = "TBL_MEMBER_FOUR")
public class Member implements java.io.Serializable {
	private static final long serialVersionUID = -3995771530175187767L;

	@Id
	@Column(name = "MEMBER_NO")
	private int no;
	
	@Column(name = "MEMBER_ID")
	private String id;
	
	@Column(name = "MEMBER_PWD")
	private String pwd;
	
	@Column(name = "NICKNAME")
	private String nickname;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "ENROLL_DATE")
	private Date enrollDate;
	
	/* Enumerated */
	@Column(name = "MEMBER_ROLE")
	@Enumerated(EnumType.STRING)	// EnumType.STRING은 enum의 해당 문자를 사용한다는 뜻이다.(자료형: varchar2(255))
//	@Enumerated(EnumType.ORDINAL)	// EnumType.ORDINAL은 enum에 정의된 순서대로 반환된 숫자값이 저장된다.(자료형: number(10,0))
	private RoleType role;			// String -> RoleType으로 수정
	
	@Column(name = "MEMBER_STATUS")
	private String status;
	
	public Member() {}

	public Member(int no, String id, String pwd, String nickname, String phone, String email, String address,
			Date enrollDate, RoleType role, String status) {	// String -> RoleType으로 수정
		this.no = no;
		this.id = id;
		this.pwd = pwd;
		this.nickname = nickname;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.enrollDate = enrollDate;
		this.role = role;
		this.status = status;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public RoleType getRole() {				// String -> RoleType으로 수정
		return role;
	}

	public void setRole(RoleType role) {	// String -> RoleType으로 수정
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Member [no=" + no + ", id=" + id + ", pwd=" + pwd + ", nickname=" + nickname + ", phone=" + phone
				+ ", email=" + email + ", address=" + address + ", enrollDate=" + enrollDate + ", role=" + role
				+ ", status=" + status + "]";
	}
}

