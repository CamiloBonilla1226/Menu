package com.unicauca.my_food.application.menu;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.unicauca.my_food.domain.menu.Menu;
import com.unicauca.my_food.domain.menu.service.MenuDomainService;
import com.unicauca.my_food.domain.menu.value_objects.DishMenu;
import com.unicauca.my_food.domain.menu.value_objects.IngredientDish;
import com.unicauca.my_food.infrastucture.exceptionHandler.ownException.ObjectNotFoundException;
import com.unicauca.my_food.infrastucture.repositories.MenuRepository;

@Service
public class MenuService implements IMenuService {

   private final MenuDomainService serviceDomain;
    private final MenuRepository repository;

    public MenuService(MenuDomainService serviceDomain, MenuRepository repository) {
        this.serviceDomain = serviceDomain;
        this.repository = repository;
    }

    @Override
    public List<Menu> getMenus() {
        return this.repository.findAll(); 
    }

    @Override
    public Menu getMenu(String idMenu) {
        return this.repository.findById(idMenu);
    }

    @Override
    public Menu createMenu() {
        Menu menu = new Menu();
        this.repository.save(menu);
        return menu;
    }

    @Override
    public Menu updateMenu(String idMenu, Menu newMenu) {
        if (this.repository.findById(idMenu) == null) {
            throw new ObjectNotFoundException("Menu was not found");
        }
        this.repository.update(idMenu, newMenu);
        return newMenu;
    }
   
    @Override
    public Menu addDish(String idMenu, String dishName, double dishValue) {
        Menu menu = this.repository.findById(idMenu);
        if (menu == null) {
            throw new ObjectNotFoundException("Menu was not found");
        }

        DishMenu dish = new DishMenu(dishName, dishValue);
        this.serviceDomain.addDish(menu, dish);
        return menu;
    }

    @Override
    public Menu addIngredient(String idMenu, String dishId, List<String> ingredientNames) {
       
        Menu menu = this.repository.findById(idMenu);
        if (menu == null) {
            throw new ObjectNotFoundException("Menu was not found");
        }
    
     
        DishMenu dish = menu.getDishes().stream()
            .filter(d -> d.getId().equals(dishId))
            .findFirst()
            .orElseThrow(() -> new ObjectNotFoundException("Dish not found"));
    
       
        IngredientDish ingredient = new IngredientDish(UUID.randomUUID().toString(), ingredientNames);
    
      
        dish.addIngredient(ingredient);
        
        return menu; 
    }
    

    @Override
    public Menu removeIngredient(String idMenu, String dishId, String ingredientId) {

        Menu menu = this.repository.findById(idMenu);
        if (menu == null) {
            throw new ObjectNotFoundException("Menu was not found");
        }


        DishMenu dish = menu.getDishes().stream()
            .filter(d -> d.getId().equals(dishId))
            .findFirst()
            .orElseThrow(() -> new ObjectNotFoundException("Dish not found"));


        dish.removeIngredient(ingredientId);

        return menu; 
    }


    @Override
    public Menu removeDish(String idMenu, String idDish) {
        Menu menu = this.repository.findById(idMenu);
        if (menu == null) {
            throw new ObjectNotFoundException("Menu was not found");
        }

        this.serviceDomain.removeDish(menu, idDish);
        return menu;
    }

    

    @Override
    public String getOrderDate(String idMenu) {
        Menu menu = this.repository.findById(idMenu);
        if (menu == null) {
            throw new ObjectNotFoundException("Menu was not found");
        }

        return this.serviceDomain.getDateMenu(menu);
    }

    @Override
    public List<DishMenu> getDishesOrder(String idMenu) {
        Menu menu = this.repository.findById(idMenu);
        if (menu == null) {
            throw new ObjectNotFoundException("Menu was not found");
        }

        return menu.getDishes();
    }


    @Override
    public Menu removeMenu(String idMenu) {
        Menu menu = this.repository.findById(idMenu);

        if (menu == null) {
            throw new ObjectNotFoundException("Menu was not found");
        }

        this.repository.delete(idMenu);

        return menu; 
    }



    
    
}


