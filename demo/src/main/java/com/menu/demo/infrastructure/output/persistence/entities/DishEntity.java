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
import jakarta.persistence.ManyToOne;
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
    private Double price;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dish_ingredients", joinColumns = @JoinColumn(name = "id_dish"), inverseJoinColumns = @JoinColumn(name = "id_ingredient"))
    private List<IngredientEntity> ingredients;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_menu", nullable = false)
    private MenuEntity menu;

    public DishEntity() {
        this.ingredients = new ArrayList<>();
    }

}