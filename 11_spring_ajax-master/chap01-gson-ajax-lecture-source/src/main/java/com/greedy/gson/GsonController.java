package com.greedy.gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Controller
public class GsonController {
	
	private final List<MemberDTO> memberList;
	
	public GsonController() {
		memberList = new ArrayList<>();
		memberList.add(new MemberDTO(1, "홍길동", 20, new java.sql.Date(System.currentTimeMillis())));
		memberList.add(new MemberDTO(2, "유관순", 16, new java.sql.Date(System.currentTimeMillis())));
		memberList.add(new MemberDTO(3, "이순신", 40, new java.sql.Date(System.currentTimeMillis())));
		memberList.add(new MemberDTO(4, "<h1>윤봉길</h1>", 33, new java.sql.Date(System.currentTimeMillis())));
	}
	
	/* 1. stream을 이용한 방법(반환형 void) */
	@GetMapping("/gson1")
	public void getMemberList(HttpServletResponse response) throws IOException {
		System.out.println("보낼 회원: " + memberList.toString());
		
		/* 1. 자바에서 한번에 MIME타입과 인코딩 설정을 해서 넘기는 방법 */
//		response.setContentType("application/json; charset=UTF-8");
		
		/* 2. 자바에서 인코딩 설정만 넘길 경우 */ 
		response.setCharacterEncoding("UTF-8");
		
//		Gson gson = new Gson();
		
		/* Gson의 다양한 옵션 */
		Gson gson = new GsonBuilder()
				        .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")			// 원하는 날짜 포맷으로 변환
				        .setPrettyPrinting()								// JSON 문자열 이쁘게 출력
				        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)	// 네이밍 규칙(기본은 낙타봉 표기법)
				        .serializeNulls()									// 필드 값이 null이라도 직렬화
				        .disableHtmlEscaping()								// 직렬화 시 escaping을 하지 않고 태그를 그대로 살림 
				        .create();											// Gson 객체로 변환
				        
		System.out.println(gson.toJson(memberList));
		
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(memberList));
		out.flush();
		out.close();
	}
	
	/*
	 * 2. @ResponseBody를 이용한 방법(반환형이 String)
	 *    @ResponseBody는 반환되는 문자열을 뷰의 이름이 아니라 Response 객체의 body에 담는 값으로
	 *    처리되게 하고 MIME 타입과 인코딩 방식을 적용시키기 위해서는 @GetMapping 어노테이션의
	 *    produces 속성에 작성해야 적용된다.(response의 setContentType()메소드로는 적용되지 않는다.)
	 */
	@GetMapping(value="gson2", produces="application/json; charset=UTF-8")
	@ResponseBody				// 반환한 문자열이 뷰의 이름이 아님을 명시한 것으로 봐도 된다.
	public String getMemberListOnResponseBody(@RequestParam String name, @RequestParam int age) {
		
		System.out.println("이름: " + name + ", 나이: " + age);
		
		Gson gson = new GsonBuilder()
				        .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")			// 원하는 날짜 포맷으로 변환
				        .setPrettyPrinting()								// JSON 문자열 이쁘게 출력
				        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)	// 네이밍 규칙(기본은 낙타봉 표기법)
				        .serializeNulls()									// 필드 값이 null이라도 직렬화
				        .disableHtmlEscaping()								// 직렬화 시 escaping을 하지 않고 태그를 그대로 살림 
				        .create();											// Gson 객체로 변환
		        
        return gson.toJson(memberList);
	}
	
	/* 3. jsonView를 이용하여 ModelAndView를 반환하는 방법 */
	@PostMapping("/gson3")
	public ModelAndView getMemberListInModelAndView(ModelAndView mv, 
			HttpServletResponse response,
			@RequestBody String data) {			// post방식으로 넘어온 JSON문자열 받기
		
		/* Gson을 이용한 자바의 객체로 전환하기 */
//		JsonObject object = new Gson().fromJson(data, JsonObject.class);
//		String name = object.getAsJsonObject().get("name").getAsString();
//		int age = object.getAsJsonObject().get("age").getAsInt();
		
		/* MemberDTO로 바로 변환해보기 */
		MemberDTO member = new Gson().fromJson(data, MemberDTO.class);
		String name = member.getName();
		int age = member.getAge();
		
		System.out.println("넘어온 값을 자바의 자료형으로 파싱: " + name + ", " + age);
		
		/* 한글 깨짐 방지 및 MIME 타입 지정 */
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new GsonBuilder()
		        .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")			// 원하는 날짜 포맷으로 변환
		        .setPrettyPrinting()								// JSON 문자열 이쁘게 출력
		        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)	// 네이밍 규칙(기본은 낙타봉 표기법)
		        .serializeNulls()									// 필드 값이 null이라도 직렬화
		        .disableHtmlEscaping()								// 직렬화 시 escaping을 하지 않고 태그를 그대로 살림 
		        .create();
		
		mv.addObject("memberList", gson.toJson(memberList));
		mv.setViewName("jsonView");									// 등록한 bean(BeanNameViewResolver)의 이름				
		
		return mv;	
	}
}





