package com.unicauca.my_food.infrastucture.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.my_food.application.inventory.IInventoryService;
import com.unicauca.my_food.domain.inventory.Product;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {
    private final IInventoryService service;

    @PostMapping("")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product response = this.service.save(product);
        return new ResponseEntity<Product>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Product> update(@RequestBody Product product) {
        Product response = this.service.update(product);
        return new ResponseEntity<Product>(response, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Product> delete(@RequestBody String id) {
        Product response = this.service.delete(id);
        return new ResponseEntity<Product>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> index() {
        List<Product> products = this.service.findAll();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/expired")
    public ResponseEntity<List<Product>> expired() {
        List<Product> products = this.service.productsExpired();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/increase/{id}/{amount}")
    public ResponseEntity<Product> increase(@PathVariable String id, @PathVariable int amount) {
        Product response = this.service.increase(id, amount);
        return new ResponseEntity<Product>(response, HttpStatus.OK);
    }

    @GetMapping("/decrease/{id}/{amount}")
    public ResponseEntity<Product> decrease(@PathVariable String id, @PathVariable int amount) {
        Product response = this.service.decrease(id, amount);
        return new ResponseEntity<Product>(response, HttpStatus.OK);
    }

    @PutMapping("expired")
    public ResponseEntity<Boolean> expired(@PathVariable String id, @RequestBody String entity) {
        this.service.markExpired();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
