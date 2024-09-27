package com.ohgiraffers.section02.fieldMapping;

//import java.sql.Date;
import java.util.Date;	    // @Temporal을 사용하기 위해서는 java.util.Date나 java.util.Calendar만 가능하다. 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity(name = "SECTION02_MEMBER")	
@Table(name = "TBL_MEMBER_TWO")
public class Member implements java.io.Serializable {
	private static final long serialVersionUID = -619448697146714645L;

	/* 
	 * 필드와 컬럼을 매핑하기 위한 어노테이션
	 * @Column: 컬럼을 매핑
	 * @Temporal: 날짜 타입을 매핑
	 * @Lob: BLOB, CLOB타입을 매핑
	 * @Transient: 매핑 하지 않을 특정 필드
	 * @Enumerated: 자바의 enum타입을 매핑(section04)
	 * @Access: JPA가 엔티티에 접근하는 방식을 지정(section05)
	 */
	@Id
	@Column(name = "MEMBER_NO")
	private int no;
	
	@Column(name = "MEMBER_ID")
	private String id;
	
	@Column(name = "MEMBER_PWD")
	private String pwd;
	
	/* Transient */
//	@Column(name = "NICKNAME")
	@Transient		// 해당 필드는 매핑하지 않아서 저장 및 조회를 하지 않고 DB의 해당 컬럼에는 null값이 들어간다.(임시로 어떤 값을 보관하고 싶을 때 사용)
	private String nickname;
	
	/* Column */
	/*
	 * 컬럼은 가장 많이 사용되고 기능도 많으므로 속성에 대한 정의를 하겠음(소괄호는 기본값을 의미)
	 * name: 필드와 매핑할 테이블의 컬럼 이름
	 * insertable: 엔티티 저장 시 이 필드도 같이 저장(true)
	 * updatable: 엔티티 수정 시 이 필드도 같이 수정(true)
	 * table: 하나의 엔티티를 두 개 이상의 테이블에 매핑할 떄 사용(현재 클래스가 매핑된 테이블)
	 * nullable(DDL): null 값의 허용 여부를 설정. not null 제약조건에 해당(true)
	 * unique(DDL): 한 컬럼에 unique 제약조건을 걸 때 사용(기본값은 없고 두 컬럼 이상에 unique 제약 조건을 걸기 위해서는 클래스 레벨에서 @Table의 uniqueConstraints를 사용해야 한다.)(section03)
	 * columnDefinition(DDL): 데이터베이스 컬럼 정보를 직접 기입(필드의 자바 타입과 방언 정보를 사용해서 적절한 컬럼 타입을 생성)
	 * length(DDL): 문자 길이 제약조건, String 타입에만 사용(255)
	 * 
	 * 주로 name과 naullabe을 사용하며 insertable과 updatable은 정보를 읽기만 하고 실수로 변경하게 될 것을 미연에 방지하고자 설정한다.
	 */
//	@Column(name = "PHONE", nullable = false)   // (주의!!)기본자료형 필드일 경우 기본자료형을 wrapper 클래스로 써야 한다. (null이 들어올 수 있어야 하므로)
//	@Column(name = "PHONE")					   // nullable = true가 기본값이므로 기본자료형일 때는 nullable = false를 하는 것이 안전하다.
//	@Column(name = "PHONE", nullable = false, unique = true)
	@Column(name = "PHONE", columnDefinition = "varchar2(200) default '010-0000-0000'")
//	@Column(length = 500)
	private String phone;

	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "ADDRESS")
	@Lob			// 길이 제한이 없다시피 하게 함(DB에는 문자면CLOB타입(4기가)으로 변환되고 그 외엔 BLOB타입(4기가)으로 매핑한다.)
	private String address;
	
	/* Temporal */
	/* DB에서 SELECT TO_CHAR(A.ENROLL_DATE, 'YYYY/MM/DD HH:mm:ss') FROM TBL_MEMBER_TWO A;로 확인해 보자 */
//	(주의!)@Temporal을 쓰기 위해서는 Date형이 util.Date형이어야 한다.
	@Temporal(TemporalType.TIMESTAMP)	// DATE + TIME으로 현재 날짜 및 시간이 나옴
//	@Temporal(TemporalType.DATE)		// ORACLE에서는 TIMESTAMP와 차이가 없다.
//	@Temporal(TemporalType.TIME)	    // 1970/01/01에 시간만 맞게 나옴
	@Column(name = "ENROLL_DATE")		// @Temporal을 생략하고 Column만 쓰게 되면 TemporalType.TIMESTAMP로 정의 된다.
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

