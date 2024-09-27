package com.ohgiraffers.jpa.menu.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.ohgiraffers.jpa.menu.domain.Category;
import com.ohgiraffers.jpa.menu.domain.Menu;
import com.ohgiraffers.jpa.menu.dto.CategoryDTO;
import com.ohgiraffers.jpa.menu.dto.MenuDTO;
import com.ohgiraffers.jpa.menu.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

	private MenuRepository menuRepository;
	private ModelMapper modelMapper;			// modelMapper 빈을 선언
	
	@Autowired
	public MenuService(MenuRepository menuRepository, ModelMapper modelMapper) {
		this.menuRepository = menuRepository;
		this.modelMapper = modelMapper;
	}

	public MenuDTO findMenuByCode(int menuCode) {

		Menu menu = menuRepository.findMenuByCode(menuCode);
		
		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 MenuDTO로 반환 */
		return modelMapper.map(menu, MenuDTO.class);
	}

	public List<MenuDTO> findMenuList() {

		List<Menu> menuList = menuRepository.findAllMenu();
		
		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return menuList.stream().map(menu -> modelMapper.map(menu, MenuDTO.class)).collect(Collectors.toList());
	}

	public List<CategoryDTO> findAllCategory() {

		List<Category> categoryList = menuRepository.findAllCategory();
		
		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 List<CategoryDTO>로 반환 */
		return categoryList.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
	}

	@Transactional
	public void registNewMenu(MenuDTO newMenu) {

		menuRepository.registNewMenu(modelMapper.map(newMenu, Menu.class));
	}

	@Transactional
	public void modifyMenu(MenuDTO menu) {

		menuRepository.modifyMenu(modelMapper.map(menu, Menu.class));
		
		
	}
	
}
