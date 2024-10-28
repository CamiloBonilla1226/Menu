package com.menu.demo.infrastructure.output.persistence.gateway;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.menu.demo.application.output.ManageMenuGatewayIntPort;
import com.menu.demo.domain.Menu;
import com.menu.demo.infrastructure.output.persistence.entities.MenuEntity;
import com.menu.demo.infrastructure.output.persistence.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManageMenuGatewayImplAdapter implements ManageMenuGatewayIntPort {
    private final OrderRepository bd;
    private final ModelMapper mapper;
  
    @Override
    public Menu save(Menu menu) {
        MenuEntity menuEntity = this.mapper.map(menu, MenuEntity.class);
        MenuEntity menuSaved = this.bd.save(menuEntity);
        Menu response = this.mapper.map(menuSaved, Menu.class);
        return response;
    }
    @Override
    public Menu findById(String idmenu) {
        MenuEntity menu = this.bd.findById(idmenu).get();
        Menu response = this.mapper.map(menu, Menu.class);
        return response;
    }
    @Override
    public boolean existsById(String idmenu) {
        return this.bd.existsById(idmenu);
    }
    @Override
    public List<Menu> findAll() {
        List<MenuEntity> menus = this.bd.findAll();
        List<Menu> response = this.mapper.map(menus, new TypeToken<List<Menu>>(){}.getType());
        return response;
    }   

}
