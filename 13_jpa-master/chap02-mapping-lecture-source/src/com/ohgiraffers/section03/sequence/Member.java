package com.ohgiraffers.section03.sequence;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

@Entity(name = "SECTION03_MEMBER")	

 /* pk를 자동으로 매핑하기 위한 전략들 */

/* 시퀀스 전략 */
/* 
 * 오라클에서 시퀀스 객체를 사용해서 기본키를 생성하기 위해서는
 *   1. DBMS에서 시퀀스 객체를 생성
 *      CREATE SEQUENCE SEQ_MEMBER_NO 
 *      START WITH 1
 *      INCREMENT BY 1
 *      NOCACHE; 
 *   2. @SequenceGenerator를 사용해서 시퀀스 생성기 등록
 *   3. sequenceName 속성의 이름으로 실제 시퀀스 객체와 매핑
 *      그러면 @Id(식별자 값)는 이 시퀀스 생성기가 할당하게 됨
 *   
 *   name: 식별자 생성기 이름
 *   sequenceName: 데이터베이스에 등록되어 있는 시퀀스 이름
 *   initialValue: DDL 처음 생성 시 시작하게 될 값
 *   allocationSize: 시퀀스 호출에 증가하는 수(성능 최적화에 사용되며 기본값은 50이다.)
 * 	  			     (JPA는 시퀀스에 접근하는 횟수를 줄이기 위해 한번에 시퀀스 값을 설정한만큼
 *                    증가시키고 나서 그만큼 메모리에 시퀀스 값을 할당한다.)
 *   catalog, schema: 데이터베이스 catalog, schema 이름
 */
//@SequenceGenerator(
//		name = "MEMBER_SEQ_GENERATOR",
//		sequenceName = "SEQ_MEMBER_NO",
//		initialValue = 1, allocationSize = 1)

/* 테이블 전략 */
/*
 * 오라클에서 테이블을 사용해서 기본키를 생성하기 위해서는
 *   1. DBMS에서 시퀀스용 테이블 생성
 *      CREATE TABLE MY_SEQUENCES (
 *      	SEQUENCE_NAME VARCHAR(255) PRIMARY KEY,
 *      	NEXT_VAL NUMBER
 *      )
 *   2. @TableGenerator를 사용해서 시퀀스 생성기 등록
 *   
 *   name: 식별자 생성기 이름
 *   table: 데이터베이스에 등록되어 있는 시퀀스 생성용 테이블 이름
 *   pkColumnName: 시퀀스 컬럼명(기본값은 SEQUENCE_NAME)
 *   valueColumnName: 시퀀스 값 컬럼명(기본값은 NEXT_VAL) 
 *   pkColumnValue: 키로 사용할 값 이름
 *   allocationSize: 시퀀스 호출에 증가하는 수(성능 최적화에 사용되며 기본값은 50이다.)
 * 				     (JPA는 시퀀스에 접근하는 횟수를 줄이기 위해 한번에 시퀀스 값을 설정한만큼
 *                    증가시키고 나서 그만큼 메모리에 시퀀스 값을 할당한다.)
 *   catalog, schema: 데이터베이스 catalog, schema 이름
 *   uniqueConstraints(DDL): 유니크 제약 조건을 지정할 수 있다.
 */
@TableGenerator(
		name = "MEMBER_SEQ_GENERATOR",
		table = "MY_SEQUENCES",
		pkColumnValue = "SEQUENCE_NAME", allocationSize = 1)

//@Table(name="TBL_MEMBER_THREE")

/* @Table의 uniqueConstraints를 활용한 컬럼 2개 이상 한번에 unique 제약조건 주기 */
@Table(name="TBL_MEMBER_THREE", uniqueConstraints = {@UniqueConstraint(		
		name = "NICKNAME_EMAIL_UNIQUE",columnNames = {"NICKNAME", "EMAIL"})})		
public class Member implements java.io.Serializable {
	private static final long serialVersionUID = -8276724828393215848L;

	/* GeneratedValue */
	@Id
	@Column(name = "MEMBER_NO")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE,
//	generator = "MEMBER_SEQ_GENERATOR")			// 시퀀스 객체를 이용한 시퀀스 생성기를 사용한다.
	@GeneratedValue(strategy = GenerationType.TABLE,
	generator = "MEMBER_SEQ_GENERATOR")			// 테이블을 이용한 시퀀스 생성기를 사용한다.
	private int no;
	
	@Column(name = "MEMBER_ID")
	private String id;
	
	@Column(name = "MEMBER_PWD", nullable=false)
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
	
	@Column(name = "MEMBER_ROLE")
	private String role;
	
	@Column(name = "MEMBER_STATUS")
	private String status;
	
	public Member() {}

	public Member(int no, String id, String pwd, String nickname, String phone, String email, String address,
			Date enrollDate, String role, String status) {
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
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

