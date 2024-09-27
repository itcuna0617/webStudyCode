package com.ohgiraffers.bootrestapi.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ohgiraffers.bootrestapi.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	Member findByMemberId(String string);

	Member findByMemberEmail(String memberEmail);

	/* jpql과 @Query를 활용한 구문 */
	@Query("SELECT MAX(a.memberCode) FROM Member a")	// jpql에서 엔티티 이름은 대소문자까지 완벽히 일치할 것
	int maxMemberCode();

	/* purchase 도메인 추가하면서 추가한 메소드 */
	@Query("SELECT a.memberCode FROM Member a WHERE a.memberId = ?1")
	int findMemberCodeByMemberId(String orderMemberId);
}
