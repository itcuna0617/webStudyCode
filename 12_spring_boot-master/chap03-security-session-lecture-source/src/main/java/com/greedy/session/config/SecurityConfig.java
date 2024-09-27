package com.greedy.session.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.greedy.session.member.model.service.MemberService;

@Configuration
@EnableWebSecurity					// 시큐리티 설정 활성화(권한 및 권한에 따른 요청 가능 경로 포함)
public class SecurityConfig {

	/* UserDetailsService 인터페이스를 구현한 Service 계층 bean을 생성자 주입 받음 */
	private MemberService memberService;
	
	@Autowired
	public SecurityConfig(MemberService memberService) {
		this.memberService = memberService;
	}
	
	/* 1. 암호화 처리를 위한 PasswordEncoder를 bean으로 설정 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/* 2. 시큐리티 설정을 무시 할 정적 리소스들을 등록 */
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring()
				           .antMatchers("/css/**", "/js/**", "/images/**", "/lib/**");
	}
	
	/* 3. HTTP 요청에 대한 권한 설정 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		/* csrf: 토큰 위조 공격을 막기 위한 것(default가 'on'인 상태) */
		http.csrf().disable()
		
			/* 권한별 요청 가능 경로 설정 */
	    	.authorizeRequests()																						
//	    		.antMatchers("/").authenticated()								// "/"요청은 인증되어야 됨(로그인 페이지로 안내)
	    		.antMatchers(HttpMethod.GET, "/menu/**").hasRole("MEMBER")		// hasRole은 ROLE_을 달아주며 ROLE_MEMBER와 일치하면 허용하겠다는 뜻
	    		.antMatchers(HttpMethod.POST, "/menu/**").hasRole("ADMIN")
				.antMatchers("/order/**").hasRole("MEMBER")
				.antMatchers("/admin/**").hasRole("ADMIN")
		    	.anyRequest().permitAll()										// 등록되지 않은 경로는 누구나 접근 가능
		    	
		    /* 로그인 페이지 및 로그인 성공 후 포워딩 경로 설정 */
		    .and()
		    	.formLogin()													// 로그인 form을 따로 이용해 로그인 처리를 할 것이다.
		    	.loginPage("/member/login")										// login Page로써의 의미이자 해당 login Page에서 submit 요청하는 경로의 의미를 지닌다.(부연 설명 고려)
		    	.successForwardUrl("/")											// 로그인 성공 시 가게 될 페이지
		    	
		    /* 로그아웃 페이지 및 쿠키 삭제, 세션 무효화, 로그아웃 성공 후 포워딩 경로 설정 */
			.and()
				.logout()																// 로그아웃 설정
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))		// 로그아웃 시 요청 경로
				.deleteCookies("JSESSIONID")											// 쿠키 제거
				.invalidateHttpSession(true)											// Session 정보 무효화
				.logoutSuccessUrl("/")													// 로그아웃 성공 시 가게 될 페이지
				
			/* 예외 핸들링 페이지 설정 */
			.and()
			    .exceptionHandling()													// 예외 핸들링 설정
			    .accessDeniedPage("/common/denied");									// 접근 거부 시 경로 설정
		
			/* 생략 가능 */
//			http.authenticationProvider(authenticationProvider());
			
		return http.build();
	}
	
	
	/* 4. UserDetailsService 인터페이스를 구현한 Service 계층 bean으로 설정 */
	
	/* 생략 가능 */
//	@Bean
//	public UserDetailsService userDetailsService() {
//		return memberService;
//	}
	
	/* 생략 가능 */
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		
//		authProvider.setUserDetailsService(userDetailsService());
//		authProvider.setPasswordEncoder(passwordEncoder());
//		
//		return authProvider;
//	}
}






