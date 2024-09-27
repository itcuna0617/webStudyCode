package com.ohgiraffers.section06.compositekey;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

/* @Embeddable 어노테이션을 활용하여 복합키를 위한 클래스 생성(이후 chap04의 section03에서 다시 다룰 예정) */
@Embeddable
public class MemberPk1 implements Serializable{		// 직렬화 해줄 것(안하면 에러남)			
	
	/* 
	 * 복합키를 위한 클래스에서 직렬화를 해주지 않으면 
	 * Composite-id class must implement Serializable 에러가 발생하므로
	 * 반드시 직렬화를 해 주어야 함
	 */
	private static final long serialVersionUID = -2727663364418817778L;

	@Column(name = "NICKNAME")
	private String nickname;
	
	@Column(name = "EMAIL")
	private String email;

	public MemberPk1() {
	}
	public MemberPk1(String nickname, String email) {
		this.nickname = nickname;
		this.email = email;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MemberId [nickname=" + nickname + ", email=" + email + "]";
	}
}
