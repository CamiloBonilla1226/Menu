package com.menu.demo.infrastructure.input.controller.DTOResponse;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DishDTOResponse {
    private String id_dish;
    private String name;
    private List<IngredientDTOResponse> ingredients;
    private double price;

    public DishDTOResponse(){
        this.ingredients = new ArrayList<>();
    }
}
