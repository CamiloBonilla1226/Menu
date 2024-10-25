package com.unicauca.my_food.infrastucture.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.my_food.application.order_comsuption.IOrderService;
import com.unicauca.my_food.domain.order_consumption.Order;
import com.unicauca.my_food.domain.order_consumption.value_objects.Dish;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderRestController {
   private final IOrderService service;

    @GetMapping("")
    public ResponseEntity<List<Order>> index(){
        List<Order> orders = this.service.getOrders();
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    @GetMapping("/{idOrder}")
    public ResponseEntity<Order> getOrder(@PathVariable String idOrder){
        Order order = this.service.getOrder(idOrder);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    
    @PostMapping("")
    public ResponseEntity<Order> createOrder(){
        Order order = this.service.createOrder();
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @PatchMapping("dishes/{idOrder}")
    public ResponseEntity<Order> addDishToOrder(@PathVariable String idOrder, 
                                                @RequestParam String dishName, 
                                                @RequestParam double dishValue){
        Order order = this.service.addDish(idOrder, dishName, dishValue);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @DeleteMapping("dishes/{idOrder}/{idDish}")
    public ResponseEntity<Order> removeDishToOrder(@PathVariable String idOrder, 
                                                @PathVariable String idDish){
        Order order = this.service.removeDish(idOrder, idDish);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    
    @PutMapping("/{idOrder}/{state}")
    public ResponseEntity<Order> updateOrderState(@PathVariable String idOrder, 
                                                @PathVariable int state){
        Order order = this.service.updateOrderState(idOrder, state);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @GetMapping("/dates/{idOrder}")
    public ResponseEntity<String> getOrderDate(@PathVariable String idOrder){
        String orderDate = this.service.getOrderDate(idOrder);
        return new ResponseEntity<>(orderDate, HttpStatus.OK);
    }

    @GetMapping("/hours/{idOrder}")
    public ResponseEntity<String> getOrderHour(@PathVariable String idOrder){
        String orderDate = this.service.getOrderHour(idOrder);
        return new ResponseEntity<>(orderDate, HttpStatus.OK);
    }

    @GetMapping("/dishes/{idOrder}")
    public ResponseEntity<List<Dish>> getDishesFromOrder(@PathVariable String idOrder){
        List<Dish> dishes = this.service.getDishesOrder(idOrder);
        return new ResponseEntity<List<Dish>>(dishes, HttpStatus.OK);
    }

    @PostMapping("/{idOrder}")
    public ResponseEntity<Double> calculateOrderTotalPrice(@PathVariable String idOrder){
        double totalPrice = this.service.calculateOrderTotalPrice(idOrder);
        return new ResponseEntity<>(totalPrice,HttpStatus.OK);
    }

}
