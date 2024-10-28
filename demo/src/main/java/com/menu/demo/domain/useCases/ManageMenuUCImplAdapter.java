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
      //  Menu menu = new Menu();
        
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createMenu'");
    }

    @Override
    public Menu updateMenu(String idMenu, Menu newMenu) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMenu'");
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
