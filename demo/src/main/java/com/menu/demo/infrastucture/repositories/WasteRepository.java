package com.unicauca.my_food.infrastucture.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.unicauca.my_food.domain.waste.Waste;
import com.unicauca.my_food.infrastucture.exceptionHandler.ownException.ObjectExistsException;
import com.unicauca.my_food.infrastucture.exceptionHandler.ownException.ObjectNotFoundException;

@Repository
public class WasteRepository {
    private final HashMap<String, Waste> db;

    public WasteRepository(){
        this.db = new HashMap<>();
    }

    public List<Waste> findAll(){
        return new ArrayList<>(db.values());
    }

    public Waste findById(String wasteId){
        if(!this.db.containsKey(wasteId))
            throw new ObjectNotFoundException("Waste not found...");

        return this.db.get(wasteId);    
    }

    public boolean save(Waste waste){
        if(this.db.containsKey(waste.getIdWaste()))
            throw new ObjectExistsException("Waste already exists...");

        this.db.put(waste.getIdWaste(), waste);
        return true;
    }

    public boolean update(String oldWasteId, Waste newWaste){
        if(!this.db.containsKey(oldWasteId))
            throw new ObjectNotFoundException("Waste not found...");

        this.db.remove(oldWasteId);
        return this.save(newWaste);
    }

    public boolean delete(String wasteId){
        if(!this.db.containsKey(wasteId))
            throw new ObjectNotFoundException("Waste not found...");

        this.db.remove(wasteId);
        return true;
    }
}
