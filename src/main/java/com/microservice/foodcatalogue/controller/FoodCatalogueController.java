package com.microservice.foodcatalogue.controller;

import com.microservice.foodcatalogue.DTO.FoodCataloguePage;
import com.microservice.foodcatalogue.DTO.FoodItemDTO;
import com.microservice.foodcatalogue.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin
public class FoodCatalogueController {

    @Autowired
    private FoodCatalogueService foodCatalogueService;


    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO>  addFoodItem(@RequestBody FoodItemDTO foodItem)

    {
        return new ResponseEntity<>(foodCatalogueService.addFoodItem(foodItem), HttpStatus.CREATED);
    }

    @GetMapping("/getRestaurantDetailsWithFoodItem/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> getRestaurantDetailsWithFoodItem(@PathVariable Integer restaurantId)
    {
        return new ResponseEntity<>(foodCatalogueService.getRestaurantDetailsWithFoodItem(restaurantId),HttpStatus.OK);
    }
}
