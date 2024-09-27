package com.ohgiraffers.section06.compositekey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity(name = "SECTION06_2_MEMBER")
@Table(name = "TBL_MEMBER_SEVEN")
@IdClass(MemberPk2.class)				
public class Member2 implements java.io.Serializable {
	private static final long serialVersionUID = 1942049450505033844L;

	/* 복합키에 해당하는 필드마다 @Id를 달아 주어야 한다. */
	@Id								// 복합키에 해당하는 필드에 @Id 어노테이션을 적용 1
	@Column(name = "NICKNAME")
	private String nickname;
	
	@Id								// 복합키에 해당하는 필드에 @Id 어노테이션을 적용 2
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "ADDRESS")
	private String address;

	public Member2() {
	}
	public Member2(String nickname, String email, String address) {
		this.nickname = nickname;
		this.email = email;
		this.address = address;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Member2 [nickname=" + nickname + ", email=" + email + ", address=" + address + "]";
	}
}

