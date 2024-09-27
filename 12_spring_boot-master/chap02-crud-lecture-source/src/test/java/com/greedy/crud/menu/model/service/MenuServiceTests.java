package com.greedy.crud.menu.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.greedy.crud.config.Chap02CrudLectureSourceApplication;
import com.greedy.crud.menu.model.dto.CategoryDTO;
import com.greedy.crud.menu.model.dto.MenuDTO;

@SpringBootTest							// 스프링부트 환경에서 테스트를 하기 위한 어노테이션
@RunWith(SpringRunner.class)			// jUnit이 SpringRunner를 사용해 스프링에서 제공하는
                                        // @Autowired, @Bean과 같은 것들을 사용할 수 있도록
									    // 확장하는 용도
@ContextConfiguration(classes= {Chap02CrudLectureSourceApplication.class}) // 같은 설정에서 진행하기 위한 용도
public class MenuServiceTests {

	@Autowired
	private MenuService menuService;
	
	private MenuDTO newMenu;
	
	@BeforeAll
	public static void test() {
		System.out.println("전역 최초 실행");
	}
	
	@BeforeEach
	public void init() {
		System.out.println("BeforeEach 실행 확인");
		
		this.newMenu = new MenuDTO();
		this.newMenu.setName("테스트메뉴");
		this.newMenu.setPrice(30000);
		this.newMenu.setCategoryCode(4);
		this.newMenu.setOrderableStatus("Y");
	}
	
	@Test
	@Disabled                              	// jUnit이 jupiter로 넘어가면서 @Disabled로 바뀜
	public void testFindAllMenu() {
		List<MenuDTO> menuList = menuService.findAllMenu();
//		System.out.println("메뉴 조회 결과: " + menuList);
		assertTrue(!menuList.isEmpty());	// 조회가 되면 초록불이 나오도록 단정문 작성
	}
	
	@Test
	@Disabled
	public void testFindAllCategory() {
		List<CategoryDTO> categoryList = menuService.findAllCategory();
		assertTrue(!categoryList.isEmpty());
	}
	
	@Test
	public void testRegistNewMenu() {
		int result = menuService.registNewMenu(newMenu);
		assertEquals(1, result);
	}
	
	/*
	 * 대표적인 단정문들
	 * assertArrayEquals(a, b): 배열 a와 b가 일치함을 확인
	 * assertEquals(a, b): 객체 a와 b의 값이 같은지 확인(동등)
	 * assertSame(a, b): 객체 a와 b가 같은 객체임을 확인(동일)
	 * assertTrue(a): a가 참인지 확인
	 * assertNotNull(a): a 객체가 null이 아님을 확인
	 */
}








