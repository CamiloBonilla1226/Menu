package com.menu.demo.application.menu;

import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;

import com.menu.demo.domain.value_objects.DishMenu;
import com.menu.demo.domain.value_objects.IngredientDish;
import com.menu.demo.domain.Menu;
import com.menu.demo.infrastucture.exceptionHandler.ownException.ObjectNullException;

public interface IMenuService {

    public List<Menu> getMenus();
    public Menu getMenu(String idMenu);
    public Menu createMenu();
    public Menu removeMenu(String idMenu);
    public Menu updateMenu(String idMenu, Menu newMenu);
    public Menu addDish(String idMenu, String dishName, double dishValue);
    public Menu addIngredient(String idMenu, String dishId, List<String> ingredientNames);
    public Menu removeIngredient(String idMenu, String dishId, String ingredientId);
    public Menu removeDish(String idMenu, String idDish);
    public String getOrderDate(String idMenu);
    public List<DishMenu> getDishesOrder(String idMenu);
   
}
