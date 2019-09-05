package iver.service;

import java.util.List;

import iver.dto.MenuDto;

public interface MenuService {
	List<MenuDto> getMenuList(MenuDto menu) throws Exception;
	
}
