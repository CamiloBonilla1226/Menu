package com.menu.demo.application.output;

import java.util.List;

import com.menu.demo.domain.Menu;

public interface ManageMenuGatewayIntPort {
    public Menu save(Menu menu);
    public Menu findById(String idmenu);
    public boolean existsById(String idmenu);
    public List<Menu> findAll();
}
