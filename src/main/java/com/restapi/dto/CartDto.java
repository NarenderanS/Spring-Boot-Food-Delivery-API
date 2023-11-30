package com.restapi.dto;

import com.restapi.model.Cart;
import com.restapi.model.Category;
import com.restapi.request.CategoryRequest;
import com.restapi.response.CartResponse;
import com.restapi.response.CategoryResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartDto {

    public List<CartResponse> mapToCartResponse(List<Cart> cartList) {
        List<CartResponse> cartResponses = new ArrayList<>();
        for(Cart cart:cartList){
            CartResponse cartResponse=new CartResponse();
            cartResponse.setId(cart.getId());
            cartResponse.setUserId(cart.getAppUser().getId());
            cartResponse.setProductId(cart.getProduct().getId());
            cartResponse.setProductName(cart.getProduct().getTitle());
            cartResponse.setDescription(cart.getProduct().getDescription());
            cartResponse.setPrice(cart.getProduct().getPrice());
            cartResponse.setCategoryId(cart.getProduct().getCategory().getId());
            cartResponse.setRestaurantId(cart.getProduct().getRestaurant().getId());
            cartResponse.setVegOrNonVegId(cart.getProduct().getVegOrNonVeg().getId());
            cartResponse.setRestaurantName(cart.getProduct().getRestaurant().getTitle());
            cartResponse.setCount(cart.getCount());
            cartResponses.add(cartResponse);
        }

        return cartResponses;
    }

    public Category mapToCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        if (categoryRequest.getId() != null) {
            category.setId(categoryRequest.getId());
        }
        category.setTitle(categoryRequest.getTitle());
        return category;
    }
}
