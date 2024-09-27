package com.greedy.crud.menu.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.greedy.crud.config.Chap02CrudLectureSourceApplication;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {Chap02CrudLectureSourceApplication.class})
public class MenuControllerTests {
	
	@Autowired
	private MenuController menuController;
	
	private MockMvc mockMvc;	// MVC를 테스트 하기 위해 가짜 Http 요청을 보낼 수 있도록 지원하는 객체
	
	@BeforeEach
	public void setUp() {
		/* 가상의 Mock객체는 단위 테스트마다(테스트 할 컨트롤러의 핸들러 메소드마다) 생성해야 한다.(@BeforeEach) */
		
		/* 테스트를 진행 할 객체를 위한 가상의 Mock객체 생성 */
		mockMvc = MockMvcBuilders.standaloneSetup(menuController).build();
	}
	
	@Test
	@Disabled
	public void testFindAllMenu() throws Exception{
		mockMvc.perform(get("/menu/list"))				// get방식의 가상의 요청 보냄(해당 되는 핸들러 메소드가 동작)         
		       .andExpect(status().isOk())				// status().isOK()는 200응답(성공)을 뜻함(기대하는 상태코드 값)
		       .andExpect(forwardedUrl("menu/list"))	// 기대하는 forward Url값을 뜻함
		       .andDo(print());							// 전체적인 과정을 볼 수 있는 결과를 콘솔에 출력
	}
	
	@Test
	@Disabled
	public void testFindAllCategory() throws Exception{
		mockMvc.perform(get("/menu/category"))
		       .andExpect(status().isOk())
		       .andDo(print());
	}
	
	@Test
	public void registNewMenu() throws Exception{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("name", "부대찌개");
		params.add("price", "20000");
		params.add("categoryCode", "7");
		params.add("orderableStatus", "Y");
		
		mockMvc.perform(post("/menu/regist").params(params))
		       .andExpect(status().is3xxRedirection())
		       .andExpect(flash().attribute("successMessage", "신규 메뉴 등록에 성공하셨습니다."))
		       .andDo(print());
	}
}










