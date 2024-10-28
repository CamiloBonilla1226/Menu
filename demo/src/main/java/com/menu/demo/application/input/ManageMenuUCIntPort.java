package com.menu.demo.application.input;

import java.util.List;

import com.menu.demo.domain.Menu;


public interface ManageMenuUCIntPort {
    public List<Menu> getMenus();
    public Menu getMenu(String idMenu);
    public Menu createMenu();
    public Menu addDish(String idMenu, String dishName, double dishValue);
    public Menu removeDish(String idMenu, String idDish);
    public Menu updateMenu(String idMenu, Menu newMenu);
   
}
