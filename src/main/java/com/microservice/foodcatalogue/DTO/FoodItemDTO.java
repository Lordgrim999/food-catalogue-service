package com.microservice.foodcatalogue.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDTO {
    private int id;
    private String description;
    private boolean isVeg;
    private Number price;
    private String name;
    private Integer restaurantId;
    private Integer quantity;
}
