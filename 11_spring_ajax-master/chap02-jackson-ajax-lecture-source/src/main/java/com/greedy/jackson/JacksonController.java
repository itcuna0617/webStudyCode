package com.greedy.jackson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class JacksonController {

	private final List<MemberDTO> memberList;
	
	public JacksonController() {
		memberList = new ArrayList<MemberDTO>();
		memberList.add(new MemberDTO(1, "홍길동", 20, new java.sql.Date(System.currentTimeMillis())));
		memberList.add(new MemberDTO(2, "강감찬", 15, new java.sql.Date(System.currentTimeMillis())));
		memberList.add(new MemberDTO(3, "안중근", 27, new java.sql.Date(System.currentTimeMillis())));
		memberList.add(new MemberDTO(4, "유관순", 17, new java.sql.Date(System.currentTimeMillis())));
	}
	
	/* 1. stream을 이용한 방법 */
	@GetMapping("jackson1")
	public void getMemberList(HttpServletResponse response) throws IOException{
		response.setContentType("application/json; charset=UTF-8");
		
		ObjectMapper mapper = new ObjectMapper();
		
		PrintWriter out = response.getWriter();
		out.print(mapper.writeValueAsString(memberList));
		
		out.flush();
		out.close();
	}
	
	/* 2. @ResponseBody를 이용한 방법 */
	@GetMapping(value = "jackson2", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String getMemberListOnResponseBody() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.writeValueAsString(memberList);
	}
	
	/* 3. @ResponseBody와 MessageConverter를 사용해서 자동 변환 후 응답 */
	@GetMapping("jackson3")
	@ResponseBody
	public List<MemberDTO> getConvertedMemberList() {
		return memberList;
	}
	
	/* 4. jsonView를 이용한 방법(MessageConverter를 사용하지 않음) - modelAndView로 반환하고 싶을 때 사용, 단, 화면단에서도 고려할 것이 있음 */
	@GetMapping("jackson4")
	public ModelAndView getMemberListInModelAndView(ModelAndView mv, HttpServletResponse response) throws JsonProcessingException {
		response.setContentType("application/json; charset=UTF-8");
		
		ObjectMapper mapper = new ObjectMapper();
		
		mv.addObject("memberList", mapper.writeValueAsString(memberList));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
}




