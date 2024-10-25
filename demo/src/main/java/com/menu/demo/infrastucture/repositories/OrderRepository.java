package com.unicauca.my_food.infrastucture.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.unicauca.my_food.domain.order_consumption.Order;
import com.unicauca.my_food.infrastucture.exceptionHandler.ownException.ObjectExistsException;
import com.unicauca.my_food.infrastucture.exceptionHandler.ownException.ObjectNotFoundException;

@Repository
public class OrderRepository {
    private final HashMap<String, Order> db;

    public OrderRepository(){
        this.db = new HashMap<>();
    }

    public List<Order> findAll(){
        return new ArrayList<>(db.values());
    }

    public Order findById(String orderId){
        if(!this.db.containsKey(orderId))
            throw new ObjectNotFoundException("Object was not found...");

        return this.db.get(orderId);
    }

    public boolean save(Order order){
        if(this.db.containsKey(order.getId()))
            throw new ObjectExistsException("Object exists...");

        this.db.put(order.getId(), order);
        return true;
    }

    public boolean update(String oldOrderId, Order newOrder){
        if(!this.db.containsKey(oldOrderId))
            throw new ObjectNotFoundException("Object was not found...");

        this.db.remove(oldOrderId);
        return this.save(newOrder);
    }

    public boolean delete(String orderId){
        if(!this.db.containsKey(orderId))
            throw new ObjectNotFoundException("Object was not found...");
        
        this.db.remove(orderId);
        return true;
    }

    
}
