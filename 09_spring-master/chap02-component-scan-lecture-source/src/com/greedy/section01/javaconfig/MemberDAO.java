package com.greedy.section01.javaconfig;

public interface MemberDAO {

	/* 회원 번호로 회원 정보를 조회하는 메소드 */
	MemberDTO selecteMember(int sequence);
	
	/* 회원 정보를 저장하고 결과를 리턴받는 메소드 */
	boolean insertMember(MemberDTO newMember);
}
