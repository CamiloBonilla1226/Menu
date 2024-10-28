package com.menu.demo.infrastructure.output.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "dishes")
@Data
@AllArgsConstructor
public class DishEntity {
    @Id
    @Column(length = 200)
    private String id_dish;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "dish_ingredients",
        joinColumns = @JoinColumn(name = "id_dish"),
        inverseJoinColumns = @JoinColumn(name = "id_ingredient")
    )
    private List<IngredientEntity> ingredients;
    
    private double price;

    public DishEntity(){
        this.ingredients = new ArrayList<>();
    }

}
