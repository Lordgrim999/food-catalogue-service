package com.microservice.foodcatalogue.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCataloguePage {

    public List<FoodItemDTO> foodItemsList;
    public Restaurant restaurant;


}
