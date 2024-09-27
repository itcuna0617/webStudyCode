package com.greedy.crud.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.crud.menu.model.dto.CategoryDTO;
import com.greedy.crud.menu.model.dto.MenuDTO;
import com.greedy.crud.menu.model.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	private MenuService menuService;		// 단위 테스트 이후 기능이 완성된 Service 계층과 연동
	
	@Autowired
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@GetMapping("/list")
	public ModelAndView findMenuList(ModelAndView mv) {
		
//		mv.addObject("message", "메시지~");
		
		List<MenuDTO> menuList = menuService.findAllMenu();
		mv.addObject("menuList", menuList);
		
		/* templates폴더 이후의 경로를 작성하면 되고 확장자는 .html로 정해진다. */
		mv.setViewName("menu/list");
		
		return mv;
	}
	
	/* 비동기 통신을 위한 핸들러 메소드로 반환형이 String인 @ResponseBody 방식으로 처리 */
	@GetMapping(value="/category", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String findCategoryList() {
		
		Gson gson = new GsonBuilder()
				       .setDateFormat("yyyy-MM-dd")
				       .create();
		
		List<CategoryDTO> categoryList = menuService.findAllCategory();
		
		return gson.toJson(categoryList);
	}
	
	/*'/menu/regist' 요청에 실행되는 핸들러 메소드로 /menu/regist라는 문자열을 반환한다.(뷰의 이름) */
	@GetMapping("/regist")
	public void registPage() {}
	
	@PostMapping("/regist")
	public ModelAndView registMenu(ModelAndView mv, 
									MenuDTO newMenu,
									RedirectAttributes rttr
								  ) {
		
//		System.out.println("사용자가 입력한 값 받아낸 객체: " + newMenu);
		
		int result = menuService.registNewMenu(newMenu);

		if(result > 0) {
//			System.out.println("메뉴 추가 성공!");
			rttr.addFlashAttribute("successMessage", "신규 메뉴 등록에 성공하셨습니다.");
		} else {
//			System.out.println("메뉴 추가 실패!");
			rttr.addFlashAttribute("failMessage", "신규 메뉴 등록에 실패하셨습니다.");
		}
		
		mv.setViewName("redirect:/menu/list");
		
		return mv;
	}
}






