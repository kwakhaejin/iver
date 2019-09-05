package iver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iver.dto.MenuDto;
import iver.mapper.MenuMapper;

@Service
public class MenuServcieImpl implements MenuService{
	
	@Autowired
	private MenuMapper menuMapper;
 
	@Override
	public List<MenuDto> getMenuList(MenuDto menu) throws Exception {
		// TODO Auto-generated method stub
		return menuMapper.getMenuList(menu);
	}

}
