package com.restapi.response;

import com.restapi.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private Long id;
    private Long userId;
    private Long productId;
    private String productName;
    private String description;
    private double price;
    private Long categoryId;
    private Long restaurantId;
    private String restaurantName;
    private Long vegOrNonVegId;
    private Integer count;
}
