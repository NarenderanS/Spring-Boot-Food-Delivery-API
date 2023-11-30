package com.restapi.response;

import com.restapi.model.Address;
import com.restapi.model.OrderedProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private List<OrderedProduct> orderedProducts;
//    private String productName;
//    private Long productId;
//    private String restaurantName;
//    private Double price;
    private Long userId;
    private String name;
    private String username;
    private Address address;
    private String orderStatus;
    private String orderTime;
}
