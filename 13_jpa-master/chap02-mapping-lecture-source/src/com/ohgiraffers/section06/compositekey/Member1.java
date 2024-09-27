package com.ohgiraffers.section06.compositekey;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "SECTION06_1_MEMBER")
@Table(name = "TBL_MEMBER_SIX")
public class Member1 implements java.io.Serializable {
	private static final long serialVersionUID = 1942049450505033844L;

	/* (주의!)기존의 MemberDTO와 다르니 DTO 수정할 것 */
	@EmbeddedId						// @EmbeddedId를 이용해 식별자라는 것을 적용
	private MemberPk1 memberPk;		// 복합키용 클래스(임베디드 타입)를 엔티티와 연관시킨다.
	
	@Column(name = "ADDRESS")
	private String address;

	public Member1() {
	}
	public Member1(MemberPk1 memberPk, String address) {
		this.memberPk = memberPk;
		this.address = address;
	}

	public MemberPk1 getMemberPk() {
		return memberPk;
	}

	public void setMemberPk(MemberPk1 memberPk) {
		this.memberPk = memberPk;
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
		return "Member1 [memberPk=" + memberPk + ", address=" + address + "]";
	}
}

