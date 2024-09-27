package com.ohgiraffers.section06.compositekey;

import java.io.Serializable;

import javax.persistence.Entity;

public class MemberPk2 implements Serializable{		// 직렬화 해줄 것(안하면 에러남)		
	private static final long serialVersionUID = -2727663364418817778L;

	private String nickname;
	private String email;

	public MemberPk2() {
	}
	public MemberPk2(String nickname, String email) {
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
