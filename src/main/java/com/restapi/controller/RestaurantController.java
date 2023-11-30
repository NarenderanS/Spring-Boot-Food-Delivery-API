package com.restapi.controller;

import com.restapi.response.RestaurantResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private RestaurantService restaurantService;
    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllRestaurant() {
        List<RestaurantResponse> restaurantResponses = restaurantService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(restaurantResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<APIResponse> getRestaurant(@PathVariable Long restaurantId) {
        RestaurantResponse restaurantResponses = restaurantService.findById(restaurantId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(restaurantResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
