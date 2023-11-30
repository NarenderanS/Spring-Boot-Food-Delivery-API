package com.restapi.dto;

import com.restapi.model.AppUser;
import com.restapi.model.Restaurant;
import com.restapi.request.RestaurantRequest;
import com.restapi.response.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantDto {
    @Autowired
    private ProductDto productDto;
    public Restaurant mapToRestaurnt(RestaurantRequest restaurantRequest) {
        Restaurant restaurant=new Restaurant();
        restaurant.setTitle(restaurantRequest.getTitle());
        restaurant.setAddress(restaurantRequest.getAddress());
        restaurant.setPhoto(restaurantRequest.getPhoto());
        return restaurant;
    }

    public RestaurantResponse mapToRestaurantResponse(Restaurant restaurant) {
        RestaurantResponse restaurantResponse=new RestaurantResponse();
        restaurantResponse.setId(restaurant.getId());
        restaurantResponse.setRestaurantId(restaurant.getAppUser().getId());
        restaurantResponse.setTitle(restaurant.getTitle());
        restaurantResponse.setAddress(restaurant.getAddress());
        restaurantResponse.setPhoto(restaurant.getPhoto());
//        restaurantResponse.setProductList(productDto.mapToProductResponse(restaurant.getProducts()));
        return restaurantResponse;
    }
    public List<RestaurantResponse> mapToRestaurantResponse(List<Restaurant> restaurants) {
        List<RestaurantResponse> restaurantResponses=new ArrayList<>();
        for(Restaurant restaurant:restaurants){
            restaurantResponses.add(mapToRestaurantResponse(restaurant));
        }
        return restaurantResponses;
    }



}
