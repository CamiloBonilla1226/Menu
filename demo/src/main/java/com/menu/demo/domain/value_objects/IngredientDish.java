package com.menu.demo.domain.value_objects;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDish {
    private String id; 
    private List<String> name;
}

