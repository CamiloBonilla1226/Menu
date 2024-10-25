package com.unicauca.my_food.infrastucture.repositories;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;


import org.springframework.stereotype.Repository;

import com.unicauca.my_food.domain.menu.Menu;
import com.unicauca.my_food.infrastucture.exceptionHandler.ownException.ObjectExistsException;
import com.unicauca.my_food.infrastucture.exceptionHandler.ownException.ObjectNotFoundException;

@Repository
public class MenuRepository {
    private final HashMap<String, Menu> db;

    public MenuRepository(){
        this.db = new HashMap<>();
    }


    public List<Menu> findAll() {
        return new ArrayList<>(db.values());
    }

    public Menu findById(String idMenu) {
        if(!this.db.containsKey(idMenu))
            throw new ObjectNotFoundException("Object was not found...");

        return this.db.get(idMenu);
    }

    public boolean save(Menu menu) {
        if(this.db.containsKey(menu.getId()))
            throw new ObjectExistsException("Object exists...");

        this.db.put(menu.getId(), menu);
        return true;
    }

    public boolean update(String oldMenuId, Menu newMenu) {
        if(!this.db.containsKey(oldMenuId))
            throw new ObjectNotFoundException("Object was not found...");

        this.db.remove(oldMenuId);
        return this.save(newMenu);
    }

    public boolean delete(String idMenu){
        if(!this.db.containsKey(idMenu))
            throw new ObjectNotFoundException("Object was not found...");
        
        this.db.remove(idMenu);
        return true;
    }
    
}
