package com.menu.demo.domain.useCases;


import java.util.List;

import com.menu.demo.application.input.ManageMenuUCIntPort;
import com.menu.demo.application.output.ExceptionFormatterIntPort;
import com.menu.demo.application.output.ManageMenuGatewayIntPort;
import com.menu.demo.domain.Menu;
import com.menu.demo.domain.service.MenuDomainService;

import com.menu.demo.domain.value_objects.DishMenu;


public class ManageMenuUCImplAdapter implements ManageMenuUCIntPort{
    private final MenuDomainService domainService;
    private final ManageMenuGatewayIntPort gateway;
    private final ExceptionFormatterIntPort formatter;

    public ManageMenuUCImplAdapter(MenuDomainService domainService,
                                    ManageMenuGatewayIntPort gateway ,
                                    ExceptionFormatterIntPort formatter){
        this.domainService = domainService;
        this.gateway = gateway;
        this.formatter = formatter;
    }

 
    @Override
    public List<Menu> getMenus() {
        return this.gateway.findAll();
    }

    @Override
    public Menu getMenu(String idMenu) {
        if(!this.gateway.existsById(idMenu))
            formatter.responseEntityNotFound("entity not found...");
        
        return this.gateway.findById(idMenu);
    }

    @Override
    public Menu createMenu() {
        // Crea una nueva instancia de Menu usando el constructor por defecto
        Menu newMenu = new Menu();
        newMenu.getObjDate().setObjMenu(newMenu);
        // Guarda la instancia de Menu en la base de datos usando el gateway
        Menu savedMenu = this.gateway.save(newMenu);

        // Retorna el menú recién creado y guardado
        return savedMenu;
    }

    

    @Override
public Menu updateMenu(String idMenu, Menu newMenu) {
    if (!this.gateway.existsById(idMenu)) {
        formatter.responseEntityNotFound("Menu not found...");
    }

    Menu existingMenu = this.gateway.findById(idMenu);

    // Actualiza los datos del menú actual con los del nuevo menú
    existingMenu.setDishes(newMenu.getDishes());
    existingMenu.setObjDate(newMenu.getObjDate());

    Menu updatedMenu = this.gateway.save(existingMenu);
    return updatedMenu;
}

    

    @Override
    public Menu addDish(String idMenu, String dishName, double dishValue) {
        if(!this.gateway.existsById(idMenu))
        formatter.responseEntityNotFound("entity not found...");

        Menu menu = this.gateway.findById(idMenu);
        DishMenu dish = new DishMenu(dishName, dishValue);

        this.domainService.addDish(menu, dish);
        
        Menu response = this.gateway.save(menu);
        return response;
    }


    @Override
    public Menu removeDish(String idMenu, String idDish) {
        Menu menu = null;
        if(!this.gateway.existsById(idMenu))
            formatter.responseEntityNotFound("entity not found...");
            
            menu = this.gateway.findById(idMenu);
        boolean flag = this.domainService.removeDish(menu, idDish);

        if(flag){
            Menu response = this.gateway.save(menu);
            return response;
        }

        this.formatter.responseEntityNotFound("Dish was not found...");
        return menu;
    }
}
