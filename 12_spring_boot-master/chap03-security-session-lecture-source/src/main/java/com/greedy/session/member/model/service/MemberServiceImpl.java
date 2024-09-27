package com.greedy.session.member.model.service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greedy.session.member.model.dao.MemberDAO;
import com.greedy.session.member.model.dto.AuthorityDTO;
import com.greedy.session.member.model.dto.MemberDTO;
import com.greedy.session.member.model.dto.MemberRoleDTO;
import com.greedy.session.member.model.dto.UserImpl;

@Service
public class MemberServiceImpl implements MemberService{

	private MemberDAO memberDAO;
	
	@Autowired
	public MemberServiceImpl(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	
	/* 
	 * 사용자가 입력한 아이디를 토대로 해당 회원을 조회한 후 UserDetails 타입의 객체(User객체)로
	 * 만들어 반환하는 메소드
	 */
	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		
		/* 해당 아이디의 회원 조회 */
		System.out.println("로그인 아이디: " + memberId);
		MemberDTO member = memberDAO.findMemberById(memberId);
		List<MemberRoleDTO> memberRoleList = memberDAO.findMemberRoleByMemberNo(member.getNo());
		for(MemberRoleDTO memberRole : memberRoleList) {
			AuthorityDTO authority = memberDAO.findAuthorityByAuthorityCode(memberRole.getAuthorityCode());
			memberRole.setAuthority(authority);
		}
		member.setMemberRoleList(memberRoleList);
		System.out.println("중간확인: " + member);
		
		/* 해당 아이디 회원의 권한들을 List<GrantedAuthority> 타입으로 담자 */
		List<GrantedAuthority> authorities = new ArrayList<>();
//		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
		
		if(!member.getMemberRoleList().isEmpty()) {	 // 해당 아이디의 회원이 권한을 하나라도 가졌다면
			List<MemberRoleDTO> roleList = member.getMemberRoleList();
			
			for(int i = 0; i < roleList.size(); i++) {
				AuthorityDTO authority = roleList.get(i).getAuthority();
				authorities.add(new SimpleGrantedAuthority(authority.getName()));
			}
		}
		
		/* 해당 회원의 아이디, 비밀번호, 권한들을 가지고 UserDetails 타입의 객체로 반환 */
//		return new User(member.getId(), member.getPwd(), authorities);
		
		/* User의 개념을 확장한 UserImpl로 수정(Principal 객체에 더 많은 정보를 제공) */
		UserImpl user = new UserImpl(member.getId(), member.getPwd(), authorities);
		user.setDetails(member);			// 조회한 회원 정보를 더 추가
		
		return user;
	}


	
}







