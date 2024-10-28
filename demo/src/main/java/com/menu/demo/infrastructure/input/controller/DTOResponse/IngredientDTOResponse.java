package com.menu.demo.infrastructure.input.controller.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientDTOResponse {
    private String id_date;
    private String name;

    public IngredientDTOResponse(){
        
    }
}
