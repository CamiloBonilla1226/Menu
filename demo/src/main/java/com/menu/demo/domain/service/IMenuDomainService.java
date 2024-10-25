package com.menu.demo.domain.service;
import com.menu.demo.domain.value_objects.DishMenu;
import com.menu.demo.domain.value_objects.IngredientDish;
import com.menu.demo.domain.Menu;


public interface IMenuDomainService {
    public boolean addDish(Menu menu, DishMenu dish);
    public boolean removeDish(Menu menu, String idDish);
    public String getDateMenu(Menu menu);
    public boolean addIngredient(DishMenu dish, IngredientDish ingredient);
    public boolean removeIngredient(DishMenu dish, String idIngredient);   
}
