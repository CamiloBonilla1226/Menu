package com.menu.demo.infrastructure.input.controller.DTOResponse;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuDTOResponse {
    private String id_order;
    private DateMenuResponse date; 
    private List<DishDTOResponse> dishes;
   

    public MenuDTOResponse(){

    }
}
