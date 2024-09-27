package com.greedy.crud.menu.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.crud.menu.model.dao.MenuDAO;
import com.greedy.crud.menu.model.dto.CategoryDTO;
import com.greedy.crud.menu.model.dto.MenuDTO;

@Service
public class MenuService {
	private final MenuDAO menuDAO;
	
	@Autowired
	public MenuService(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}
	
	public List<MenuDTO> findAllMenu() {
		return menuDAO.findAllMenu();
	}
	
	public List<CategoryDTO> findAllCategory() {
		return menuDAO.findAllCategory();
	}
	
	@Transactional
	public int registNewMenu(MenuDTO newMenu) {
		return menuDAO.registNewMenu(newMenu);
	}
}
