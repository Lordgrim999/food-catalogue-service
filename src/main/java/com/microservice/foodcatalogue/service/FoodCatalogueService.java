package com.microservice.foodcatalogue.service;

import com.microservice.foodcatalogue.DTO.FoodCataloguePage;
import com.microservice.foodcatalogue.DTO.FoodItemDTO;
import com.microservice.foodcatalogue.DTO.Restaurant;
import com.microservice.foodcatalogue.Repository.FoodItemRepository;
import com.microservice.foodcatalogue.entity.FoodItem;
import com.microservice.foodcatalogue.mapper.FoodItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodCatalogueService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    RestTemplate restTemplate;

    public FoodItemDTO addFoodItem(FoodItemDTO foodItem) {

        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(foodItemRepository.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItem)));
    }

    public FoodCataloguePage getRestaurantDetailsWithFoodItem(Integer restaurantId) {
        List<FoodItemDTO> foodItems=fetchFoodItemList(restaurantId);
        Restaurant restaurant=fetchRestaurantDetailsById(restaurantId);
       return createFoodCataloguePage(foodItems,restaurant);

    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItemDTO> foodItems, Restaurant restaurant) {
         FoodCataloguePage foodCataloguePage=new FoodCataloguePage();
         foodCataloguePage.setFoodItemsList(foodItems);
        foodCataloguePage.setRestaurant(restaurant);
         return foodCataloguePage;
    }

    private Restaurant fetchRestaurantDetailsById(Integer restaurantId) {

       return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId,Restaurant.class);
    }

    private List<FoodItemDTO> fetchFoodItemList(Integer restaurantId) {
        List<FoodItem> foodItems=foodItemRepository.findByRestaurantId(restaurantId);
        return foodItems.stream().map(FoodItemMapper.INSTANCE::mapFoodItemToFoodItemDTO).collect(Collectors.toList());

    }
}
