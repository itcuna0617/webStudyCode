package com.greedy.session.member.model.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/* 스프링 시큐리티의 principal 객체에서 더 구체적인 정보를 얻기 위해 확장된 User 객체 생성용 클래스 */
public class UserImpl extends User {

	private int no;									// 회원번호
	private String id;								// 회원아이디
	private String pwd;								// 회원비밀번호
	private String tempPwdYn;						// 임시비밀번호여부
	private java.sql.Date pwdChangedDatetime;		// 회원비밀번호변경일시
	private String pwdExpDate;						// 회원비밀번호만료일자
	private String name;							// 회원이름
	private java.sql.Date registDatetime;			// 회원가입일시
	private int accumLoginCount;					// 누적로그인횟수
	private int loginFailedCount;					// 로그인연속실패횟수
	private String accLockYn;						// 계정잠금여부
	private String accInactiveYn;					// 계정비활성여부
	private String accExpDate;						// 계정만료일자
	private String accExpYn;						// 계정만료여부
	private java.sql.Date accSecessionDatetime;		// 계정탈퇴일시
	private String accSecessionYn;					// 계정탈퇴여부
	
	private List<MemberRoleDTO> memberRoleList;		// 회원별권한리스트
	
	/* 매개변수 있는 생성자는 반드시 구현해야 하고 우리는 매개변수가 3개인 매개변수 있는 생성자 구현 */
	public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	/* member 객체를 전달 받아 필드를 다 초기화 해주는 메소드 작성 */
	public void setDetails(MemberDTO member) {
		this.no = member.getNo();
		this.id = member.getId();
		this.pwd = "[PROTECTED]";
		this.tempPwdYn = member.getTempPwdYn();
		this.pwdChangedDatetime = member.getPwdChangedDatetime();
		this.pwdExpDate = member.getPwdExpDate();
		this.name = member.getName();
		this.registDatetime = member.getRegistDatetime();
		this.accumLoginCount = member.getAccumLoginCount();
		this.loginFailedCount = member.getLoginFailedCount();
		this.accLockYn = member.getAccLockYn();
		this.accInactiveYn = member.getAccInactiveYn();
		this.accExpDate = member.getAccExpDate();
		this.accExpYn = member.getAccExpYn();
		this.accSecessionDatetime = member.getAccSecessionDatetime();
		this.accSecessionYn = member.getAccSecessionYn();
		this.memberRoleList = member.getMemberRoleList();
	}

	/* 나중에 필드 값들을 꺼내 쓸 일들은 있으므로 getter들은 따로따로 추가해 주자. */
	public int getNo() {
		return no;
	}
	public String getId() {
		return id;
	}
	public String getPwd() {
		return pwd;
	}
	public String getTempPwdYn() {
		return tempPwdYn;
	}
	public java.sql.Date getPwdChangedDatetime() {
		return pwdChangedDatetime;
	}
	public String getPwdExpDate() {
		return pwdExpDate;
	}
	public String getName() {
		return name;
	}
	public java.sql.Date getRegistDatetime() {
		return registDatetime;
	}
	public int getAccumLoginCount() {
		return accumLoginCount;
	}
	public int getLoginFailedCount() {
		return loginFailedCount;
	}
	public String getAccLockYn() {
		return accLockYn;
	}
	public String getAccInactiveYn() {
		return accInactiveYn;
	}
	public String getAccExpDate() {
		return accExpDate;
	}
	public String getAccExpYn() {
		return accExpYn;
	}
	public java.sql.Date getAccSecessionDatetime() {
		return accSecessionDatetime;
	}
	public String getAccSecessionYn() {
		return accSecessionYn;
	}
	public List<MemberRoleDTO> getMemberRoleList() {
		return memberRoleList;
	}

	@Override
	public String toString() {
		return "UserImpl [no=" + no + ", id=" + id + ", pwd=" + pwd + ", tempPwdYn=" + tempPwdYn
				+ ", pwdChangedDatetime=" + pwdChangedDatetime + ", pwdExpDate=" + pwdExpDate + ", name=" + name
				+ ", registDatetime=" + registDatetime + ", accumLoginCount=" + accumLoginCount + ", loginFailedCount="
				+ loginFailedCount + ", accLockYn=" + accLockYn + ", accInactiveYn=" + accInactiveYn + ", accExpDate="
				+ accExpDate + ", accExpYn=" + accExpYn + ", accSecessionDatetime=" + accSecessionDatetime
				+ ", accSecessionYn=" + accSecessionYn + ", memberRoleList=" + memberRoleList + "]";
	}

}
