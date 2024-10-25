package com.unicauca.my_food.infrastucture.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.unicauca.my_food.domain.inventory.Product;
import com.unicauca.my_food.infrastucture.exceptionHandler.ownException.ObjectExistsException;
import com.unicauca.my_food.infrastucture.exceptionHandler.ownException.ObjectNotFoundException;

@Repository
public class InventoryRepository {
    private final HashMap<String, Product> db;

    public InventoryRepository() {
        this.db = new HashMap<>();
    }

    public Product save(Product product) {
        if (this.db.containsKey(product.getId()))
            throw new ObjectExistsException("Object exists...");
        this.db.put(product.getId(), product);
        return this.db.get(product.getId());
    }

    public Product update(Product product) {
        if (this.db.containsKey(product.getId()))
            throw new ObjectNotFoundException("Object was not found...");
        this.db.remove(product.getId());
        return this.save(product);
    }

    public Product delete(String productId) {
        if (!this.db.containsKey(productId))
            throw new ObjectNotFoundException("Object was not found...");
        return this.db.remove(productId);
    }

    public List<Product> findAll() {
        return new ArrayList<>(this.db.values());
    }

    public Product findById(String productId) {
        Product found = this.db.get(productId);
        if (found == null)
            throw new ObjectNotFoundException("Object was not found...");
        return found;
    }

    public List<Product> expired() {
        List<Product> expired = new ArrayList<>();
        for (Product product : this.db.values())
            if (product.isExpired())
                expired.add(product);
        return expired;
    }

    public void markExpired() {
        this.db.values().forEach(Product::markExpired);
    }
}
