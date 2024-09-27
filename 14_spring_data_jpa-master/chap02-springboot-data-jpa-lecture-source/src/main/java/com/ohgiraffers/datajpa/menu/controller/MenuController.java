package com.ohgiraffers.datajpa.menu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ohgiraffers.datajpa.common.paging.Pagenation;
import com.ohgiraffers.datajpa.common.paging.SelectCriteria;
import com.ohgiraffers.datajpa.menu.dto.MenuDTO;
import com.ohgiraffers.datajpa.menu.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	private final MenuService menuService;
	
	@Autowired
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@GetMapping("/{menuCode}")
	public ModelAndView findMenuByCode(ModelAndView mv, @PathVariable int menuCode) {

		MenuDTO menu = menuService.findMenuByCode(menuCode);
		
		mv.addObject("menu", menu);
		mv.setViewName("/menu/one");
		
		return mv;
	}
	
	@GetMapping("/list")
	public ModelAndView findMenuList(ModelAndView mv) {

		List<MenuDTO> menuList = menuService.findMenuList();
		
		mv.addObject("menuList", menuList);
		mv.setViewName("menu/list");
		
		return mv;
	}
	
	@GetMapping("/regist")
	public void registPage() {}
	
	@GetMapping(value="/category", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String findCategoryList(){

		Gson gson = new GsonBuilder()
			      .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
			      .setPrettyPrinting()
			      .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
			      .serializeNulls()
			      .disableHtmlEscaping()
			      .create();
	
		return gson.toJson(menuService.findAllCategory());
	}
	
	@PostMapping("/regist")
	public ModelAndView registMenu(ModelAndView mv, MenuDTO newMenu, RedirectAttributes rttr) {

		menuService.registNewMenu(newMenu);

		rttr.addFlashAttribute("registSuccessMessage", "메뉴 등록에 성공하셨습니다");
		mv.setViewName("redirect:/menu/list");
		
		return mv;
	}
	
	@GetMapping("/modify")
	public void modifyPage() {}
	
	@PostMapping("/modify")
	public String modifyPage(RedirectAttributes rttr, @ModelAttribute MenuDTO menu) {
		
		menuService.modifyMenu(menu);
		
		rttr.addFlashAttribute("modifySuccessMessage", "메뉴 수정에 성공하셨습니다");

		return "redirect:/menu/" + menu.getMenuCode();
	}

	@GetMapping("/search")
	public ModelAndView searchPage(HttpServletRequest request, ModelAndView mv) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = menuService.selectTotalCount(searchCondition, searchValue);

		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10;		//얘도 파라미터로 전달받아도 된다.

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;

		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
		SelectCriteria selectCriteria = null;
		if(searchValue != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		System.out.println(selectCriteria);

		List<MenuDTO> menuList = menuService.searchMenuList(selectCriteria);

		for(MenuDTO menu : menuList) {
			System.out.println(menu);
		}

		mv.addObject("menuList", menuList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("menu/search");

		return mv;
	}
}
