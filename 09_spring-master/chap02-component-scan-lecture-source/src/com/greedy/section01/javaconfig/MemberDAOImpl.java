package com.greedy.section01.javaconfig;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/*
 * 스프링 컨테이너가 스캐닝 기능을 이용하여 해당 클래스(어노테이션이 달린 클래스)를
 * bean으로 등록할 수 있도록 어노테이션 설정을 한다.
 * 
 * @Component: 스프링에서 관리되는 객체(bean)임을 표시하기 위해 사용하는 가장 기본적인 어노테이션이다.
 * @Controller: Web MVC 코드에서 사용되는 어노테이션으로 @RequestMapping 어노테이션은
 *              해당 어노테이션이 작성 된 클래스 내에서만 사용 가능하다.
 * @Service: 비즈니스 로직이 작성된 클래스에 사용한다. 추가적인 기능은 없지만 추가적인 기능을 제공 할 가능성이
 *           있으니 계층을 명시할 때 사용한다.
 * @Repository: 플랫폼별 예외를 포착하여 PersistenceExceptionTranslationPostProcessor가
 *              DataAccessException으로 변환하여 다시 발생한다.
 * 계층이 명확하지 않은 경우에는 @Component를 사용한다. 
 */

@Service
public class MemberDAOImpl implements MemberDAO{

	private final Map<Integer, MemberDTO> memberMap;
	
	public MemberDAOImpl() {
		memberMap = new HashMap<>();
		
		memberMap.put(1,  new MemberDTO(1, "user01", "pass01", "홍길동"));
		memberMap.put(2,  new MemberDTO(2, "user02", "pass02", "유관순"));
		
		System.out.println("MemberDAOImpl 객체 지금 탄생!!");
	}
	
	@Override
	public MemberDTO selecteMember(int sequence) {
		return memberMap.get(sequence);
	}

	@Override
	public boolean insertMember(MemberDTO newMember) {
		
		int before = memberMap.size();  // 기존 회원의 마지막 번호
		
		newMember.setSequence(before + 1);
		memberMap.put(before + 1, newMember);
		
		int after = memberMap.size();
		
		return (after - before == 1) ? true : false;
	}
	
}







