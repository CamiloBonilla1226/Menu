package com.menu.demo.infrastructure.input.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.menu.demo.application.input.ManageMenuUCIntPort;
import com.menu.demo.application.output.MapperMenuInfrastructureDomainIntPort;
import com.menu.demo.domain.Menu;
import com.menu.demo.infrastructure.input.controller.DTOResponse.MenuDTOResponse;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {
    private final ManageMenuUCIntPort menuUC;
    private final MapperMenuInfrastructureDomainIntPort mapper;
   

    @GetMapping("")
    public ResponseEntity<List<MenuDTOResponse>> index(){
        List<Menu> menus = this.menuUC.getMenus();
        return new ResponseEntity<List<MenuDTOResponse>>(
            this.mapper.mapModelToResponse(menus), HttpStatus.OK);
    }

    @GetMapping("/{idMenu}")
    public ResponseEntity<MenuDTOResponse> findMenuById(@PathVariable String idMenu){
        Menu menus = this.menuUC.getMenu(idMenu);
        
        return new ResponseEntity<MenuDTOResponse>(
            this.mapper.mapModelToResponse(menus), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<MenuDTOResponse> createMenu(){
        Menu menu = this.menuUC.createMenu();
        return new ResponseEntity<MenuDTOResponse>(
            this.mapper.mapModelToResponse(menu), HttpStatus.CREATED);
    }

    @PatchMapping("dishes/{idMenu}")
    public ResponseEntity<MenuDTOResponse> addDishToMenu(@PathVariable String idMenu, 
                                                @RequestParam String dishName, 
                                                @RequestParam double dishValue){
        Menu menu = this.menuUC.addDish(idMenu, dishName, dishValue);
        return new ResponseEntity<MenuDTOResponse>(
            this.mapper.mapModelToResponse(menu), HttpStatus.OK);
    }

    @DeleteMapping("dishes/{idMenu}/{idDish}")
    public ResponseEntity<MenuDTOResponse> removeDishToMenu(@PathVariable String idMenu, 
                                                @PathVariable String idDish){
        Menu menu = this.menuUC.removeDish(idMenu, idDish);
        return new ResponseEntity<MenuDTOResponse>(
            this.mapper.mapModelToResponse(menu), HttpStatus.OK);
    }
    
  // @PutMapping("/{idMenu}/{state}")
  // public ResponseEntity<MenuDTOResponse> updateOrderState(@PathVariable String idOrder, 
  //                                             @PathVariable int state){
  //     Menu menu = this.menuUC.updateOrderState(idOrder, state);
  //     return new ResponseEntity<MenuDTOResponse>(
  //         this.mapper.mapModelToResponse(menu), HttpStatus.OK);
  // }


}
