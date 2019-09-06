package iver.dto;

import java.util.List; 
import lombok.Data;

@Data
public class MenuDto {
	 int menu_id;
	 String menu_name;
	 String menu_desc;
	 int parent_id;
	 String use_yn;
	 int menu_order;
	 String default_menu;
	 String url;
	
	
	
	 List<MenuDto> sub_menus;



	public int getMenu_id() {
		return menu_id;
	}



	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}



	public String getMenu_name() {
		return menu_name;
	}



	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}



	public String getMenu_desc() {
		return menu_desc;
	}



	public void setMenu_desc(String menu_desc) {
		this.menu_desc = menu_desc;
	}



	public int getParent_id() {
		return parent_id;
	}



	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}



	public String getUse_yn() {
		return use_yn;
	}



	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}



	public int getMenu_order() {
		return menu_order;
	}



	public void setMenu_order(int menu_order) {
		this.menu_order = menu_order;
	}



	public String getDefault_menu() {
		return default_menu;
	}



	public void setDefault_menu(String default_menu) {
		this.default_menu = default_menu;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public List<MenuDto> getSub_menus() {
		return sub_menus;
	}



	public void setSub_menus(List<MenuDto> sub_menus) {
		this.sub_menus = sub_menus;
	}
	
	
	
}
