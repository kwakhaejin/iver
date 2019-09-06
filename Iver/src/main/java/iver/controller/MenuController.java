package iver.controller;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import iver.dto.MenuDto;
import iver.service.MenuService;

@RequestMapping("/iver/api")
@Api(value="TEST API")
@RestController
public class MenuController {
	
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private MenuService menuService;
	
	//private ObjectMapper mapper = new ObjectMapper(); //json 변환 인스턴스 생성
	
	@SuppressWarnings("null")
	@RequestMapping(value="getMenuList", method=RequestMethod.GET, produces= {"application/json; charset=UTF-8", "text/plain"})
	public String getTableList() {
		JSONObject jResult = new JSONObject();
		//return jResult.toString();
		try {
			MenuDto menu = new MenuDto();
			menu.setDefault_menu("Y");
			List<MenuDto> list = menuService.getMenuList(menu);
			
			if(list != null || list.size() > 0) {
				JSONArray menus = new JSONArray(mapper.writeValueAsString(list));
				jResult.put("menus" , menus);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("erors", e);
		}
		return jResult.toString();
	}
	 
}
