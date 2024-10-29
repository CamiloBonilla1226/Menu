package com.menu.demo.domain.value_objects;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor

public class IngredientDish {
    private String id; 
    private List<String> name;
    public IngredientDish(){
        this.id = UUID.randomUUID().toString();
    }
}

