package com.unicauca.my_food.infrastucture.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.my_food.application.menu.IMenuService;
import com.unicauca.my_food.domain.menu.Menu;
import com.unicauca.my_food.domain.menu.value_objects.DishMenu;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus")
public class MenuRestController {
    private final IMenuService service;
    
    @GetMapping("")
    public ResponseEntity<List<Menu>> index(){
        List<Menu> menus = this.service.getMenus();
        return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
    }

    @GetMapping("/{idMenu}")
    public ResponseEntity<Menu> getMenu(@PathVariable String idMenu){
        Menu menu = this.service.getMenu(idMenu);
        return new ResponseEntity<Menu>(menu, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Menu> createMenu(){
        Menu menu = this.service.createMenu();
        return new ResponseEntity<Menu>(menu, HttpStatus.CREATED);
    }

    @PatchMapping("dishes/{idMenu}")
    public ResponseEntity<Menu> addDishToMenu(@PathVariable String idMenu, 
                                                @RequestParam String dishName, 
                                                @RequestParam double dishValue){
        Menu menu = this.service.addDish(idMenu, dishName, dishValue);
        return new ResponseEntity<Menu>(menu, HttpStatus.OK);
    }
    
    @DeleteMapping("dishes/{idMenu}/{idDish}")
    public ResponseEntity<Menu> removeDishToMenu(@PathVariable String idMenu, 
                                                @PathVariable String idDish){
                                                    Menu menu = this.service.removeDish(idMenu, idDish);
        return new ResponseEntity<Menu>(menu, HttpStatus.OK);
    }


    @PutMapping("/{idMenu}")
    public ResponseEntity<Menu> updateMenu(@PathVariable String idMenu, 
                                           @RequestParam Menu newMenu) {
        Menu menu = this.service.updateMenu(idMenu, newMenu);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @GetMapping("/dates/{idMenu}")
    public ResponseEntity<String> getMenuDate(@PathVariable String idMenu) {
        String menuDate = this.service.getOrderDate(idMenu);
        return new ResponseEntity<>(menuDate, HttpStatus.OK);
    }
    
    @GetMapping("/dishes/{idMenu}")
    public ResponseEntity<List<DishMenu>> getDishesFromMenu(@PathVariable String idMenu) {
        List<DishMenu> dishes = this.service.getDishesOrder(idMenu);
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @PatchMapping("/ingredients/{idMenu}/{dishId}")
public ResponseEntity<Menu> addIngredientsToDish(@PathVariable String idMenu, 
                                                  @PathVariable String dishId, 
                                                  @RequestParam List<String> ingredientNames) {
    Menu menu = this.service.addIngredient(idMenu, dishId, ingredientNames);
    return new ResponseEntity<>(menu, HttpStatus.OK);
}

    @DeleteMapping("/ingredients/{idMenu}/{dishId}/{ingredientId}")
    public ResponseEntity<Menu> removeIngredientFromDish(@PathVariable String idMenu, 
                                                           @PathVariable String dishId, 
                                                           @PathVariable String ingredientId) {
        Menu menu = this.service.removeIngredient(idMenu, dishId, ingredientId);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @DeleteMapping("/{idMenu}")
    public ResponseEntity<Menu> removeMenu(@PathVariable String idMenu) {
        Menu menu = this.service.removeMenu(idMenu);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

}
