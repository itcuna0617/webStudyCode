package com.ohgiraffers.section05.access;

import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/*
 * <<< 아래에서 설명 시 프로퍼티나 메소드는 같은 표현으로 인식하면 됨 >>>
 * 클래스 레벨과 필드 레벨, 그리고 프로퍼티 레벨에서 접근방식 설정을 할 수 있다.
 * 클래스 레벨: 클래스 레벨에 @Access를 적용하면 모든 필드에 대해 적용된다.
 * 필드 레벨: 해당 필드의 접근 방식을 필드 접근으로 바꿀 수 있다.
 *          @Id(Identifier annotation)가 있는 필드는 @Access(AccessType.FIELD)효과를 낸다.(@Access를 생략해도 된다.)
 * 프로퍼티 레벨: 해당 메소드로 접근방식을 프로퍼티 접근으로 바꿀 수 있다.
 *             @Id(Identifier annotation)가 있는 메소드는 @Access(AccessType.PROPERTY)효과를 낸다.(@Access를 생략해도 된다.)
 *             
 * 클래스 레벨에 @Access를 적용하면 모든 필드에 대해 적용되지만 해당 필드나 메소드에 @Access를 같이 혼용해서
 * 적용할 수 있다.
 * 예를 들어 클래스 레벨은 AccessType.FIELD로 하고 프로퍼티 레벨에서는 AccessType.PROPERTY를 해서 Access를 혼용하는
 * 경우는 해당 getter 메소드에서 특별한 로직을 처리하거나 프로퍼티 방식을 선호하는 경우에 쓰게 되며 특별한 이유가 없다면
 * 혼용해서 쓸 필요까진 없음 (아래의 예제는 단순한 조작 로직을 첨가함)
 */
@Entity(name = "SECTION05_MEMBER")

/* 클래스 레벨 */
//@Access(AccessType.FIELD)		// 직접접근 방식       (이 때는 @Id도 필드 레벨에 하지 않으면 에러 발생)
//@Access(AccessType.PROPERTY) 	// 프로퍼티 접근 방식	(이 때는 @Id도 프로퍼티 레벨에 하지 않으면 에러 발생)
@Table(name = "TBL_MEMBER_FIVE")
public class Member implements java.io.Serializable {
	private static final long serialVersionUID = -3995771530175187767L;

	@Id							// @Id가 필드에 있으므로 필드 접근 방식이다.(@Access생략 가능)
	@Column(name = "MEMBER_NO")
	private int no;
	
//	@Access(AccessType.FIELD)	// 클래스 레벨에 하면 굳이 각각 주지 않아도 됨
	@Column(name = "MEMBER_ID")
	private String id;
	
	@Transient					// 암호는 DB에 직접적으로 저장하거나 조회하지 않을 예정
//	@Column(name = "MEMBER_PWD")
    private String pwd;			
	
	/* (주의!)아래 두 필드값은 기존의 Member와 다르니 DTO 일부분 수정할 것! */
	@Transient					// 주민등록번호도 DB에 직접적으로 저장하거나 조회하지 않을 예정
//	@Column(name = "MEMBER_rrn")
	private String rrn;			// 주민등록번호(영어로 Resident registration number)

	@Column(name = "Member_PWD_rrn")
	private String pwdRrn;		// 암호(pwd)와 주민등록번호(Rrn)을 조작해서 담을 변수

	public Member() {
	}
	public Member(int no, String id, String pwd, String rrn, String pwdRrn) {
		this.no = no;
		this.id = id;
		this.pwd = pwd;
		this.rrn = rrn;
		this.pwdRrn = pwdRrn;
	}
	
//	@Id							// @Id가 메소드에 있으므로 프로퍼티 접근 방식이다.(@Access생략 가능)
	public int getNo() {
		System.out.println("no는 getter를 사용할까?");
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		System.out.println("id는 getter를 사용할까?");
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		System.out.println("pwd는 getter를 사용할까?");
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRrn() {
		System.out.println("rrn는 getter를 사용할까?");
		return rrn;
	}
	
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	
	@Access(AccessType.PROPERTY)
	public String getPwdRrn() {
		System.out.println("pwdRrn은 getter를 사용할까?");
		return pwd + rrn + UUID.randomUUID();	// pwd와 rrn을 조작
	}
	public void setPwdRrn(String pwdRrn) {
		this.pwdRrn = pwdRrn;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Member [no=" + no + ", id=" + id + ", pwd=" + pwd + ", rrn=" + rrn + ", pwdRrn=" + pwdRrn + "]";
	}
	
}

